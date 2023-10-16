// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2._6_0.model.server.Server;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SpringPayloadAnnotationTypeExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.AbstractOperationDataScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.lang.NonNull;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class AsyncListenerAnnotationScanner extends AbstractOperationDataScanner implements EmbeddedValueResolverAware {
    private StringValueResolver resolver;
    private final ComponentClassScanner componentClassScanner;
    private final SchemasService schemasService;
    private final AsyncApiDocketService asyncApiDocketService;

    private final List<OperationBindingProcessor> operationBindingProcessors;

    private final List<MessageBindingProcessor> messageBindingProcessors;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected SchemasService getSchemaService() {
        return this.schemasService;
    }

    @Override
    protected List<OperationData> getOperationData() {
        return componentClassScanner.scan().stream()
                .flatMap(this::getAnnotatedMethods)
                .flatMap(this::toOperationData)
                .collect(Collectors.toList());
    }

    private Stream<Method> getAnnotatedMethods(Class<?> type) {
        Class<AsyncListener> annotationClass = AsyncListener.class;
        Class<AsyncListeners> annotationClassRepeatable = AsyncListeners.class;
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass)
                        || method.isAnnotationPresent(annotationClassRepeatable));
    }

    private Stream<OperationData> toOperationData(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        Map<String, OperationBinding> operationBindings =
                AsyncAnnotationScannerUtil.processOperationBindingFromAnnotation(method, operationBindingProcessors);
        Map<String, MessageBinding> messageBindings =
                AsyncAnnotationScannerUtil.processMessageBindingFromAnnotation(method, messageBindingProcessors);
        Message message = AsyncAnnotationScannerUtil.processMessageFromAnnotation(method);

        Class<AsyncListener> annotationClass = AsyncListener.class;
        return Arrays.stream(method.getAnnotationsByType(annotationClass))
                .map(annotation -> toConsumerData(method, operationBindings, messageBindings, message, annotation));
    }

    private ConsumerData toConsumerData(
            Method method,
            Map<String, OperationBinding> operationBindings,
            Map<String, MessageBinding> messageBindings,
            Message message,
            AsyncListener annotation) {
        AsyncOperation op = annotation.operation();
        Class<?> payloadType = op.payloadType() != Object.class
                ? op.payloadType()
                : SpringPayloadAnnotationTypeExtractor.getPayloadType(method);

        String channelName = resolver.resolveStringValue(op.channelName());
        List<String> servers = AsyncAnnotationScannerUtil.getServers(op, resolver);

        validateServers(servers, channelName);

        return ConsumerData.builder()
                .channelName(channelName)
                .description(resolver.resolveStringValue(op.description()))
                .servers(servers)
                .headers(AsyncAnnotationScannerUtil.getAsyncHeaders(op, resolver))
                .payloadType(payloadType)
                .operationBinding(operationBindings)
                .messageBinding(messageBindings)
                .message(message)
                .build();
    }

    /**
     * retrieves the 'servers' parameter from the given {@link AsyncOperation} and validates the servers list
     * with the defined servers in the current AsyncApi.
     *
     * @param op          the AsyncOperation which provides the servers for the current operation
     * @param channelname resolved channel name for this AsyncOperation - used for exception messages
     * @return List of server names, may be empty.
     * @throws IllegalArgumentException if server from {@link AsyncOperation} is not present in AsyncApi's servers definition.
     */
    @NonNull
    private void validateServers(List<String> serversFromAnnotation, String channelname) {
        if (!serversFromAnnotation.isEmpty()) {
            Map<String, Server> asyncApiServers =
                    asyncApiDocketService.getAsyncApiDocket().getServers();
            if (asyncApiServers == null || asyncApiServers.isEmpty()) {
                throw new IllegalArgumentException(String.format(
                        "Channel '%s' defines server refs (%s) but there are no servers defined in this AsyncAPI.",
                        channelname, serversFromAnnotation));
            }
            for (String server : serversFromAnnotation) {
                if (!asyncApiServers.containsKey(server)) {
                    throw new IllegalArgumentException(String.format(
                            "Channel '%s' defines unknown server ref '%s'. This AsyncApi defines these server(s): %s",
                            channelname, server, asyncApiServers.keySet()));
                }
            }
        }
    }

    @Override
    protected OperationData.OperationType getOperationType() {
        return OperationData.OperationType.PUBLISH;
    }
}
