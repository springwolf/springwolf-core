// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.asyncapi.v3.model.server.ServerReference;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationMethodLevelScanner;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AsyncAnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class AsyncAnnotationMethodLevelChannelsScanner<A extends Annotation>
        extends AsyncAnnotationMethodLevelScanner<A> implements ChannelsInClassScanner {

    private final AsyncApiDocketService asyncApiDocketService;

    public AsyncAnnotationMethodLevelChannelsScanner(
            AsyncAnnotationProvider<A> asyncAnnotationProvider,
            ComponentsService componentsService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadAsyncOperationService payloadAsyncOperationService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors,
            StringValueResolverProxy resolver) {
        super(
                asyncAnnotationProvider,
                payloadAsyncOperationService,
                componentsService,
                operationBindingProcessors,
                messageBindingProcessors,
                resolver);
        this.asyncApiDocketService = asyncApiDocketService;
    }

    @Override
    public Stream<Map.Entry<String, ChannelObject>> scan(Class<?> clazz) {
        return this.getAnnotatedMethods(clazz).map(this::buildChannel);
    }

    private Map.Entry<String, ChannelObject> buildChannel(
            AnnotationScannerUtil.MethodAndAnnotation<A> methodAndAnnotation) {
        ChannelObject.ChannelObjectBuilder channelBuilder = ChannelObject.builder();

        AsyncOperation operationAnnotation =
                this.asyncAnnotationProvider.getAsyncOperation(methodAndAnnotation.annotation());
        String channelName = resolver.resolveStringValue(operationAnnotation.channelName());
        String channelId = ReferenceUtil.toValidId(channelName);

        Operation operation = buildOperation(operationAnnotation, methodAndAnnotation.method(), channelId);

        List<String> servers = AsyncAnnotationUtil.getServers(operationAnnotation, resolver);
        if (servers != null && !servers.isEmpty()) {
            validateServers(servers, operation.getTitle());
            channelBuilder.servers(
                    servers.stream().map(ServerReference::fromServer).toList());
        }
        MessageObject message = buildMessage(operationAnnotation, methodAndAnnotation.method());

        ChannelObject channelItem = channelBuilder
                .channelId(channelId)
                .address(channelName)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .build();
        return Map.entry(channelItem.getChannelId(), channelItem);
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
}
