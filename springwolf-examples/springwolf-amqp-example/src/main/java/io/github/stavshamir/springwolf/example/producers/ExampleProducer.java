package io.github.stavshamir.springwolf.example.producers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AmqpAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.springframework.stereotype.Component;

@Component
public class ExampleProducer {

    @AsyncPublisher(operation = @AsyncOperation(
            channelName = "example-producer-channel-publisher",
            description = "Custom, optional description defined in the AsyncPublisher annotation"
    ))
    @AmqpAsyncOperationBinding()
    public void sendMessage(ExamplePayloadDto msg) {
        // send
    }

}
