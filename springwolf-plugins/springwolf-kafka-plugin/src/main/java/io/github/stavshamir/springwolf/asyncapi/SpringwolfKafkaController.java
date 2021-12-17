package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/springwolf/kafka")
@RequiredArgsConstructor
public class SpringwolfKafkaController {

    private final SpringwolfKafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        if (!kafkaProducer.isEnabled()) {
            log.debug("Kafka producer is not enabled - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kafka producer is not enabled");
        }

        log.info("Publishing to kafka topic {}: {}", topic, payload);
        kafkaProducer.send(topic, payload);
    }

}
