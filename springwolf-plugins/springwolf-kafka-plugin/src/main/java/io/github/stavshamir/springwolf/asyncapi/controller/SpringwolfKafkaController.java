// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

@Slf4j
@RestController
@RequestMapping("/springwolf/kafka")
@RequiredArgsConstructor
public class SpringwolfKafkaController implements InitializingBean {

    private final DefaultSchemasService schemasService;

    private final SpringwolfKafkaProducer producer;

    private final ObjectMapper objectMapper;

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody MessageDto message) {
        if (!producer.isEnabled()) {
            log.warn("Kafka producer is not enabled - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kafka producer is not enabled");
        }

        boolean foundDefinition = false;
        String messagePayloadType = message.getPayloadType();
        for (Schema<?> value : schemasService.getDefinitions().values()) {
            String schemaPayloadType = value.getName();
            // security: match against user input, but always use our controlled data from the DefaultSchemaService
            if (schemaPayloadType.equals(messagePayloadType)) {
                publishMessage(topic, message, schemaPayloadType);

                foundDefinition = true;
                break;
            }
        }

        if (!foundDefinition) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Specified payloadType is not a registered springwolf schema.");
        }
    }

    private void publishMessage(String topic, MessageDto message, String schemaPayloadType) {
        try {
            Class<?> payloadClass = Class.forName(schemaPayloadType);
            Object payload = objectMapper.readValue(message.getPayload(), payloadClass);

            String kafkaKey =
                    message.getBindings() != null ? message.getBindings().get("key") : null;
            log.debug("Publishing to kafka topic {} with key {}: {}", topic, kafkaKey, message);
            producer.send(topic, kafkaKey, message.getHeaders(), payload);
        } catch (ClassNotFoundException | JsonProcessingException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    MessageFormat.format(
                            "Unable to create payload {0} from data: {1}", schemaPayloadType, message.getPayload()));
        }
    }

    @Override
    public void afterPropertiesSet() {
        log.debug("Message publishing via " + this.getClass().getSimpleName() + " is active.");
    }
}
