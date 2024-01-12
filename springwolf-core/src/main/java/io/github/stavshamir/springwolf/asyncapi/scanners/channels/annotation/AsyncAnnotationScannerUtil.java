// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncMessage;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaderSchema;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

class AsyncAnnotationScannerUtil {
    private AsyncAnnotationScannerUtil() {}

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

                    // FIXME: Replace AsyncHeaders by proper AsyncAPI v3 Headers
                    // MessageHeaders.of(
                    //         SchemaObject.builder()
                    //                 .description(getDescription(headers, resolver))
                    //                 .enumValues(values)
                    //                 .examples(exampleValue != null ? List.of(exampleValue) : null)
                    //                 .build());
                });

        return asyncHeaders;
    }

    private static List<String> getHeaderValues(
            List<AsyncOperation.Headers.Header> value, StringValueResolver resolver) {
        return value.stream()
                .map(AsyncOperation.Headers.Header::value)
                .map(resolver::resolveStringValue)
                .sorted()
                .toList();
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

    public static void processAsyncMessageAnnotation(
            MessageObject.MessageObjectBuilder messageBuilder,
            AsyncMessage asyncMessage,
            StringValueResolver resolver) {
        String annotationMessageDescription = resolver.resolveStringValue(asyncMessage.description());
        if (StringUtils.hasText(annotationMessageDescription)) {
            messageBuilder.description(annotationMessageDescription);
        }

        String annotationMessageId = resolver.resolveStringValue(asyncMessage.messageId());
        if (StringUtils.hasText(annotationMessageId)) {
            messageBuilder.messageId(annotationMessageId);
        }

        String annotationName = resolver.resolveStringValue(asyncMessage.name());
        if (StringUtils.hasText(annotationName)) {
            messageBuilder.name(annotationName);
        }

        // FIXME
        // String annotationSchemaFormat = asyncMessage.schemaFormat();
        // var schemaFormat = annotationSchemaFormat != null ? annotationSchemaFormat :
        // Message.DEFAULT_SCHEMA_FORMAT;
        //        messageBuilder.schemaFormat(schemaFormat);

        String annotationTitle = resolver.resolveStringValue(asyncMessage.title());
        if (StringUtils.hasText(annotationTitle)) {
            messageBuilder.title(annotationTitle);
        }
    }

    /**
     * extracts servers array from the given AsyncOperation, resolves placeholders with spring variables and
     * return a List of server names.
     *
     * @param op       the given AsyncOperation
     * @param resolver the StringValueResolver to resolve placeholders
     * @return List of server names
     */
    public static List<String> getServers(AsyncOperation op, StringValueResolver resolver) {
        return Arrays.stream(op.servers()).map(resolver::resolveStringValue).toList();
    }
}
