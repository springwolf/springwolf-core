// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.producers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersCloudEventConstants;
import io.github.springwolf.examples.kafka.configuration.KafkaConfiguration;
import io.github.springwolf.examples.kafka.dtos.NestedPayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;

import static org.springframework.kafka.support.mapping.DefaultJacksonJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME;

public class NestedProducer {

    @Autowired
    private KafkaTemplate<String, NestedPayloadDto> kafkaTemplate;

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = "topic-defined-via-asyncPublisher-annotation",
                            description = "Custom, optional description defined in the AsyncPublisher annotation",
                            servers = {"kafka-server"},
                            headers =
                                    @AsyncOperation.Headers(
                                            schemaName = "SpringDefaultHeaderAndCloudEvent",
                                            description = "Spring __TypeId__ and CloudEvent Headers",
                                            values = {
                                                @AsyncOperation.Headers.Header(
                                                        name = DEFAULT_CLASSID_FIELD_NAME,
                                                        description = "Spring Type Id Header",
                                                        value =
                                                                "io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"),
                                                @AsyncOperation.Headers.Header(
                                                        name = AsyncHeadersCloudEventConstants.CONTENT_TYPE,
                                                        description = AsyncHeadersCloudEventConstants.CONTENT_TYPE_DESC,
                                                        value = MediaType.APPLICATION_JSON_VALUE),
                                                @AsyncOperation.Headers.Header(
                                                        name = AsyncHeadersCloudEventConstants.ID,
                                                        description = AsyncHeadersCloudEventConstants.ID_DESC,
                                                        value = "2c60089e-6f39-459d-8ced-2d6df7e4c03a"),
                                                @AsyncOperation.Headers.Header(
                                                        name = AsyncHeadersCloudEventConstants.SPECVERSION,
                                                        description = AsyncHeadersCloudEventConstants.SPECVERSION_DESC,
                                                        value = "1.0"),
                                                @AsyncOperation.Headers.Header(
                                                        name = AsyncHeadersCloudEventConstants.SOURCE,
                                                        description = AsyncHeadersCloudEventConstants.SOURCE_DESC,
                                                        value = "http://localhost"),
                                                @AsyncOperation.Headers.Header(
                                                        name = AsyncHeadersCloudEventConstants.SUBJECT,
                                                        description = AsyncHeadersCloudEventConstants.SUBJECT_DESC,
                                                        value = "${spring.application.name}"),
                                                @AsyncOperation.Headers.Header(
                                                        name = AsyncHeadersCloudEventConstants.TIME,
                                                        description = AsyncHeadersCloudEventConstants.TIME_DESC,
                                                        value = "2023-10-28T20:01:23+00:00",
                                                        type = SchemaType.STRING,
                                                        format = "date-time"),
                                                @AsyncOperation.Headers.Header(
                                                        name = AsyncHeadersCloudEventConstants.TYPE,
                                                        description = AsyncHeadersCloudEventConstants.TYPE_DESC,
                                                        value = "NestedPayloadDto.v1"),
                                            })))
    @KafkaAsyncOperationBinding(
            clientId = "foo-clientId",
            messageBinding =
                    @KafkaAsyncOperationBinding.KafkaAsyncMessageBinding(
                            key =
                                    @KafkaAsyncOperationBinding.KafkaAsyncKey(
                                            description = "Kafka Producer Message Key",
                                            example = "example-key")))
    public void sendMessage(NestedPayloadDto msg) {
        kafkaTemplate.send(KafkaConfiguration.PRODUCER_TOPIC, msg);
    }
}
