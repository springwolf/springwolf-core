// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.producers;

import io.github.springwolf.examples.kafka.configuration.KafkaConfiguration;
import io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AnotherProducer {

    @Autowired
    private KafkaTemplate<String, AnotherPayloadDto> kafkaTemplate;

    public void sendMessage(AnotherPayloadDto msg) {
        kafkaTemplate.send(KafkaConfiguration.PRODUCER_TOPIC, msg);
    }
}
