// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.controller;

import io.github.springwolf.core.controller.PublishingBaseController;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.core.controller.dtos.MessageDto;
import io.github.springwolf.plugins.sqs.producer.SpringwolfSqsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${springwolf.path.base:/springwolf}/plugin/sqs")
@Slf4j
public class SpringwolfSqsController extends PublishingBaseController {

    private final SpringwolfSqsProducer producer;

    public SpringwolfSqsController(PublishingPayloadCreator publishingPayloadCreator, SpringwolfSqsProducer producer) {
        super(publishingPayloadCreator);
        this.producer = producer;
    }

    @Override
    protected boolean isEnabled() {
        return producer.isEnabled();
    }

    @Override
    protected void publishMessage(String topic, MessageDto messageDto, Object payload) {
        log.debug("Publishing to sqs queue {}: {}", topic, messageDto);
        producer.send(topic, messageDto.getHeaders(), payload);
    }
}
