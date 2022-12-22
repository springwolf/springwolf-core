package io.github.stavshamir.springwolf.example.producers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static io.github.stavshamir.springwolf.example.configuration.KafkaConfiguration.PRODUCER_TOPIC;
import static org.springframework.kafka.support.mapping.AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME;

@Component
public class ExampleProducer {

    @Autowired
    private KafkaTemplate<String, ExamplePayloadDto> kafkaTemplate;

    @AsyncPublisher(operation = @AsyncOperation(
            channelName = PRODUCER_TOPIC,
            description = "Custom, optional description defined in the AsyncPublisher annotation",
            headers = @AsyncOperation.Headers(
                    schemaName = "SpringKafkaDefaultHeaders",
                    values = {
                            @AsyncOperation.Headers.Header(
                                    name = DEFAULT_CLASSID_FIELD_NAME,
                                    description = "Spring Type Id Header",
                                    value = "io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto"
                            ),
                            @AsyncOperation.Headers.Header(
                                    name = DEFAULT_CLASSID_FIELD_NAME,
                                    description = "Spring Type Id Header",
                                    value = "io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto"
                            ),
                    }
            )
    ))
    @KafkaAsyncOperationBinding(
            bindingVersion = "1",
            clientId = "foo-clientId"
    )
    public void sendMessage(ExamplePayloadDto msg) {
        kafkaTemplate.send(PRODUCER_TOPIC, msg);
    }

}
