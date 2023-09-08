package io.github.stavshamir.springwolf.asyncapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.producer.SpringwolfSqsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SQS_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SQS_PLUGIN_PUBLISHING_ENABLED;

@Slf4j
@RestController
@RequestMapping("/springwolf/sqs")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = SPRINGWOLF_SQS_CONFIG_PREFIX, name = SPRINGWOLF_SQS_PLUGIN_PUBLISHING_ENABLED)
public class SpringwolfSqsController implements InitializingBean {

    private final AsyncApiDocketService asyncApiDocketService;

    private final SpringwolfSqsProducer producer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/publish")
    public void publish(@RequestParam String topic, @RequestBody MessageDto message) {
        if (!producer.isEnabled()) {
            log.warn("SQS producer is not enabled - message will not be published");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SQS producer is not enabled");
        }

        String payloadType = message.getPayloadType();
        if (payloadType.startsWith(asyncApiDocketService.getAsyncApiDocket().getBasePackage())) {
            try {
                Class<?> payloadClass = Class.forName(payloadType);
                Object payload = objectMapper.readValue(message.getPayload(), payloadClass);

                log.debug("Publishing to sqs queue {}: {}", topic, payload);
                producer.send(topic, payload);
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
