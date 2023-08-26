package io.github.stavshamir.springwolf.example.sqs.producers;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import io.github.stavshamir.springwolf.example.sqs.dtos.AnotherPayloadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExampleProducer {
    private final SqsTemplate template;

    public static final String QUEUE = "another-queue";

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = QUEUE,
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    @SqsAsyncOperationBinding
    public void sendMessage(AnotherPayloadDto msg) {
        template.send(QUEUE, msg);
    }
}
