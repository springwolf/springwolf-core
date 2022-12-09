package io.github.stavshamir.springwolf.example.consumers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncSubscriber;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.money.MonetaryAmount;

import static org.springframework.kafka.support.converter.AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME;


@Service
@KafkaListener(topics = "multi-payload-topic", containerFactory = "exampleKafkaListenerContainerFactory")
public class ExampleClassLevelKafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(ExampleClassLevelKafkaListener.class);

    @KafkaHandler
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        logger.info("Received new message in multi-payload-topic: {}", payload.toString());
    }

    @KafkaHandler
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        logger.info("Received new message in multi-payload-topic: {}", payload.toString());
    }

    @KafkaHandler
    @AsyncSubscriber(operation = @AsyncOperation(
            channelName = "multi-payload-topic",
            description = "Override description in the AsyncSubscriber annotation",
            headers = @AsyncOperation.Headers(
                    schemaName = "SpringKafkaDefaultHeaders-MonetaryAmount",
                    values = {
                            @AsyncOperation.Headers.Header(
                                    name = DEFAULT_CLASSID_FIELD_NAME,
                                    description = "Spring Type Id Header",
                                    value = "javax.money.MonetaryAmount"
                            ),
                    }
            )
    ))
    @KafkaAsyncOperationBinding(
            bindingVersion = "1",
            clientId = "foo-clientId",
            groupId = "foo-groupId"
    )
    public void receiveMonetaryAmount(MonetaryAmount payload) {
        logger.info("Received new message in multi-payload-topic: {}", payload.toString());
    }

}
