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

    private final SpringwolfProducer springwolfAmqpProducer;

    public SpringwolfProducersController(
            @Nullable SpringwolfProducer springwolfAmqpProducer) {
        this.springwolfAmqpProducer = springwolfAmqpProducer;
    }

    @PostMapping("/amqp/publish")
    public void publishAmqp(@RequestParam String topic, @RequestBody Map<String, Object> payload) {
        if (springwolfAmqpProducer == null) {
            log.warn("AMQP" + " producer was not provided - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMQP" + " producer was not provided");
        }

        log.info("Publishing to {} queue {}: {}", "AMQP", topic, payload);
        springwolfAmqpProducer.send(topic, payload);
    }

}
