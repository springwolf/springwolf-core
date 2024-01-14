// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

import io.github.stavshamir.springwolf.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelMerger;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.bindings.EmptyChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.EmptyOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.bindings.EmptyMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.server.Server;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.BindingServiceProperties;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CloudStreamFunctionChannelsScanner implements ChannelsScanner {

    private final AsyncApiDocketService asyncApiDocketService;
    private final BeanMethodsScanner beanMethodsScanner;
    private final SchemasService schemasService;
    private final BindingServiceProperties cloudStreamBindingsProperties;
    private final FunctionalChannelBeanBuilder functionalChannelBeanBuilder;

    @Override
    public Map<String, ChannelObject> scanChannels() {
        Set<Method> beanMethods = beanMethodsScanner.getBeanMethods();
        return ChannelMerger.mergeChannels(beanMethods.stream()
                .map(functionalChannelBeanBuilder::fromMethodBean)
                .flatMap(Set::stream)
                .filter(this::isChannelBean)
                .map(this::toChannelEntry)
                .collect(Collectors.toList()));
    }

    @Override
    public Map<String, Operation> scanOperations() {
        // FIXME
        return Map.of();
    }

    private boolean isChannelBean(FunctionalChannelBeanData beanData) {
        return cloudStreamBindingsProperties.getBindings().containsKey(beanData.cloudStreamBinding());
    }

    private Map.Entry<String, ChannelObject> toChannelEntry(FunctionalChannelBeanData beanData) {
        String channelName = cloudStreamBindingsProperties
                .getBindings()
                .get(beanData.cloudStreamBinding())
                .getDestination();

        String operationId = buildOperationId(beanData, channelName);
        ChannelObject channelItem = buildChannel(beanData, operationId);

        return Map.entry(channelName, channelItem);
    }

    private ChannelObject buildChannel(FunctionalChannelBeanData beanData, String operationId) {
        Class<?> payloadType = beanData.payloadType();
        String modelName = schemasService.registerSchema(payloadType);
        String headerModelName = schemasService.registerSchema(AsyncHeaders.NOT_DOCUMENTED);

        MessageObject message = MessageObject.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(MessagePayload.of(MessageReference.toSchema(modelName)))
                .headers(MessageHeaders.of(MessageReference.toSchema(headerModelName)))
                .bindings(buildMessageBinding())
                .build();

        // FIXME
        //        Operation operation = Operation.builder()
        //                .description("Auto-generated description")
        //                // .operationId(operationId) FIXME?
        //                .messages(List.of(message))
        //                .bindings(buildOperationBinding())
        //                .build();

        Map<String, ChannelBinding> channelBinding = buildChannelBinding();
        return beanData.beanType() == FunctionalChannelBeanData.BeanType.CONSUMER
                ? ChannelObject.builder()
                .bindings(channelBinding)
                // .publish(operation) FIXME
                .build()
                : ChannelObject.builder()
                .bindings(channelBinding)
                // .subscribe(operation) FIXME
                .build();
    }

    private Map<String, MessageBinding> buildMessageBinding() {
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyMessageBinding());
    }

    private Map<String, OperationBinding> buildOperationBinding() {
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyOperationBinding());
    }

    private Map<String, ChannelBinding> buildChannelBinding() {
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyChannelBinding());
    }

    private String getProtocolName() {
        AsyncApiDocket docket = asyncApiDocketService.getAsyncApiDocket();
        if (docket.getServers().size() > 1) {
            log.warn(
                    "More than one server has been defined - the channels protocol will be determined by the first one");
        }

        return docket.getServers().entrySet().stream()
                .findFirst()
                .map(Map.Entry::getValue)
                .map(Server::getProtocol)
                .orElseThrow(() ->
                        new IllegalStateException("There must be at least one server define in the AsyncApiDocker"));
    }

    private String buildOperationId(FunctionalChannelBeanData beanData, String channelName) {
        String operationName =
                beanData.beanType() == FunctionalChannelBeanData.BeanType.CONSUMER ? "publish" : "subscribe";

        return String.format("%s_%s_%s", channelName, operationName, beanData.beanName());
    }
}
