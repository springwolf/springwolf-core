// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaderSchema;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

class AsyncAnnotationScannerUtil {
    public static AsyncHeaders getAsyncHeaders(AsyncOperation op, StringValueResolver resolver) {
        if (op.headers().values().length == 0) {
            return AsyncHeaders.NOT_DOCUMENTED;
        }

        AsyncHeaders asyncHeaders = new AsyncHeaders(op.headers().schemaName());
        Arrays.stream(op.headers().values())
                .collect(groupingBy(AsyncOperation.Headers.Header::name))
                .forEach((headerName, headers) -> {
                    List<String> values = getHeaderValues(headers, resolver);
                    String exampleValue = values.stream().findFirst().orElse(null);
                    asyncHeaders.addHeader(AsyncHeaderSchema.headerBuilder()
                            .headerName(resolver.resolveStringValue(headerName))
                            .description(getDescription(headers, resolver))
                            .enumValue(values)
                            .example(exampleValue)
                            .build());
                });

        return asyncHeaders;
    }

    private static List<String> getHeaderValues(
            List<AsyncOperation.Headers.Header> value, StringValueResolver resolver) {
        return value.stream()
                .map(AsyncOperation.Headers.Header::value)
                .map(resolver::resolveStringValue)
                .sorted()
                .collect(Collectors.toList());
    }

    private static String getDescription(List<AsyncOperation.Headers.Header> value, StringValueResolver resolver) {
        return value.stream()
                .map(AsyncOperation.Headers.Header::description)
                .map(resolver::resolveStringValue)
                .filter(StringUtils::hasText)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public static Map<String, OperationBinding> processOperationBindingFromAnnotation(
            Method method, List<OperationBindingProcessor> operationBindingProcessors) {
        return operationBindingProcessors.stream()
                .map(operationBindingProcessor -> operationBindingProcessor.process(method))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(
                        ProcessedOperationBinding::getType, ProcessedOperationBinding::getBinding, (e1, e2) -> e1));
    }

    public static Map<String, MessageBinding> processMessageBindingFromAnnotation(
            Method method, List<MessageBindingProcessor> messageBindingProcessors) {
        return messageBindingProcessors.stream()
                .map(messageBindingProcessor -> messageBindingProcessor.process(method))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(
                        ProcessedMessageBinding::getType, ProcessedMessageBinding::getBinding, (e1, e2) -> e2));
    }

    public static Message processMessageFromAnnotation(Method method) {
        var messageBuilder = Message.builder();

        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof AsyncListener asyncListener) {
                return parseMessage(asyncListener.operation());
            } else if (annotation instanceof AsyncPublisher asyncPublisher) {
                return parseMessage(asyncPublisher.operation());
            }
        }

        return messageBuilder.build();
    }

    private static Message parseMessage(AsyncOperation asyncOperation) {
        var messageBuilder = Message.builder();

        var asyncMessage = asyncOperation.message();
        if (StringUtils.hasText(asyncMessage.description())) {
            messageBuilder.description(asyncMessage.description());
        }
        if (StringUtils.hasText(asyncMessage.messageId())) {
            messageBuilder.messageId(asyncMessage.messageId());
        }
        if (StringUtils.hasText(asyncMessage.name())) {
            messageBuilder.name(asyncMessage.name());
        }
        if (StringUtils.hasText(asyncMessage.schemaFormat())) {
            messageBuilder.schemaFormat(asyncMessage.schemaFormat());
        }
        if (StringUtils.hasText(asyncMessage.title())) {
            messageBuilder.title(asyncMessage.title());
        }

        return messageBuilder.build();
    }

    /**
     * Checks whether the method is an actual method on the class
     * <br/>
     * When generic are used in interfaces, after type erasure class will have an additional method (with object as parameter)
     *
     * @param method The method in question
     * @param type The original class, which may implement interface
     * @return true, when the method was created due to type erasure
     */
    public static boolean isMethodInherited(Method method, Class<?> type) {
        List<Method> methodsFromInterfaces = Arrays.stream(type.getInterfaces())
                .flatMap((c) -> Arrays.stream(c.getMethods()))
                .toList();

        for (Method methodFromInterface : methodsFromInterfaces) {
            if (method.getName().equals(methodFromInterface.getName())
                    && method.getReturnType().equals(methodFromInterface.getReturnType())
                    && Arrays.equals(method.getParameterTypes(), methodFromInterface.getParameterTypes())) {
                return true;
            }
        }
        return false;
    }

    /**
     * extracts servers array from the given AsyncOperation, resolves placeholdes with spring variables and
     * return a List of server names.
     *
     * @param op       the given AsyncOperation
     * @param resolver the StringValueResolver to resolve placeholders
     * @return List of server names
     */
    @NonNull
    public static List<String> getServers(AsyncOperation op, StringValueResolver resolver) {
        return Arrays.stream(op.servers()).map(resolver::resolveStringValue).collect(Collectors.toList());
    }
}
