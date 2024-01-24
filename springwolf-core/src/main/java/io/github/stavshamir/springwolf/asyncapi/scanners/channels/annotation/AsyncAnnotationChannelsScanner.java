// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelMerger;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ServerReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.server.Server;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

@Slf4j
public class AsyncAnnotationChannelsScanner<A extends Annotation> extends AsyncAnnotationScanner<A>
        implements ChannelsScanner {

    private final ClassScanner classScanner;
    private final AsyncApiDocketService asyncApiDocketService;

    public AsyncAnnotationChannelsScanner(
            AsyncAnnotationProvider<A> asyncAnnotationProvider,
            ClassScanner classScanner,
            SchemasService schemasService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadClassExtractor payloadClassExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        super(
                asyncAnnotationProvider,
                payloadClassExtractor,
                schemasService,
                operationBindingProcessors,
                messageBindingProcessors);
        this.classScanner = classScanner;
        this.asyncApiDocketService = asyncApiDocketService;
    }

    @Override
    public Map<String, ChannelObject> scan() {
        List<Map.Entry<String, ChannelObject>> channels = classScanner.scan().stream()
                .flatMap(this::getAnnotatedMethods)
                .map(this::buildChannel)
                .toList();

        return ChannelMerger.mergeChannels(channels);
    }

    private Map.Entry<String, ChannelObject> buildChannel(MethodAndAnnotation<A> methodAndAnnotation) {
        ChannelObject.ChannelObjectBuilder channelBuilder = ChannelObject.builder();

        AsyncOperation operationAnnotation =
                this.asyncAnnotationProvider.getAsyncOperation(methodAndAnnotation.annotation());
        String channelName = resolver.resolveStringValue(operationAnnotation.channelName());

        Operation operation = buildOperation(operationAnnotation, methodAndAnnotation.method(), channelName);

        List<String> servers = AsyncAnnotationScannerUtil.getServers(operationAnnotation, resolver);
        if (servers != null && !servers.isEmpty()) {
            validateServers(servers, operation.getTitle());
            channelBuilder.servers(servers.stream()
                    .map(it -> ServerReference.builder().ref(it).build())
                    .toList());
        }
        MessageObject message = buildMessage(operationAnnotation, methodAndAnnotation.method());

        ChannelObject channelItem = channelBuilder
                .messages(Map.of(message.getName(), MessageReference.toComponentMessage(message)))
                .build();
        return Map.entry(channelName, channelItem);
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
