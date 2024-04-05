// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.examples.kafka.dtos.YamlPayloadDto;
import io.github.springwolf.plugins.kafka.asyncapi.annotations.KafkaAsyncOperationBinding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class YamlConsumer {

    @AsyncListener(
            operation =
                    @AsyncOperation(
                            channelName = "yaml-topic",
                            message =
                                    @AsyncMessage(
                                            contentType = "application/yaml",
                                            description = "Showcases a yaml based message")))
    @KafkaAsyncOperationBinding
    @KafkaListener(topics = "yaml-topic")
    public void receiveExamplePayload(YamlPayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());
    }
}
