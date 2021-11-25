package io.github.stavshamir.springwolf.asyncapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/springwolf")
public class SpringwolfProducersController {

    private final SpringwolfProducer springwolfKafkaProducer;
    private final SpringwolfProducer springwolfAmqpProducer;

    public SpringwolfProducersController(
            @Nullable SpringwolfProducer springwolfKafkaProducer,
            @Nullable SpringwolfProducer springwolfAmqpProducer) {
        this.springwolfKafkaProducer = springwolfKafkaProducer;
        this.springwolfAmqpProducer = springwolfAmqpProducer;
    }

    @PostMapping("/kafka/publish")
    public void publishKafka(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        publish(topic, payload, springwolfKafkaProducer, "Kafka");
    }

    @PostMapping("/amqp/publish")
    public void publishAmqp(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        publish(topic, payload, springwolfAmqpProducer, "AMQP");
    }

    private void publish(String topic, Map<String, Object> payload, SpringwolfProducer producer, String protocol) {
        if (producer == null) {
            log.warn(protocol + " producer was not provided - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, protocol + " producer was not provided");
        }

        log.info("Publishing to {} queue {}: {}", protocol, topic, payload);
        producer.send(topic, payload);
    }

}
