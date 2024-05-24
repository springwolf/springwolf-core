// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DiscriminatorConsumer {

    @KafkaListener(topics = "vehicle-topic")
    public void receiveExamplePayload(VehicleBase payload) {
        log.info("Received new message in vehicle-topic: {}", payload.toString());
    }
}
