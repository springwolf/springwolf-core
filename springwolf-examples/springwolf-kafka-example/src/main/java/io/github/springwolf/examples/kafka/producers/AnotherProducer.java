// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.producers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.examples.kafka.configuration.KafkaConfiguration;
import io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.mapping.DefaultJacksonJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME;

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
                                                        name = DEFAULT_CLASSID_FIELD_NAME,
                                                        description = "Type ID"),
                                                @AsyncOperation.Headers.Header(
                                                        name = "my_uuid_field",
                                                        description = "Event identifier",
                                                        value = {
                                                            "00000000-0000-0000-0000-000000000000",
                                                            "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"
                                                        },
                                                        type = SchemaType.STRING,
                                                        format = "uuid")
                                            })))
    @KafkaAsyncOperationBinding
    public void sendMessage(AnotherPayloadDto msg) {
        kafkaTemplate.send(KafkaConfiguration.PRODUCER_TOPIC, msg);
    }
}
