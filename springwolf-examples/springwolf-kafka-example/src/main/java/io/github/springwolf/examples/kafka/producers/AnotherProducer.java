// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.producers;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.examples.kafka.configuration.KafkaConfiguration;
import io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AnotherProducer {

    @Autowired
    private KafkaTemplate<String, AnotherPayloadDto> kafkaTemplate;

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = "another-topic",
                            headers =
                                    @AsyncOperation.Headers(
                                            schemaName = "SpringKafkaDefaultHeaders-AnotherTopic",
                                            values = {
                                                @AsyncOperation.Headers.Header(
                                                        name = "kafka_messageKey",
                                                        description = "Message key",
                                                        format = "string"),
                                                @AsyncOperation.Headers.Header(
                                                        name = "__TypeId__",
                                                        description = "Type ID",
                                                        format = "string"),
                                                @AsyncOperation.Headers.Header(
                                                        name = "my_key",
                                                        description = "my_key",
                                                        format = "int32")
                                            })))
    public void sendMessage(AnotherPayloadDto msg) {
        kafkaTemplate.send(KafkaConfiguration.PRODUCER_TOPIC, msg);
    }
}
