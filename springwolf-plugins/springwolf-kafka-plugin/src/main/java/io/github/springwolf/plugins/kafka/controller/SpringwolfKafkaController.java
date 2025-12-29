// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.controller;

import io.github.springwolf.core.controller.PublishingBaseController;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.core.controller.dtos.MessageDto;
import io.github.springwolf.plugins.kafka.producer.SpringwolfKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${springwolf.path.base:/springwolf}/plugin/kafka")
@Slf4j
public class SpringwolfKafkaController extends PublishingBaseController {

    private final SpringwolfKafkaProducer producer;

    public SpringwolfKafkaController(
            PublishingPayloadCreator publishingPayloadCreator, SpringwolfKafkaProducer producer) {
        super(publishingPayloadCreator);
        this.producer = producer;
    }

    @Override
    protected boolean isEnabled() {
        return producer.isEnabled();
    }

    @Override
    protected void publishMessage(String topic, MessageDto message, Object payload) {
        String kafkaKey = message.getBindings() != null ? message.getBindings().get("key") : null;
        log.debug("Publishing to kafka topic {} with key {}: {}", topic, kafkaKey, message);
        producer.send(topic, kafkaKey, message.getHeaders(), payload);
    }
}
