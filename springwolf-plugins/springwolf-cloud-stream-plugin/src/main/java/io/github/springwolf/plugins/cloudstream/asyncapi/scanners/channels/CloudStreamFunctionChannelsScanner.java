// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.EmptyChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.EmptyMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelMerger;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AsyncAnnotationUtil;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanBuilder;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.BindingServiceProperties;

import java.lang.reflect.AnnotatedElement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class CloudStreamFunctionChannelsScanner implements ChannelsScanner {

    private final AsyncApiDocketService asyncApiDocketService;
    private final BeanMethodsScanner beanMethodsScanner;
    private final ComponentClassScanner componentClassScanner;
    private final ComponentsService componentsService;
    private final BindingServiceProperties cloudStreamBindingsProperties;
    private final FunctionalChannelBeanBuilder functionalChannelBeanBuilder;
    protected final List<ChannelBindingProcessor> channelBindingProcessors;
    protected final List<MessageBindingProcessor> messageBindingProcessors;

    @Override
    public Map<String, ChannelObject> scan() {
        Set<AnnotatedElement> elements = new HashSet<>();
        elements.addAll(componentClassScanner.scan());
        elements.addAll(beanMethodsScanner.getBeanMethods());

        List<Map.Entry<String, ChannelObject>> channels = elements.stream()
                .map(functionalChannelBeanBuilder::build)
                .flatMap(Set::stream)
                .filter(this::isChannelBean)
                .map(this::toChannelEntry)
                .toList();

        return ChannelMerger.mergeChannels(channels);
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
        String headerModelName = componentsService.registerSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED);

        var messagePayload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(modelName))
                .build());

        Map<String, MessageBinding> messageBinding = buildMessageBinding(beanData.annotatedElement());
        MessageObject message = MessageObject.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(messagePayload)
                .headers(MessageHeaders.of(MessageReference.toSchema(headerModelName)))
                .bindings(messageBinding)
                .build();
        this.componentsService.registerMessage(message);

        Map<String, ChannelBinding> channelBinding = buildChannelBinding(beanData.annotatedElement());
        return ChannelObject.builder()
                .bindings(channelBinding)
                .messages(Map.of(message.getName(), MessageReference.toComponentMessage(message)))
                .build();
    }

    private Map<String, MessageBinding> buildMessageBinding(AnnotatedElement annotatedElement) {
        Map<String, MessageBinding> messageBindingMap =
                AsyncAnnotationUtil.processMessageBindingFromAnnotation(annotatedElement, messageBindingProcessors);
        if (!messageBindingMap.isEmpty()) {
            return messageBindingMap;
        }
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyMessageBinding());
    }

    private Map<String, ChannelBinding> buildChannelBinding(AnnotatedElement annotatedElement) {
        Map<String, ChannelBinding> channelBindingMap =
                AsyncAnnotationUtil.processChannelBindingFromAnnotation(annotatedElement, channelBindingProcessors);
        if (!channelBindingMap.isEmpty()) {
            return channelBindingMap;
        }

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
