// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.utils;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ProcessedChannelBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotUsed;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class AsyncAnnotationUtil {
    private AsyncAnnotationUtil() {}

    public static SchemaObject getAsyncHeaders(AsyncOperation op, StringValueResolver resolver) {
        AsyncOperation.Headers headers = op.headers();
        if (headers.values().length == 0) {
            if (headers.notUsed()) {
                return AsyncHeadersNotUsed.NOT_USED;
            }
            return AsyncHeadersNotDocumented.NOT_DOCUMENTED;
        }
        if (!StringUtils.hasText(headers.schemaName())) {
            throw new IllegalArgumentException("The schemaName in @AsyncOperation.Headers must be set for values: "
                    + Arrays.toString(headers.values()));
        }

        String headerDescription =
                StringUtils.hasText(headers.description()) ? resolver.resolveStringValue(headers.description()) : null;

        SchemaObject headerSchema = new SchemaObject();
        headerSchema.setType(SchemaType.OBJECT);
        headerSchema.setTitle(headers.schemaName());
        headerSchema.setDescription(headerDescription);
        headerSchema.setProperties(new HashMap<>());

        Arrays.stream(headers.values())
                .collect(groupingBy(AsyncOperation.Headers.Header::name))
                .forEach((headerName, headersValues) -> {
                    String propertyName = resolver.resolveStringValue(headerName);

                    SchemaObject property = new SchemaObject();
                    property.setType(SchemaType.STRING);
                    property.setTitle(propertyName);
                    property.setDescription(getDescription(headersValues, resolver));
                    List<String> values = getHeaderValues(headersValues, resolver);
                    property.setExamples(new ArrayList<>(values));
                    property.setEnumValues(values);
                    headerSchema.getProperties().put(propertyName, property);
                });

        return headerSchema;
    }

    private static List<String> getHeaderValues(
            List<AsyncOperation.Headers.Header> value, StringValueResolver resolver) {
        return value.stream()
                .map(AsyncOperation.Headers.Header::value)
                .filter(StringUtils::hasText)
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
            AnnotatedElement annotatedElement, List<MessageBindingProcessor> messageBindingProcessors) {
        return messageBindingProcessors.stream()
                .map(messageBindingProcessor -> messageBindingProcessor.process(annotatedElement))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(
                        ProcessedMessageBinding::getType, ProcessedMessageBinding::getBinding, (e1, e2) -> e1));
    }

    public static void processAsyncMessageAnnotation(
            MessageObject.MessageObjectBuilder messageBuilder,
            AsyncMessage asyncMessage,
            StringValueResolver resolver) {
        String annotationMessageDescription = resolver.resolveStringValue(asyncMessage.description());
        if (StringUtils.hasText(annotationMessageDescription)) {
            annotationMessageDescription = TextUtils.trimIndent(annotationMessageDescription);
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

        String annotationTitle = resolver.resolveStringValue(asyncMessage.title());
        if (StringUtils.hasText(annotationTitle)) {
            messageBuilder.title(annotationTitle);
        }

        if (StringUtils.hasText(asyncMessage.contentType())) {
            messageBuilder.contentType(asyncMessage.contentType());
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

    public static Map<String, ChannelBinding> processChannelBindingFromAnnotation(
            AnnotatedElement annotatedElement, List<ChannelBindingProcessor> channelBindingProcessors) {
        return channelBindingProcessors.stream()
                .map(channelBindingProcessor -> channelBindingProcessor.process(annotatedElement))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(
                        ProcessedChannelBinding::getType, ProcessedChannelBinding::getBinding, (e1, e2) -> e1));
    }
}
