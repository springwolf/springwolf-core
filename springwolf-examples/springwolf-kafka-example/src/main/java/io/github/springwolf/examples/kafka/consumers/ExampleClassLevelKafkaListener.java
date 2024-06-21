// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.money.MonetaryAmount;

import static io.github.springwolf.examples.kafka.consumers.ExampleClassLevelKafkaListener.TOPIC;
import static org.springframework.kafka.support.mapping.AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME;

@Component
@Slf4j
@KafkaListener(topics = TOPIC)
public class ExampleClassLevelKafkaListener {
    protected static final String TOPIC = "multi-payload-topic";

    @KafkaHandler
    public void receiveExamplePayload(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.OFFSET) Integer offset,
            @Payload ExamplePayloadDto payload) {
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
