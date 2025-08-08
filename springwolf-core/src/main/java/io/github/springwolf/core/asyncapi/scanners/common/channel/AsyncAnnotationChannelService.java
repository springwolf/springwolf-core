// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.channel;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.asyncapi.v3.model.server.ServerReference;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AsyncAnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.AsyncAnnotationOperationService;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class AsyncAnnotationChannelService<Annotation extends java.lang.annotation.Annotation> {

    private final AsyncAnnotationProvider<Annotation> asyncAnnotationProvider;
    private final AsyncAnnotationOperationService<Annotation> asyncAnnotationOperationService;
    private final AsyncAnnotationMessageService asyncAnnotationMessageService;
    private final List<ChannelBindingProcessor> channelBindingProcessors;
    private final StringValueResolver stringValueResolver;
    private final AsyncApiDocketService asyncApiDocketService;

    public ChannelObject buildChannel(MethodAndAnnotation<Annotation> methodAndAnnotation) {
        AsyncOperation operationAnnotation =
                this.asyncAnnotationProvider.getAsyncOperation(methodAndAnnotation.annotation());

        ChannelObject.ChannelObjectBuilder channelBuilder = ChannelObject.builder();
        List<String> servers = AsyncAnnotationUtil.getServers(operationAnnotation, stringValueResolver);
        if (servers != null && !servers.isEmpty()) {
            Operation operation = asyncAnnotationOperationService.buildOperation(
                    operationAnnotation, Set.of(methodAndAnnotation.method()));
            validateServers(servers, operation.getTitle());

            channelBuilder.servers(
                    servers.stream().map(ServerReference::fromServer).toList());
        }

        String channelName = stringValueResolver.resolveStringValue(operationAnnotation.channelName());
        String channelId = ReferenceUtil.toValidId(channelName);
        MessageObject message =
                asyncAnnotationMessageService.buildMessage(operationAnnotation, methodAndAnnotation.method());
        Map<String, ChannelBinding> channelBindings = AsyncAnnotationUtil.processChannelBindingFromAnnotation(
                methodAndAnnotation.method(), channelBindingProcessors);

        ChannelObject channel = channelBuilder
                .channelId(channelId)
                .address(channelName)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .bindings(channelBindings)
                .build();
        return channel;
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
