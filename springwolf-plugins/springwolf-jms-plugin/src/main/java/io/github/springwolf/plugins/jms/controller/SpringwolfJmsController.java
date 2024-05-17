// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.controller;

import io.github.springwolf.core.controller.PublishingBaseController;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.core.controller.dtos.MessageDto;
import io.github.springwolf.plugins.jms.producer.SpringwolfJmsProducer;
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
        log.debug("Publishing to jms queue {}: {}", topic, message);
        producer.send(topic, message.getHeaders(), payload);
    }
}
