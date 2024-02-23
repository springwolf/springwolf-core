// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.consumers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncMessage;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.example.kafka.dtos.YamlPayloadDto;
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
                            description = "Showcases a xml based message",
                            message = @AsyncMessage(contentType = "application/yaml")))
    @KafkaAsyncOperationBinding
    @KafkaListener(topics = "yaml-topic")
    public void receiveExamplePayload(YamlPayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());
    }
}
