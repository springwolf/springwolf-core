// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.sns.producers;

import io.awspring.cloud.sns.core.SnsTemplate;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBindingIdentifier;
import io.github.stavshamir.springwolf.example.sns.dtos.AnotherPayloadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnotherProducer {
    private final SnsTemplate template;

    public static final String TOPIC = "another-topic";

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = TOPIC,
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    @SnsAsyncOperationBinding(protocol = "sqs", endpoint = @SnsAsyncOperationBindingIdentifier())
    public void sendMessage(AnotherPayloadDto msg) {
        template.send(TOPIC, MessageBuilder.withPayload(msg).build());
    }
}
