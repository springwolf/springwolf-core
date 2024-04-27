// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sns.producers;

import io.awspring.cloud.sns.core.SnsTemplate;
import io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBinding;
import io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.examples.sns.dtos.AnotherPayloadDto;
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
