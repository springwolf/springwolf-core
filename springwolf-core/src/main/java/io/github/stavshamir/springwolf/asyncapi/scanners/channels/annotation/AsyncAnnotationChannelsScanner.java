// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelMerger;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ServerReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.server.Server;
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
import java.util.stream.Stream;

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
    public Map<String, ChannelObject> scanChannels() {
        List<Map.Entry<String, ChannelObject>> channels = classScanner.scan().stream()
                .flatMap(this::getAnnotatedMethods)
                .map(this::buildChannel)
                .filter(this::isInvalidChannel)
                .toList();

        return ChannelMerger.mergeChannels(channels);
    }

    @Override
    public Map<String, Operation> scanOperations() {
        List<Map.Entry<String, Operation>> operations = classScanner.scan().stream()
                .flatMap(this::getAnnotatedMethods)
                .map(this::buildOperation)
                .filter(this::isInvalidOperation)
                .toList();

        return ChannelMerger.mergeOperations(operations);
    }

    private Stream<MethodAndAnnotation<A>> getAnnotatedMethods(Class<?> type) {
        Class<A> annotationClass = this.asyncAnnotationProvider.getAnnotation();
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> !method.isBridge())
                .filter(method -> AnnotationUtil.findAnnotation(annotationClass, method) != null)
                .peek(method -> log.debug("Mapping method \"{}\" to channels", method.getName()))
                .flatMap(method -> AnnotationUtil.findAnnotations(annotationClass, method).stream()
                        .map(annotation -> new MethodAndAnnotation<>(method, annotation)));
    }

    private boolean isInvalidChannel(Map.Entry<String, ChannelObject> entry) {
        //        Operation publish = entry.getValue().getPublish();
        //        boolean publishBindingExists = publish != null && publish.getBindings() != null;
        //
        //        Operation subscribe = entry.getValue().getSubscribe();
        //        boolean subscribeBindingExists = subscribe != null && subscribe.getBindings() != null;

        boolean allNonNull = entry.getKey() != null; // && (publishBindingExists || subscribeBindingExists); FIXME

        if (!allNonNull) {
            log.warn(
                    "Some data fields are null - method (channel={}) will not be documented: {}",
                    entry.getKey(),
                    entry.getValue());
        }

        return allNonNull;
    }

    private boolean isInvalidOperation(Map.Entry<String, Operation> entry) {
        //        Operation publish = entry.getValue().getPublish();
        //        boolean publishBindingExists = publish != null && publish.getBindings() != null;
        //
        //        Operation subscribe = entry.getValue().getSubscribe();
        //        boolean subscribeBindingExists = subscribe != null && subscribe.getBindings() != null;

        boolean allNonNull = entry.getKey() != null; // && (publishBindingExists || subscribeBindingExists); FIXME

        if (!allNonNull) {
            log.warn(
                    "Some data fields are null - method (channel={}) will not be documented: {}",
                    entry.getKey(),
                    entry.getValue());
        }

        return allNonNull;
    }

    private Map.Entry<String, ChannelObject> buildChannel(MethodAndAnnotation<A> methodAndAnnotation) {
        ChannelObject.ChannelObjectBuilder channelBuilder = ChannelObject.builder();

        AsyncOperation operationAnnotation =
                this.asyncAnnotationProvider.getAsyncOperation(methodAndAnnotation.annotation());
        String channelName = resolver.resolveStringValue(operationAnnotation.channelName());

        Operation operation = buildOperation(operationAnnotation, methodAndAnnotation.method(), channelName);

        List<String> servers = AsyncAnnotationScannerUtil.getServers(operationAnnotation, resolver);
        if (servers != null && !servers.isEmpty()) {
            // FIXME: It was originally operationId, which doesn't exist anymore
            validateServers(servers, operation.getTitle());
            channelBuilder.servers(servers.stream()
                    .map(it -> ServerReference.builder().ref(it).build())
                    .toList());
        }
        MessageObject message = buildMessage(operationAnnotation, methodAndAnnotation.method());

        ChannelObject channelItem = channelBuilder
                .messages(Map.of(message.getName(), MessageReference.fromMessage(message)))
                .build();
        return Map.entry(channelName, channelItem);
    }

    private Map.Entry<String, Operation> buildOperation(MethodAndAnnotation<A> methodAndAnnotation) {
        Operation.OperationBuilder operationBuilder = Operation.builder();
        AsyncOperation operationAnnotation =
                this.asyncAnnotationProvider.getAsyncOperation(methodAndAnnotation.annotation());
        String operationName = resolver.resolveStringValue(operationAnnotation.channelName());

        Operation operation = buildOperation(operationAnnotation, methodAndAnnotation.method(), operationName);
        switch (this.asyncAnnotationProvider.getOperationType()) {
            case PUBLISH -> operation.setAction(OperationAction.RECEIVE);
            case SUBSCRIBE -> operation.setAction(OperationAction.SEND);
        }

        MessageObject message = buildMessage(operationAnnotation, methodAndAnnotation.method());

        // FIXME
        // Operation operation =
        //     operationBuilder.messages(Map.of(message.getMessageId(), message)).build();
        return Map.entry(operationName, operation);
    }

    private Operation buildOperation(AsyncOperation asyncOperation, Method method, String channelName) {
        String description = this.resolver.resolveStringValue(asyncOperation.description());
        if (!StringUtils.hasText(description)) {
            description = "Auto-generated description";
        }

        String operationTitle = channelName + "_" + this.asyncAnnotationProvider.getOperationType().operationName;

        Map<String, OperationBinding> operationBinding =
                AsyncAnnotationScannerUtil.processOperationBindingFromAnnotation(method, operationBindingProcessors);
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        MessageObject message = buildMessage(asyncOperation, method);

        return Operation.builder()
                .channel(ChannelReference.fromChannel(channelName))
                .description(description)
                .title(operationTitle)
                .messages(List.of(MessageReference.fromMessage(message)))
                .bindings(opBinding)
                .build();
    }

    private MessageObject buildMessage(AsyncOperation operationData, Method method) {
        Class<?> payloadType = operationData.payloadType() != Object.class
                ? operationData.payloadType()
                : payloadClassExtractor.extractFrom(method);

        String modelName = this.schemasService.register(payloadType);
        AsyncHeaders asyncHeaders = AsyncAnnotationScannerUtil.getAsyncHeaders(operationData, resolver);
        String headerModelName = this.schemasService.register(asyncHeaders);
        var headers = MessageHeaders.of(MessageReference.fromSchema(headerModelName));

        var schema = payloadType.getAnnotation(Schema.class);
        String description = schema != null ? schema.description() : null;

        Map<String, MessageBinding> messageBinding =
                AsyncAnnotationScannerUtil.processMessageBindingFromAnnotation(method, messageBindingProcessors);

        var messagePayload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(modelName))
                .build());

        var builder = MessageObject.builder()
                .messageId(payloadType.getName())
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(description)
                .payload(messagePayload)
                .headers(headers)
                .bindings(messageBinding);

        // Retrieve the Message information obtained from the @AsyncMessage annotation. These values have higher
        // priority so if we find them, we need to override the default values.
        AsyncAnnotationScannerUtil.processAsyncMessageAnnotation(builder, operationData.message(), this.resolver);

        MessageObject message = builder.build();
        this.schemasService.registerMessage(message);
        return message;
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

    private record MethodAndAnnotation<A>(Method method, A annotation) {}
}
