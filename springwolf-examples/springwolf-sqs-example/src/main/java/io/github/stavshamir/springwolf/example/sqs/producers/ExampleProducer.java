package io.github.stavshamir.springwolf.example.sqs.producers;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.example.sqs.dtos.AnotherPayloadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExampleProducer {
    private final SqsTemplate template;

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = "example-producer-channel-publisher",
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    public void sendMessage(AnotherPayloadDto msg) {
        template.send(msg);
    }
}
