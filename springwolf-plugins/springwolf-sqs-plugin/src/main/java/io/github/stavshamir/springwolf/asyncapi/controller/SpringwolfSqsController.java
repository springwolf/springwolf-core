// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.controller;

import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.producer.SpringwolfSqsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springwolf/sqs")
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
    protected void publishMessage(String topic, MessageDto message, Object payload) {
        log.debug("Publishing to SQS queue {}: {}", topic, message);
        producer.send(topic, payload);
    }
}
