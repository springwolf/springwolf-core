package io.github.stavshamir.springwolf.asyncapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
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

import java.text.MessageFormat;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED;

@Slf4j
@RestController
@RequestMapping("/springwolf/kafka")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = SPRINGWOLF_KAFKA_CONFIG_PREFIX, name = SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED)
public class SpringwolfKafkaController {

    private final AsyncApiDocketService asyncApiDocketService;

    private final SpringwolfKafkaProducer producer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody MessageDto message) {
        if (!producer.isEnabled()) {
            log.debug("Kafka producer is not enabled - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kafka producer is not enabled");
        }

        String payloadType = message.getPayloadType();
        if (payloadType.startsWith(asyncApiDocketService.getAsyncApiDocket().getBasePackage())) {
            try {
                Class<?> payloadClass = Class.forName(payloadType);
                Object payload = objectMapper.readValue(message.getPayload(), payloadClass);

                String kafkaKey =
                        message.getBindings() != null ? message.getBindings().get("key") : null;
                log.debug("Publishing to kafka topic {} with key {}: {}", topic, kafkaKey, message);
                producer.send(topic, kafkaKey, message.getHeaders(), payload);
            } catch (ClassNotFoundException | JsonProcessingException ex) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        MessageFormat.format(
                                "Unable to create payload {0} from data: {1}", payloadType, message.getPayload()));
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No payloadType specified.");
        }
    }
}
