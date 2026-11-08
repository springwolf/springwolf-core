// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.controller;

import io.github.springwolf.core.controller.publish.PublishingBaseController;
import io.github.springwolf.core.controller.publish.PublishingPayloadCreator;
import io.github.springwolf.core.controller.publish.dtos.MessageDto;
import io.github.springwolf.plugins.sqs.publish.SpringwolfSqsPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${springwolf.path.base:/springwolf}/plugin/sqs")
@Slf4j
public class SpringwolfSqsController extends PublishingBaseController {

    private final SpringwolfSqsPublisher publisher;

    public SpringwolfSqsController(
            PublishingPayloadCreator publishingPayloadCreator, SpringwolfSqsPublisher publisher) {
        super(publishingPayloadCreator);
        this.publisher = publisher;
    }

    @Override
    protected boolean isEnabled() {
        return publisher.isEnabled();
    }

    @Override
    protected void publishMessage(String topic, MessageDto messageDto, Object payload) {
        log.debug("Publishing to sqs queue {}: {}", topic, messageDto);
        publisher.send(topic, messageDto.getHeaders(), payload);
    }
}
