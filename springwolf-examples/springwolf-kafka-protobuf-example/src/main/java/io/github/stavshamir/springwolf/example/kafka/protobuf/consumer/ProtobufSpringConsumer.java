// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.protobuf.consumer;

import io.confluent.protobuf.entity.CustomerMessagePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProtobufSpringConsumer {

    private final Logger logger = LoggerFactory.getLogger(ProtobufSpringConsumer.class);
    private static final String TOPIC = "protobuf-topic";

    @KafkaListener(topics = TOPIC, groupId = "group_id_v1")
    public void processEvent(CustomerMessagePayload.CustomerMessage message) {
        logger.info(String.format("#### -> Consuming message -> %s", message.toString()));
    }
}
