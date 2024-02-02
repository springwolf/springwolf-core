// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

import io.github.stavshamir.springwolf.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelMerger;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.bindings.EmptyChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.bindings.EmptyMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.server.Server;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.ComponentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.BindingServiceProperties;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class CloudStreamFunctionChannelsScanner implements ChannelsScanner {

    private final AsyncApiDocketService asyncApiDocketService;
    private final BeanMethodsScanner beanMethodsScanner;
    private final ComponentsService componentsService;
    private final BindingServiceProperties cloudStreamBindingsProperties;
    private final FunctionalChannelBeanBuilder functionalChannelBeanBuilder;

    @Override
    public Map<String, ChannelObject> scan() {
        Set<Method> beanMethods = beanMethodsScanner.getBeanMethods();
        return ChannelMerger.mergeChannels(beanMethods.stream()
                .map(functionalChannelBeanBuilder::fromMethodBean)
                .flatMap(Set::stream)
                .filter(this::isChannelBean)
                .map(this::toChannelEntry)
                .toList());
    }

    private boolean isChannelBean(FunctionalChannelBeanData beanData) {
        return cloudStreamBindingsProperties.getBindings().containsKey(beanData.cloudStreamBinding());
    }

    private Map.Entry<String, ChannelObject> toChannelEntry(FunctionalChannelBeanData beanData) {
        String channelName = cloudStreamBindingsProperties
                .getBindings()
                .get(beanData.cloudStreamBinding())
                .getDestination();

        ChannelObject channelItem = buildChannel(beanData);

        return Map.entry(channelName, channelItem);
    }

    private ChannelObject buildChannel(FunctionalChannelBeanData beanData) {
        Class<?> payloadType = beanData.payloadType();
        String modelName = componentsService.registerSchema(payloadType);
        String headerModelName = componentsService.registerSchema(AsyncHeaders.NOT_DOCUMENTED);

        var messagePayload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(modelName))
                .build());

        MessageObject message = MessageObject.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(messagePayload)
                .headers(MessageHeaders.of(MessageReference.toSchema(headerModelName)))
                .bindings(buildMessageBinding())
                .build();
        this.componentsService.registerMessage(message);

        Map<String, ChannelBinding> channelBinding = buildChannelBinding();
        return ChannelObject.builder()
                .bindings(channelBinding)
                .messages(Map.of(message.getName(), MessageReference.toComponentMessage(message)))
                .build();
    }

    private Map<String, MessageBinding> buildMessageBinding() {
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyMessageBinding());
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
}
