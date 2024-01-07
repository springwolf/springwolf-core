// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.consumers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.example.kafka.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.kafka.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.money.MonetaryAmount;

import static io.github.stavshamir.springwolf.example.kafka.consumers.ExampleClassLevelKafkaListener.TOPIC;
import static org.springframework.kafka.support.mapping.AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME;

@Component
@Slf4j
@KafkaListener(topics = TOPIC)
public class ExampleClassLevelKafkaListener {
    protected static final String TOPIC = "multi-payload-topic";

    @KafkaHandler
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in {}: {}", TOPIC, payload.toString());
    }

    @KafkaHandler
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in {}: {}", TOPIC, payload.toString());
    }

    @KafkaHandler
    @AsyncListener(
            operation =
                    @AsyncOperation(
                            channelName = TOPIC,
                            description =
                                    "Override description in the AsyncListener annotation with servers at ${spring.kafka.bootstrap-servers}",
                            headers =
                                    @AsyncOperation.Headers(
                                            schemaName = "SpringKafkaDefaultHeaders-MonetaryAmount",
                                            values = {
                                                @AsyncOperation.Headers.Header(
                                                        name = DEFAULT_CLASSID_FIELD_NAME,
                                                        description = "Spring Type Id Header",
                                                        value = "javax.money.MonetaryAmount"),
                                            })))
    @KafkaAsyncOperationBinding(
            clientId = "foo-clientId",
            groupId = "#{'foo-groupId'}",
            messageBinding =
                    @KafkaAsyncOperationBinding.KafkaAsyncMessageBinding(
                            key =
                                    @KafkaAsyncOperationBinding.KafkaAsyncKey(
                                            description = "Kafka Consumer Message Key",
                                            example = "example-key")))
    public void receiveMonetaryAmount(MonetaryAmount payload) {
        log.info("Received new message in {}: {}", TOPIC, payload.toString());
    }
}
