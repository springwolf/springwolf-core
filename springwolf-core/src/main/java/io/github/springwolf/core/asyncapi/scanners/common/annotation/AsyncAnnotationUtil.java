// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.annotation;

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
import io.github.springwolf.core.asyncapi.scanners.common.utils.TextUtils;
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
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderSchemaObjectMerger.generateHeaderSchemaName;
import static java.util.stream.Collectors.groupingBy;

public class AsyncAnnotationUtil {
    private AsyncAnnotationUtil() {}

    public static SchemaObject getAsyncHeaders(AsyncOperation op, StringValueResolver stringValueResolver) {
        AsyncOperation.Headers headers = op.headers();
        if (headers.values().length == 0) {
            if (headers.notUsed()) {
                return AsyncHeadersNotUsed.NOT_USED;
            }
            return AsyncHeadersNotDocumented.NOT_DOCUMENTED;
        }

        String headerSchemaTitle;
        headerSchemaTitle =
                StringUtils.hasText(headers.schemaName()) ? headers.schemaName() : generateHeaderSchemaName(headers);
        String headerDescription = StringUtils.hasText(headers.description())
                ? stringValueResolver.resolveStringValue(headers.description())
                : null;

        SchemaObject headerSchema = new SchemaObject();
        headerSchema.setType(Set.of(SchemaType.OBJECT));
        headerSchema.setTitle(headerSchemaTitle);
        headerSchema.setDescription(headerDescription);
        headerSchema.setProperties(new HashMap<>());

        Arrays.stream(headers.values())
                .collect(groupingBy(AsyncOperation.Headers.Header::name))
                .forEach((headerName, headersValues) -> {
                    String propertyName = stringValueResolver.resolveStringValue(headerName);

                    SchemaObject property = new SchemaObject();
                    property.setType(Set.of(SchemaType.STRING));

                    property.setTitle(propertyName);
                    property.setDescription(getDescription(headersValues, stringValueResolver));
                    property.setFormat(getFormat(headersValues, stringValueResolver));
                    List<String> values = getHeaderValues(headersValues, stringValueResolver);
                    if (!values.isEmpty()) {
                        property.setExamples(new ArrayList<>(values));
                        property.setEnumValues(values);
                    }
                    headerSchema.getProperties().put(propertyName, property);
                });

        return headerSchema;
    }

    private static List<String> getHeaderValues(
            List<AsyncOperation.Headers.Header> value, StringValueResolver stringValueResolver) {
        return value.stream()
                .map(AsyncOperation.Headers.Header::value)
                .filter(StringUtils::hasText)
                .flatMap(text -> Optional.ofNullable(stringValueResolver.resolveStringValue(text)).stream())
                .sorted()
                .toList();
    }

    private static String getDescription(
            List<AsyncOperation.Headers.Header> value, StringValueResolver stringValueResolver) {
        return value.stream()
                .map(AsyncOperation.Headers.Header::description)
                .filter(StringUtils::hasText)
                .flatMap(text -> Optional.ofNullable(stringValueResolver.resolveStringValue(text)).stream())
                .sorted()
                .findFirst()
                .orElse(null);
    }

    private static String getFormat(
            List<AsyncOperation.Headers.Header> value, StringValueResolver stringValueResolver) {
        return value.stream()
                .map(AsyncOperation.Headers.Header::format)
                .filter(StringUtils::hasText)
                .flatMap(text -> Optional.ofNullable(stringValueResolver.resolveStringValue(text)).stream())
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public static Map<String, OperationBinding> processOperationBindingFromAnnotation(
            Method method, List<OperationBindingProcessor> operationBindingProcessors) {
        return operationBindingProcessors.stream()
                .map(operationBindingProcessor -> operationBindingProcessor.process(method))
                .flatMap(Optional::stream)
                .collect(Collectors.toMap(
                        ProcessedOperationBinding::type, ProcessedOperationBinding::binding, (e1, e2) -> e1));
    }

    public static Map<String, MessageBinding> processMessageBindingFromAnnotation(
            AnnotatedElement annotatedElement, List<MessageBindingProcessor> messageBindingProcessors) {
        return messageBindingProcessors.stream()
                .map(messageBindingProcessor -> messageBindingProcessor.process(annotatedElement))
                .flatMap(Optional::stream)
                .collect(Collectors.toMap(
                        ProcessedMessageBinding::type, ProcessedMessageBinding::binding, (e1, e2) -> e1));
    }

    public static void processAsyncMessageAnnotation(
            MessageObject.MessageObjectBuilder messageBuilder,
            AsyncMessage asyncMessage,
            StringValueResolver stringValueResolver) {
        String annotationMessageDescription = stringValueResolver.resolveStringValue(asyncMessage.description());
        if (StringUtils.hasText(annotationMessageDescription)) {
            annotationMessageDescription = TextUtils.trimIndent(annotationMessageDescription);
            messageBuilder.description(annotationMessageDescription);
        }

        String annotationMessageId = stringValueResolver.resolveStringValue(asyncMessage.messageId());
        if (StringUtils.hasText(annotationMessageId)) {
            messageBuilder.messageId(annotationMessageId);
        }

        String annotationName = stringValueResolver.resolveStringValue(asyncMessage.name());
        if (StringUtils.hasText(annotationName)) {
            messageBuilder.name(annotationName);
        }

        String annotationTitle = stringValueResolver.resolveStringValue(asyncMessage.title());
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
     * @param stringValueResolver the StringValueResolver to resolve placeholders
     * @return List of server names
     */
    public static List<String> getServers(AsyncOperation op, StringValueResolver stringValueResolver) {
        return Arrays.stream(op.servers())
                .map(stringValueResolver::resolveStringValue)
                .toList();
    }

    public static Map<String, ChannelBinding> processChannelBindingFromAnnotation(
            AnnotatedElement annotatedElement, List<ChannelBindingProcessor> channelBindingProcessors) {
        return channelBindingProcessors.stream()
                .map(channelBindingProcessor -> channelBindingProcessor.process(annotatedElement))
                .flatMap(Optional::stream)
                .collect(Collectors.toMap(
                        ProcessedChannelBinding::type, ProcessedChannelBinding::binding, (e1, e2) -> e1));
    }
}
