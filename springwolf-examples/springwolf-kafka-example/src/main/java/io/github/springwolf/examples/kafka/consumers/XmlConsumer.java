// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncMessage;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.springwolf.examples.kafka.dtos.XmlPayloadDto;
import io.github.springwolf.plugins.kafka.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class XmlConsumer {

    @AsyncListener(
            operation =
                    @AsyncOperation(
                            channelName = "xml-topic",
                            description = "Showcases a xml based message",
                            message = @AsyncMessage(contentType = "text/xml")))
    @KafkaAsyncOperationBinding
    @KafkaListener(topics = "xml-topic")
    public void receiveExamplePayload(XmlPayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());
    }
}
