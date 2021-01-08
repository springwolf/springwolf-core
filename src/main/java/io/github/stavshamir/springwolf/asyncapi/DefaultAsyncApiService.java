package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.types.Components;
import io.github.stavshamir.springwolf.asyncapi.types.channel.Channel;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAsyncApiService implements AsyncApiService {

    private final AsyncApiDocket docket;
    private final ChannelsService channelsService;
    private final SchemasService schemasService;

    private AsyncAPI asyncAPI;

    @PostConstruct
    void buildAsyncApi() {
        log.debug("Building AsyncAPI document");

        // Find for producer channels including also the consumers.
        Map<String, Channel> allChannels = scanForProducers();
        allChannels.putAll(channelsService.getChannels());

        Components components = Components.builder()
                .schemas(schemasService.getDefinitions())
                .build();

        asyncAPI = AsyncAPI.builder()
                .info(docket.getInfo())
                .servers(docket.getServers())
                //.channels(channelsService.getChannels())
                .channels(allChannels)
                .components(components)
                .build();
    }

    @Override
    public AsyncAPI getAsyncAPI() {
        return asyncAPI;
    }

    private Map<String, Channel> scanForProducers() {
        Map<String, Channel> map = new HashMap<>();

        docket.getProducers().stream()
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
