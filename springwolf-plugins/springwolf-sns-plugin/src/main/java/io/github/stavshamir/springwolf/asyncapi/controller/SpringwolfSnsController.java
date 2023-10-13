// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.producer.SpringwolfSnsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

@Slf4j
@RestController
@RequestMapping("/springwolf/sns")
@RequiredArgsConstructor
public class SpringwolfSnsController implements InitializingBean {

    private final AsyncApiDocketService asyncApiDocketService;

    private final SpringwolfSnsProducer producer;

    private final ObjectMapper objectMapper;

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody MessageDto message) {
        if (!producer.isEnabled()) {
            log.warn("SNS producer is not enabled - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SNS producer is not enabled");
        }

        String payloadType = message.getPayloadType();
        if (payloadType.startsWith(asyncApiDocketService.getAsyncApiDocket().getBasePackage())) {
            try {
                Class<?> payloadClass = Class.forName(payloadType);
                Object payload = objectMapper.readValue(message.getPayload(), payloadClass);

                log.debug("Publishing to sns queue {}: {}", topic, payload);
                producer.send(topic, MessageBuilder.withPayload(payload).build());
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

    @Override
    public void afterPropertiesSet() {
        log.debug("Message publishing via " + this.getClass().getSimpleName() + " is active.");
    }
}
