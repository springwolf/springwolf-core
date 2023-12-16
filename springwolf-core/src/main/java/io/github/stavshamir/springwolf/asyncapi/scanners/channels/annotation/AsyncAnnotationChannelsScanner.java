// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2._6_0.model.server.Server;
import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

@Slf4j
@RequiredArgsConstructor
public class AsyncAnnotationChannelsScanner<A extends Annotation>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private final AsyncAnnotationProvider<A> asyncAnnotationProvider;
    private final ClassScanner classScanner;
    private final SchemasService schemasService;
    private final AsyncApiDocketService asyncApiDocketService;
    private final PayloadClassExtractor payloadClassExtractor;
    private final List<OperationBindingProcessor> operationBindingProcessors;
    private final List<MessageBindingProcessor> messageBindingProcessors;
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Map<String, ChannelItem> scan() {
        Map<String, List<OperationData>> operationDataGroupedByChannelName = this.getOperationData().stream()
                .filter(this::allFieldsAreNonNull)
                .collect(groupingBy(OperationData::getChannelName));

        return operationDataGroupedByChannelName.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> buildChannel(entry.getValue())));
    }

    private boolean allFieldsAreNonNull(OperationData operationData) {
        boolean allNonNull = operationData.getChannelName() != null
                && operationData.getPayloadType() != null
                && operationData.getOperationBinding() != null;

        if (!allNonNull) {
            log.warn("Some data fields are null - this method will not be documented: {}", operationData);
        }

        return allNonNull;
    }

    /**
     * Creates an asyncapi {@link ChannelItem} using the given list of {@link OperationData}. Expects, that all {@link OperationData}
     * items belong to the same channel. Most properties of the resulting {@link ChannelItem} are extracted from the
     * first {@link OperationData} item in the list, assuming that all {@link OperationData} contains the same channel
     * informations.
     *
     * @param operationDataList List of all {@link OperationData} items for a single channel.
     * @return the resulting {@link ChannelItem}
     */
    private ChannelItem buildChannel(List<OperationData> operationDataList) {
        // All bindings in the group are assumed to be the same
        // AsyncApi does not support multiple bindings on a single channel
        Map<String, ? extends ChannelBinding> channelBinding =
                operationDataList.get(0).getChannelBinding();
        Map<String, ? extends OperationBinding> operationBinding =
                operationDataList.get(0).getOperationBinding();
        Map<String, Object> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        Map<String, Object> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        String operationId = operationDataList.get(0).getChannelName() + "_"
                + this.asyncAnnotationProvider.getOperationType().operationName;
        String description = operationDataList.get(0).getDescription();
        List<String> servers = operationDataList.get(0).getServers();

        if (description.isEmpty()) {
            description = "Auto-generated description";
        }

        Operation operation = Operation.builder()
                .description(description)
                .operationId(operationId)
                .message(getMessageObject(operationDataList))
                .bindings(opBinding)
                .build();

        ChannelItem.ChannelItemBuilder channelBuilder = ChannelItem.builder().bindings(chBinding);
        channelBuilder = switch (this.asyncAnnotationProvider.getOperationType()) {
            case PUBLISH -> channelBuilder.publish(operation);
            case SUBSCRIBE -> channelBuilder.subscribe(operation);};

        // Only set servers if servers are defined. Avoid setting an emtpy list
        // because this would generate empty server entries for each channel in the resulting
        // async api.
        if (servers != null && !servers.isEmpty()) {
            validateServers(servers, operationId);
            channelBuilder.servers(servers);
        }
        return channelBuilder.build();
    }

    private Object getMessageObject(List<OperationData> operationDataList) {
        Set<Message> messages =
                operationDataList.stream().map(this::buildMessage).collect(toSet());

        return toMessageObjectOrComposition(messages);
    }

    private Message buildMessage(OperationData operationData) {
        Class<?> payloadType = operationData.getPayloadType();
        String modelName = this.schemasService.register(payloadType);
        String headerModelName = this.schemasService.register(operationData.getHeaders());

        /*
         * Message information can be obtained via a @AsyncMessage annotation on the method parameter, the Payload
         * itself or via the Swagger @Schema annotation on the Payload.
         */

        var schema = payloadType.getAnnotation(Schema.class);
        String description = schema != null ? schema.description() : null;

        var builder = Message.builder()
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(description)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .bindings(operationData.getMessageBinding());

        // Retrieve the Message information obtained from the @AsyncMessage annotation. These values have higher
        // priority
        //  so if we find them, we need to override the default values.
        processAsyncMessageAnnotation(operationData.getMessage(), builder);

        return builder.build();
    }

    private void processAsyncMessageAnnotation(Message annotationMessage, Message.MessageBuilder builder) {
        if (annotationMessage != null) {
            builder.messageId(annotationMessage.getMessageId());

            var schemaFormat = annotationMessage.getSchemaFormat() != null
                    ? annotationMessage.getSchemaFormat()
                    : Message.DEFAULT_SCHEMA_FORMAT;
            builder.schemaFormat(schemaFormat);

            var annotationMessageDescription = annotationMessage.getDescription();
            if (StringUtils.hasText(annotationMessageDescription)) {
                builder.description(annotationMessageDescription);
            }

            var name = annotationMessage.getName();
            if (StringUtils.hasText(name)) {
                builder.name(name);
            }

            var title = annotationMessage.getTitle();
            if (StringUtils.hasText(title)) {
                builder.title(title);
            }
        }
    }

    protected List<OperationData> getOperationData() {
        return classScanner.scan().stream()
                .flatMap(this::getAnnotatedMethods)
                .flatMap(this::toOperationData)
                .toList();
    }

    private Stream<Method> getAnnotatedMethods(Class<?> type) {
        Class<A> annotationClass = this.asyncAnnotationProvider.getAnnotation();
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> !method.isBridge())
                .filter(method -> AnnotationUtil.findAnnotation(annotationClass, method) != null);
    }

    private Stream<OperationData> toOperationData(Method method) {
        Class<A> annotationClass = this.asyncAnnotationProvider.getAnnotation();
        log.debug("Mapping method \"{}\" to channels", method.getName());

        Map<String, OperationBinding> operationBindings =
                AsyncAnnotationScannerUtil.processOperationBindingFromAnnotation(method, operationBindingProcessors);
        Map<String, MessageBinding> messageBindings =
                AsyncAnnotationScannerUtil.processMessageBindingFromAnnotation(method, messageBindingProcessors);
        Message message = AsyncAnnotationScannerUtil.processMessageFromAnnotation(method);

        Set<A> annotations = AnnotationUtil.findAnnotations(annotationClass, method);
        return annotations.stream()
                .map(annotation -> toOperationData(method, operationBindings, messageBindings, message, annotation));
    }

    private OperationData toOperationData(
            Method method,
            Map<String, OperationBinding> operationBindings,
            Map<String, MessageBinding> messageBindings,
            Message message,
            A annotation) {
        AsyncOperation op = this.asyncAnnotationProvider.getAsyncOperation(annotation);
        Class<?> payloadType =
                op.payloadType() != Object.class ? op.payloadType() : payloadClassExtractor.extractFrom(method);
        return ConsumerData.builder() // temporarily reuse this data type
                .channelName(resolver.resolveStringValue(op.channelName()))
                .description(resolver.resolveStringValue(op.description()))
                .servers(AsyncAnnotationScannerUtil.getServers(op, resolver))
                .headers(AsyncAnnotationScannerUtil.getAsyncHeaders(op, resolver))
                .payloadType(payloadType)
                .operationBinding(operationBindings)
                .messageBinding(messageBindings)
                .message(message)
                .build();
    }

    /**
     * validates the given list of server names (for a specific operation) with the servers defined in the 'servers' part of
     * the current AsyncApi.
     *
     * @param serversFromOperation the server names defined for the current operation
     * @param operationId          operationId of the current operation - used for exception messages
     * @throws IllegalArgumentException if server from operation is not present in AsyncApi's servers definition.
     */
    void validateServers(List<String> serversFromOperation, String operationId) {
        if (!serversFromOperation.isEmpty()) {
            Map<String, Server> asyncApiServers =
                    this.asyncApiDocketService.getAsyncApiDocket().getServers();
            if (asyncApiServers == null || asyncApiServers.isEmpty()) {
                throw new IllegalArgumentException(String.format(
                        "Operation '%s' defines server refs (%s) but there are no servers defined in this AsyncAPI.",
                        operationId, serversFromOperation));
            }
            for (String server : serversFromOperation) {
                if (!asyncApiServers.containsKey(server)) {
                    throw new IllegalArgumentException(String.format(
                            "Operation '%s' defines unknown server ref '%s'. This AsyncApi defines these server(s): %s",
                            operationId, server, asyncApiServers.keySet()));
                }
            }
        }
    }

    public interface AsyncAnnotationProvider<A> {
        Class<A> getAnnotation();

        AsyncOperation getAsyncOperation(A annotation);

        OperationData.OperationType getOperationType();
    }
}
