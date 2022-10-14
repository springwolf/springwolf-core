package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.Constants.ONE_OF;
import static java.util.stream.Collectors.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class ConsumerChannelScanner implements ChannelsScanner {

    private final AsyncApiDocket docket;
    private final SchemasService schemasService;

    @Override
    public Map<String, ChannelItem> scan() {
        Map<String, List<ConsumerData>> consumerDataGroupedByChannelName = docket.getConsumers().stream()
                .filter(this::allFieldsAreNonNull)
                .collect(groupingBy(ConsumerData::getChannelName));

        return consumerDataGroupedByChannelName.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> buildChannel(entry.getValue())));
    }

    private boolean allFieldsAreNonNull(ConsumerData consumerData) {
        boolean allNonNull = consumerData.getChannelName() != null
                && consumerData.getPayloadType() != null
                && consumerData.getOperationBinding() != null;

        if (!allNonNull) {
            log.warn("Some consumer data fields are null - this consumer will not be documented: {}", consumerData);
        }

        return allNonNull;
    }

    private ChannelItem buildChannel(List<ConsumerData> consumerDataList) {
        // All bindings in the group are assumed to be the same
        // AsyncApi does not support multiple bindings on a single channel
        Map<String, ? extends ChannelBinding> channelBinding = consumerDataList.get(0).getChannelBinding();
        Map<String, ? extends OperationBinding> operationBinding = consumerDataList.get(0).getOperationBinding();

        Operation operation = Operation.builder()
                .message(getMessageObject(consumerDataList))
                .bindings(operationBinding)
                .build();

        return ChannelItem.builder()
                .bindings(channelBinding)
                .publish(operation)
                .build();
    }

    private Object getMessageObject(List<ConsumerData> consumerDataList) {
        Set<Message> messages = consumerDataList.stream()
                .map(this::buildMessage)
                .collect(toSet());

        return messages.size() == 1
                ? messages.toArray()[0]
                : ImmutableMap.of(ONE_OF, messages);
    }

    private Message buildMessage(ConsumerData consumerData) {
        Class<?> payloadType = consumerData.getPayloadType();
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(consumerData.getHeaders());

        return Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                .description(consumerData.getDescription())
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .build();
    }

}
