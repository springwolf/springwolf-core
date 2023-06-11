package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2._6_0.model.server.Server;
import com.asyncapi.v2.binding.message.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.bindings.EmptyChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.EmptyOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.bindings.EmptyMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;


@Slf4j
@Service
@RequiredArgsConstructor
public class CloudStreamFunctionChannelsScanner implements ChannelsScanner {

    private final AsyncApiDocketService asyncApiDocketService;
    private final DefaultBeanMethodsScanner beanMethodsScanner;
    private final SchemasService schemasService;
    private final BindingServiceProperties cloudStreamBindingsProperties;

    @Override
    public Map<String, ChannelItem> scan() {
        Set<Method> beanMethods = beanMethodsScanner.getBeanMethods();
        return beanMethods.stream()
                .map(FunctionalChannelBeanData::fromMethodBean)
                .flatMap(Set::stream)
                .filter(this::isChannelBean)
                .map(this::toChannelEntry)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean isChannelBean(FunctionalChannelBeanData beanData) {
        return cloudStreamBindingsProperties
                .getBindings()
                .containsKey(beanData.getCloudStreamBinding());
    }

    private Map.Entry<String, ChannelItem> toChannelEntry(FunctionalChannelBeanData beanData) {
        String channelName = cloudStreamBindingsProperties
                .getBindings()
                .get(beanData.getCloudStreamBinding())
                .getDestination();

        String operationId = buildOperationId(beanData, channelName);
        ChannelItem channelItem = buildChannel(beanData, operationId);

        return Map.entry(channelName, channelItem);
    }

    private ChannelItem buildChannel(FunctionalChannelBeanData beanData, String operationId) {
        String modelName = schemasService.register(beanData.getPayloadType());
        String headerModelName = schemasService.register(AsyncHeaders.NOT_DOCUMENTED);

        Message message = Message.builder()
                .name(beanData.getPayloadType().getName())
                .title(modelName)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .bindings(buildMessageBinding())
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId(operationId)
                .message(message)
                .bindings(buildOperationBinding())
                .build();

        Map<String, Object> channelBinding = buildChannelBinding();
        return beanData.getBeanType() == FunctionalChannelBeanData.BeanType.CONSUMER
                ? ChannelItem.builder().bindings(channelBinding).publish(operation).build()
                : ChannelItem.builder().bindings(channelBinding).subscribe(operation).build();
    }

    private Map<String, ? extends MessageBinding> buildMessageBinding() {
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyMessageBinding());
    }

    private Map<String, Object> buildOperationBinding() {
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyOperationBinding());
    }

    private Map<String, Object> buildChannelBinding() {
        String protocolName = getProtocolName();
        return Map.of(protocolName, new EmptyChannelBinding());
    }

    private String getProtocolName() {
        AsyncApiDocket docket = asyncApiDocketService.getAsyncApiDocket();
        if (docket.getServers().size() > 1) {
            log.warn("More than one server has been defined - the channels protocol will be determined by the first one");
        }

        return docket.getServers().entrySet().stream().findFirst()
                .map(Map.Entry::getValue)
                .map(Server::getProtocol)
                .orElseThrow(() -> new IllegalStateException("There must be at least one server define in the AsyncApiDocker"));
    }

    private String buildOperationId(FunctionalChannelBeanData beanData, String channelName) {
        String operationName = beanData.getBeanType() == FunctionalChannelBeanData.BeanType.CONSUMER
                ? "publish"
                : "subscribe";

        return String.format("%s_%s_%s", channelName, operationName, beanData.getBeanName());
    }

}
