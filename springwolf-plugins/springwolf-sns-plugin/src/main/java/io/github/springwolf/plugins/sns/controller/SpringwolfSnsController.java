// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.controller;

import io.github.springwolf.core.controller.publish.PublishingBaseController;
import io.github.springwolf.core.controller.publish.PublishingPayloadCreator;
import io.github.springwolf.core.controller.publish.dtos.MessageDto;
import io.github.springwolf.plugins.sns.publish.SpringwolfSnsPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("${springwolf.path.base:/springwolf}/plugin/sns")
public class SpringwolfSnsController extends PublishingBaseController {

    private final SpringwolfSnsPublisher producer;

    public SpringwolfSnsController(PublishingPayloadCreator publishingPayloadCreator, SpringwolfSnsPublisher producer) {
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
