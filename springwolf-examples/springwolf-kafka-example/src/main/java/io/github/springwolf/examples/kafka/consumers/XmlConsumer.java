// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.examples.kafka.dtos.XmlPayloadDto;
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
                            message =
                                    @AsyncMessage(
                                            contentType = "text/xml",
                                            description = "Showcases a xml based message")))
    @KafkaAsyncOperationBinding
    @KafkaListener(topics = "xml-topic")
    public void receiveExamplePayload(XmlPayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());
    }
}
