package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
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

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static java.util.stream.Collectors.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProducerChannelScanner implements ChannelsScanner {

    private final AsyncApiDocket docket;
    private final SchemasService schemasService;

    @Override
    public Map<String, ChannelItem> scan() {
        Map<String, List<ProducerData>> producerDataGroupedByChannelName = docket.getProducers().stream()
                .filter(this::allFieldsAreNonNull)
                .collect(groupingBy(ProducerData::getChannelName));

        return producerDataGroupedByChannelName.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> buildChannel(entry.getValue())));
    }

    private boolean allFieldsAreNonNull(ProducerData producerData) {
        boolean allNonNull = producerData.getChannelName() != null
                && producerData.getPayloadType() != null
                && producerData.getOperationBinding() != null;

        if (!allNonNull) {
            log.warn("Some producer data fields are null - this producer will not be documented: {}", producerData);
        }

        return allNonNull;
    }

    private ChannelItem buildChannel(List<ProducerData> producerDataList) {
        // All bindings in the group are assumed to be the same
        // AsyncApi does not support multiple bindings on a single channel
        Map<String, ? extends ChannelBinding> channelBinding = producerDataList.get(0).getChannelBinding();
        Map<String, ? extends OperationBinding> operationBinding = producerDataList.get(0).getOperationBinding();
        String operationId = producerDataList.get(0).getChannelName() + "_subscribe";

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId(operationId)
                .message(getMessageObject(producerDataList))
                .bindings(operationBinding)
                .build();

        return ChannelItem.builder()
                .bindings(channelBinding)
                .subscribe(operation)
                .build();
    }

    private Object getMessageObject(List<ProducerData> producerDataList) {
        Set<Message> messages = producerDataList.stream()
                .map(this::buildMessage)
                .collect(toSet());

        return toMessageObjectOrComposition(messages);
    }

    private Message buildMessage(ProducerData producerData) {
        Class<?> payloadType = producerData.getPayloadType();
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(producerData.getHeaders());

        return Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                .description(producerData.getDescription())
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .build();
    }

}
