package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProducerChannelScanner implements ChannelsScanner {

    private final AsyncApiDocket docket;
    private final SchemasService schemasService;

    @Override
    public Map<String, ChannelItem> scan() {
        return docket.getProducers().stream()
                .filter(this::allFieldsAreNonNull)
                .collect(toMap(ProducerData::getChannelName, this::buildChannel));
    }

    private boolean allFieldsAreNonNull(ProducerData producerData) {
        boolean allNonNull = producerData.getChannelName() != null
                && producerData.getPayloadType() != null
                && producerData.getBinding() != null;

        if (!allNonNull) {
            log.warn("Some producer data fields are null - this producer will not be documented: {}", producerData);
        }

        return allNonNull;
    }

    private ChannelItem buildChannel(ProducerData producerData) {
        Class<?> payloadType = producerData.getPayloadType();
        Map<String, ? extends OperationBinding> operationBinding = producerData.getBinding();

        String modelName = schemasService.register(payloadType);

        Message message = Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(PayloadReference.fromModelName(modelName))
                .build();

        Operation operation = Operation.builder()
                .message(message)
                .bindings(operationBinding)
                .build();

        return ChannelItem.builder()
                .subscribe(operation)
                .build();
    }

}