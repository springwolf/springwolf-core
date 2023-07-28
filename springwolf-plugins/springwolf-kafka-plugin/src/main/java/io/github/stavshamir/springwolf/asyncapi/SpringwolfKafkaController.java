package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED;

@Slf4j
@RestController
@RequestMapping("/springwolf/kafka")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = SPRINGWOLF_KAFKA_CONFIG_PREFIX, name = SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED)
public class SpringwolfKafkaController {

    private final SpringwolfKafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody MessageDto message) {
        if (message.getPayload() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message payload is required");
        }

        if (!kafkaProducer.isEnabled()) {
            log.debug("Kafka producer is not enabled - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kafka producer is not enabled");
        }

        String kafkaKey = message.getBindings() != null ? message.getBindings().get("key") : null;
        log.debug("Publishing to kafka topic {} with key {}: {}", topic, kafkaKey, message);

        kafkaProducer.send(topic, kafkaKey, message.getHeaders(), message.getPayload());
    }
}
