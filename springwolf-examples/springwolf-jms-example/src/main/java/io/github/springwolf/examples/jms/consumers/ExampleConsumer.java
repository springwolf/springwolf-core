// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms.consumers;

import io.github.springwolf.core.asyncapi.annotations.AsyncApiPayload;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.examples.jms.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.jms.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.jms.producers.AnotherProducer;
import io.github.springwolf.plugins.jms.asyncapi.annotations.JmsAsyncOperationBinding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {
    private final AnotherProducer anotherProducer;

    @JmsListener(destination = "example-queue")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @JmsListener(destination = "another-queue")
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in another-queue: {}", payload.toString());
    }

    @AsyncListener(
            operation =
                    @AsyncOperation(
                            channelName = "queue.in.x-to-me-feedback",
                            description = "X send the feedback to Me over this channel", // Optional
                            payloadType = String.class,
                            //                            payloadType = XFeedbackMessagePayload.class,
                            message =
                                    @AsyncMessage(
                                            name = "XFeedbackMessagePayload-CustomName",
                                            messageId = "XFeedbackMessageId",
                                            description = "Example feedback that X send to me",
                                            title = "X send FEEDBACK to Me -  Message"),
                            headers =
                                    @AsyncOperation.Headers(
                                            schemaName = "XToMeFeedbackHeaders",
                                            values = {
                                                @AsyncOperation.Headers.Header(
                                                        name = "headerFeedbackXToMe1",
                                                        value = "1",
                                                        description = "..headerFeedback XToMe1 description.."),
                                                @AsyncOperation.Headers.Header(
                                                        name = "headerFeedbackXToMe2",
                                                        value = "2",
                                                        description = "..headerFeedback XToMe2 description.."),
                                            })))
    @JmsAsyncOperationBinding
    public void xSendFeedbackListenerFlow() {
        // Flow logic
    }

    record XFeedbackMessagePayload(@AsyncApiPayload String data) {}

    @AsyncListener(
            operation =
                    @AsyncOperation(
                            channelName = "queue.in.x-to-me-data",
                            description = "X send the data to Me over this channel", // Optional
                            payloadType = String.class,
                            //                            payloadType = XDataMessagePayload.class,
                            message =
                                    @AsyncMessage(
                                            name = "XDataMessagePayload-CustomName",
                                            messageId = "XDataMessageId",
                                            description = "Example data message that X send to Me",
                                            title = "X send DATA to Me - Message"),
                            headers =
                                    @AsyncOperation.Headers(
                                            schemaName = "XToMeDataHeaders",
                                            values = {
                                                @AsyncOperation.Headers.Header(
                                                        name = "headerDataXToMe1",
                                                        value = "1",
                                                        description = "..headerData XToMe1 description.."),
                                                @AsyncOperation.Headers.Header(
                                                        name = "headerDataXToMe2",
                                                        value = "2",
                                                        description = "..headerData XToMe2 description.."),
                                            })))
    @JmsAsyncOperationBinding
    public void xSendDataListenerFlow() {
        // Flow logic
    }

    record XDataMessagePayload(@AsyncApiPayload String data) {}
}
