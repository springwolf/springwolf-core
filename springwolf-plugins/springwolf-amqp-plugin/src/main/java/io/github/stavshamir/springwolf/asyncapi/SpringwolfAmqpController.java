package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.producer.SpringwolfAmqpProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static io.github.stavshamir.springwolf.SpringWolfAmqpConfigConstants.SPRINGWOLF_AMQP_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.SpringWolfAmqpConfigConstants.SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED;

@Slf4j
@RestController
@RequestMapping("/springwolf/amqp")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = SPRINGWOLF_AMQP_CONFIG_PREFIX, name = SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED)
public class SpringwolfAmqpController {

    private final SpringwolfAmqpProducer amqpProducer;

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        if (amqpProducer.isEnabled()) {
            log.warn("AMQP producer is not enabled - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMQP producer is not enabled");
        }

        log.info("Publishing to amqp queue {}: {}", topic, payload);
        amqpProducer.send(topic, payload);
    }

}
