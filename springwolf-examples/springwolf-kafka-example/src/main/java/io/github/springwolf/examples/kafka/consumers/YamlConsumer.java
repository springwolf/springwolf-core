// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncMessage;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.springwolf.examples.kafka.dtos.YamlPayloadDto;
import io.github.springwolf.plugins.kafka.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
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
                            description = "Showcases a yaml based message",
                            message = @AsyncMessage(contentType = "application/yaml")))
    @KafkaAsyncOperationBinding
    @KafkaListener(topics = "yaml-topic")
    public void receiveExamplePayload(YamlPayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());
    }
}
