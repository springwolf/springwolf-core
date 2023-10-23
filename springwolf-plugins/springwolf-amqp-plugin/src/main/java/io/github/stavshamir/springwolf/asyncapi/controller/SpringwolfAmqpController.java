// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.controller;

import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.producer.SpringwolfAmqpProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springwolf/amqp")
@Slf4j
public class SpringwolfAmqpController extends PublishingBaseController {

    private final SpringwolfAmqpProducer producer;

    public SpringwolfAmqpController(
            PublishingPayloadCreator publishingPayloadCreator, SpringwolfAmqpProducer producer) {
        super(publishingPayloadCreator);
        this.producer = producer;
    }

    @Override
    protected boolean isEnabled() {
        return producer.isEnabled();
    }

    @Override
    protected void publishMessage(String topic, MessageDto message, Object payload) {
        log.debug("Publishing to amqp queue {}: {}", topic, message.getPayload());
        producer.send(topic, payload);
    }
}
