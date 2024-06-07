// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sqs.producers;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.github.springwolf.bindings.sqs.annotations.SqsAsyncOperationBinding;
import io.github.springwolf.bindings.sqs.annotations.SqsAsyncQueueBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnotherProducer {
    private final SqsTemplate template;

    public static final String QUEUE = "another-queue";

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = QUEUE,
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    @SqsAsyncOperationBinding(queues = {@SqsAsyncQueueBinding(name = "queue-name")})
    public void sendMessage(AnotherPayloadDto msg) {
        template.send(QUEUE, msg);
    }
}
