// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sqs.producers;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto;
import io.github.springwolf.plugins.sqs.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import io.github.springwolf.plugins.sqs.scanners.channels.operationdata.annotation.SqsAsyncQueueBinding;
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
