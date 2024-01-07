// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ServerReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.info.Info;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.server.Server;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            ConsumerOperationDataScanner.class,
            DefaultSchemasService.class,
            PayloadClassExtractor.class,
            ExampleJsonGenerator.class,
            SpringwolfConfigProperties.class,
        })
class ConsumerOperationDataScannerIntegrationTest {

    @Autowired
    private ConsumerOperationDataScanner scanner;

    @MockBean
    private AsyncApiDocketService asyncApiDocketService;

    @BeforeEach
    public void defaultDocketSetup() {
        AsyncApiDocket docket = AsyncApiDocket.builder()
                .info(Info.builder()
                        .title("Default Asyncapi Title")
                        .version("1.0.0")
                        .build())
                .server("kafka1", new Server())
                .server("kafka2", new Server())
                .build();

        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(docket);
    }

    @Test
    void allFieldsConsumerData() {
        // Given a consumer data with all fields set
        String channelName = "example-consumer-topic-foo1";
        String description = channelName + "-description";
        ConsumerData consumerData = ConsumerData.builder()
                .channelName(channelName)
                .description(description)
                .channelBinding(Map.of("kafka", new KafkaChannelBinding()))
                .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        mockConsumers(List.of(consumerData));

        // When scanning for consumers
        Map<String, ChannelObject> consumerChannels = scanner.scan();

        // Then the channel should be created correctly
        assertThat(consumerChannels).containsKey(channelName);

        String messageDescription = "Example Payload DTO Description";
        Operation operation = Operation.builder()
                .description(description)
                .title("example-consumer-topic-foo1_publish")
                .bindings(Map.of("kafka", new KafkaOperationBinding()))
                //                .message(Message.builder() FIXME
                //                        .name(ExamplePayloadDto.class.getName())
                //                        .title(ExamplePayloadDto.class.getSimpleName())
                //                        .description(messageDescription)
                //
                // .payload(PayloadReference.fromModelName(ExamplePayloadDto.class.getSimpleName()))
                //
                // .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                //                        .bindings(Map.of("kafka", new KafkaMessageBinding()))
                //                        .build())
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(Map.of("kafka", new KafkaChannelBinding()))
                //                .publish(operation) FIXME
                .build();

        assertThat(consumerChannels.get(channelName)).isEqualTo(expectedChannel);
    }

    @Test
    void missingFieldConsumerData() {
        // Given a consumer data with missing fields
        String channelName = "example-consumer-topic-foo1";
        ConsumerData consumerData =
                ConsumerData.builder().channelName(channelName).build();

        mockConsumers(List.of(consumerData));

        // When scanning for consumers
        Map<String, ChannelObject> consumerChannels = scanner.scan();

        // Then the channel is not created, and no exception is thrown
        assertThat(consumerChannels).isEmpty();
    }

    @Test
    void multipleConsumersForSameTopic() {
        // Given a multiple ConsumerData objects for the same topic
        String channelName = "example-consumer-topic";
        String description1 = channelName + "-description1";
        String description2 = channelName + "-description2";

        ConsumerData consumerData1 = ConsumerData.builder()
                .channelName(channelName)
                .description(description1)
                .server(ServerReference.builder().ref("kafka1").build())
                .channelBinding(Map.of("kafka", new KafkaChannelBinding()))
                .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        ConsumerData consumerData2 = ConsumerData.builder()
                .channelName(channelName)
                .description(description2)
                .server(ServerReference.builder().ref("kafka2").build())
                .channelBinding(Map.of("kafka", new KafkaChannelBinding()))
                .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                .payloadType(AnotherExamplePayloadDto.class)
                .headers(AsyncHeaders.NOT_USED)
                .build();

        mockConsumers(List.of(consumerData1, consumerData2));

        // When scanning for consumers
        Map<String, ChannelObject> consumerChannels = scanner.scan();

        // Then one channel is created for the ConsumerData objects with multiple messages
        assertThat(consumerChannels).hasSize(1).containsKey(channelName);

        String messageDescription1 = "Example Payload DTO Description";
        String messageDescription2 = "Another Example Payload DTO Description";
        Set<MessageObject> messages = Set.of(
                MessageObject.builder()
                        .name(ExamplePayloadDto.class.getName())
                        .title(ExamplePayloadDto.class.getSimpleName())
                        .description(messageDescription1)
                        // .payload(PayloadReference.fromModelName(ExamplePayloadDto.class.getSimpleName())) FIXME
                        .headers(MessageHeaders.of(
                                MessageReference.fromSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                        .bindings(Map.of("kafka", new KafkaMessageBinding()))
                        .build(),
                MessageObject.builder()
                        .name(AnotherExamplePayloadDto.class.getName())
                        .title(AnotherExamplePayloadDto.class.getSimpleName())
                        .description(messageDescription2)
                        // .payload(PayloadReference.fromModelName(AnotherExamplePayloadDto.class.getSimpleName()))
                        // FIXME
                        .headers(MessageHeaders.of(
                                MessageReference.fromSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                        .bindings(Map.of("kafka", new KafkaMessageBinding()))
                        .build());

        Operation operation = Operation.builder()
                .description(description1)
                .title("example-consumer-topic_publish")
                .bindings(Map.of("kafka", new KafkaOperationBinding()))
                //                .message(toMessageObjectOrComposition(messages)) FIXME
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .servers(List.of(ServerReference.builder().ref("kafka1").build())) // First Consumerdata Server Entry
                .bindings(Map.of("kafka", new KafkaChannelBinding()))
                //                .publish(operation) FIXME
                .build();

        assertThat(consumerChannels.get(channelName)).isEqualTo(expectedChannel);
    }

    private void mockConsumers(Collection<ConsumerData> consumers) {
        AsyncApiDocket asyncApiDocket = AsyncApiDocket.builder()
                .info(Info.builder()
                        .title("ConsumerOperationDataScannerTest-title")
                        .version("ConsumerOperationDataScannerTest-version")
                        .build())
                .server("kafka1", new Server())
                .server("kafka2", new Server())
                .consumers(consumers)
                .build();

        reset(asyncApiDocketService);
        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(asyncApiDocket);
    }

    @Schema(description = "Example Payload DTO Description")
    static class ExamplePayloadDto {
        private String foo;
    }

    @Schema(description = "Another Example Payload DTO Description")
    static class AnotherExamplePayloadDto {
        private String bar;
    }
}
