package com.stavshamir.springaroo.endpoints.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * This class is used by KafkaEndpointServiceTest.
 * Do not change or move.
 */
@Component
public class KafkaConsumerClass {

    private final static String TOPIC = "test-topic";

    @KafkaListener(topics = TOPIC)
    private void listenerMethod(String payload) {}

}
