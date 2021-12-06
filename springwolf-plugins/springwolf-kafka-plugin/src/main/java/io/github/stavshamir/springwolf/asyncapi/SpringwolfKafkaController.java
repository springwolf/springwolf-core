package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/springwolf/kafka")
@RequiredArgsConstructor
public class SpringwolfKafkaController {

    private final SpringwolfKafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        log.info("Publishing to kafka topic {}: {}", topic, payload);
        kafkaProducer.send(topic, payload);
    }

}
