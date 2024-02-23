// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.controller;

import io.github.springwolf.core.asyncapi.controller.PublishingBaseController;
import io.github.springwolf.core.asyncapi.controller.PublishingPayloadCreator;
import io.github.springwolf.core.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.producer.SpringwolfJmsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springwolf/jms")
@Slf4j
public class SpringwolfJmsController extends PublishingBaseController {

    private final SpringwolfJmsProducer producer;

    public SpringwolfJmsController(PublishingPayloadCreator publishingPayloadCreator, SpringwolfJmsProducer producer) {
        super(publishingPayloadCreator);
        this.producer = producer;
    }

    @Override
    protected boolean isEnabled() {
        return producer.isEnabled();
    }

    @Override
    protected void publishMessage(String topic, MessageDto message, Object payload) {
        log.debug("Publishing to JMS queue {}: {}", topic, message);
        producer.send(topic, message.getHeaders(), payload);
    }
}
