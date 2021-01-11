package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.types.channel.Channel;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProducerChannelScanner implements ChannelsScanner {

    private final AsyncApiDocket docket;
    private final SchemasService schemasService;

    @Override
    public Map<String, Channel> scan() {
        Map<String, Channel> map = new HashMap<>();
        docket.getProducers()
              .forEach(pd -> map.put(pd.getChannelName(), buildChannel(pd.getPayloadType(), pd.getBinding())));
        return map;
    }
    private Channel buildChannel(Class<?> payloadType, Map<String, ? extends OperationBinding> operationBinding) {
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

        return Channel.builder()
                      .publish(operation)
                      .build();
    }
}