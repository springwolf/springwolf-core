// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.controller;

import io.github.springwolf.core.controller.PublishingBaseController;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.core.controller.dtos.MessageDto;
import io.github.springwolf.plugins.sns.producer.SpringwolfSnsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("${springwolf.path.base:/springwolf}/sns")
public class SpringwolfSnsController extends PublishingBaseController {

    private final SpringwolfSnsProducer producer;

    public SpringwolfSnsController(PublishingPayloadCreator publishingPayloadCreator, SpringwolfSnsProducer producer) {
        super(publishingPayloadCreator);
        this.producer = producer;
    }

    @Override
    protected boolean isEnabled() {
        return producer.isEnabled();
    }

    @Override
    protected void publishMessage(String topic, MessageDto message, Object payload) {
        log.debug("Publishing to sns topic {}: {}", topic, message);
        producer.send(topic, message.getHeaders(), payload);
    }
}
