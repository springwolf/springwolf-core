package io.github.stavshamir.springwolf.asyncapi.examples.consumers;

import lombok.Data;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerClass {

    public final static String TOPIC = "test-topic";

    @KafkaListener(topics = TOPIC)
    private void listenerMethod(ExamplePayload payload) {
    }

    @Data
    public static class ExamplePayload {
        String s;
    }

}
