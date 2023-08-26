package io.github.stavshamir.springwolf.asyncapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.producer.SpringwolfAmqpProducer;
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

import java.text.MessageFormat;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_AMQP_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED;

@Slf4j
@RestController
@RequestMapping("/springwolf/amqp")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = SPRINGWOLF_AMQP_CONFIG_PREFIX, name = SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED)
public class SpringwolfAmqpController {

    private final SpringwolfAmqpProducer amqpProducer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody MessageDto message) {
        if (!amqpProducer.isEnabled()) {
            log.warn("AMQP producer is not enabled - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AMQP producer is not enabled");
        }

        try {
            Class<?> payloadClass = message.getPayloadClass();
            Object payload = objectMapper.readValue(message.getPayload(), payloadClass);

            log.info("Publishing to amqp queue {}: {}", topic, message.getPayload());
            amqpProducer.send(topic, payload);
        } catch (ClassNotFoundException | JsonProcessingException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    MessageFormat.format(
                            "Unable to create payload {0} from data: {1}",
                            message.getPayloadType(), message.getPayload()));
        }
    }
}
