// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.producers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding.KafkaAsyncKey;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding.KafkaAsyncMessageBinding;
import io.github.stavshamir.springwolf.example.kafka.configuration.KafkaConfiguration;
import io.github.stavshamir.springwolf.example.kafka.dtos.NestedPayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.mapping.AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME;

@Component
public class NestedProducer {

    @Autowired
    private KafkaTemplate<String, NestedPayloadDto> kafkaTemplate;

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = "topic-defined-via-asyncPublisher-annotation",
                            description = "Custom, optional description defined in the AsyncPublisher annotation",
                            headers =
                                    @AsyncOperation.Headers(
                                            schemaName = "SpringKafkaDefaultHeaders",
                                            values = {
                                                @AsyncOperation.Headers.Header(
                                                        name = DEFAULT_CLASSID_FIELD_NAME,
                                                        description = "Spring Type Id Header",
                                                        value =
                                                                "io.github.stavshamir.springwolf.example.kafka.dtos.NestedPayloadDto"),
                                            })))
    @KafkaAsyncOperationBinding(
            clientId = "foo-clientId",
            messageBinding =
                    @KafkaAsyncMessageBinding(
                            key = @KafkaAsyncKey(description = "Kafka Producer Message Key", example = "example-key")))
    public void sendMessage(NestedPayloadDto msg) {
        kafkaTemplate.send(KafkaConfiguration.PRODUCER_TOPIC, msg);
    }
}
