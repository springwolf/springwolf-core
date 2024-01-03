// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ServerReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
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
            ProducerOperationDataScanner.class,
            DefaultSchemasService.class,
            PayloadClassExtractor.class,
            ExampleJsonGenerator.class,
            SpringwolfConfigProperties.class,
        })
class ProducerOperationDataScannerIntegrationTest {

    @Autowired
    private ProducerOperationDataScanner scanner;

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
    void allFieldsProducerData() {
        // Given a producer data with all fields set
        String channelName = "example-producer-topic-foo1";
        String description = channelName + "-description";
        ProducerData producerData = ProducerData.builder()
                .channelName(channelName)
                .description(description)
                .channelBinding(Map.of("kafka", new KafkaChannelBinding()))
                .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        mockProducers(List.of(producerData));

        // When scanning for producers
        Map<String, ChannelObject> producerChannels = scanner.scan();

        // Then the channel should be created correctly
        assertThat(producerChannels).containsKey(channelName);

        String messageDescription1 = "Example Payload DTO Description";
        Operation operation = Operation.builder()
                .description(description)
                .title("example-producer-topic-foo1_subscribe")
                .bindings(Map.of("kafka", new KafkaOperationBinding()))
                //                .message(Message.builder() FIXME
                //                        .name(ExamplePayloadDto.class.getName())
                //                        .title(ExamplePayloadDto.class.getSimpleName())
                //                        .description(messageDescription1)
                //
                // .payload(PayloadReference.fromModelName(ExamplePayloadDto.class.getSimpleName()))
                //
                // .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                //                        .bindings(Map.of("kafka", new KafkaMessageBinding()))
                //                        .build())
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .bindings(Map.of("kafka", new KafkaChannelBinding()))
                //                .subscribe(operation) FIXME
                .build();

        assertThat(producerChannels.get(channelName)).isEqualTo(expectedChannel);
    }

    @Test
    void missingFieldProducerData() {
        // Given a producer data with missing fields
        String channelName = "example-producer-topic-foo1";
        ProducerData producerData =
                ProducerData.builder().channelName(channelName).build();

        mockProducers(List.of(producerData));

        // When scanning for producers
        Map<String, ChannelObject> producerChannels = scanner.scan();

        // Then the channel is not created, and no exception is thrown
        assertThat(producerChannels).isEmpty();
    }

    @Test
    void multipleProducersForSameTopic() {
        // Given a multiple ProducerData objects for the same topic
        String channelName = "example-producer-topic";
        String description1 = channelName + "-description1";
        String description2 = channelName + "-description2";

        ProducerData producerData1 = ProducerData.builder()
                .channelName(channelName)
                .description(description1)
                .server(ServerReference.builder().ref("kafka1").build())
                .channelBinding(Map.of("kafka", new KafkaChannelBinding()))
                .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        ProducerData producerData2 = ProducerData.builder()
                .channelName(channelName)
                .description(description2)
                .server(ServerReference.builder().ref("kafka2").build())
                .channelBinding(Map.of("kafka", new KafkaChannelBinding()))
                .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                .payloadType(AnotherExamplePayloadDto.class)
                .headers(AsyncHeaders.NOT_USED)
                .build();

        mockProducers(List.of(producerData1, producerData2));

        // When scanning for producers
        Map<String, ChannelObject> producerChannels = scanner.scan();

        // Then one channel is created for the ProducerData objects with multiple messages
        assertThat(producerChannels).hasSize(1).containsKey(channelName);

        String messageDescription1 = "Example Payload DTO Description";
        String messageDescription2 = "Another Example Payload DTO Description";
        Set<MessageObject> messages = Set.of(
                MessageObject.builder()
                        .name(ExamplePayloadDto.class.getName())
                        .title(ExamplePayloadDto.class.getSimpleName())
                        .description(messageDescription1)
                        //
                        // .payload(PayloadReference.fromModelName(ExamplePayloadDto.class.getSimpleName())) FIXME
                        //
                        // .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())) FIXME
                        .bindings(Map.of("kafka", new KafkaMessageBinding()))
                        .build(),
                MessageObject.builder()
                        .name(AnotherExamplePayloadDto.class.getName())
                        .title(AnotherExamplePayloadDto.class.getSimpleName())
                        .description(messageDescription2)
                        //
                        // .payload(PayloadReference.fromModelName(AnotherExamplePayloadDto.class.getSimpleName()))
                        // FIXME
                        //
                        // .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName())) FIXME
                        .bindings(Map.of("kafka", new KafkaMessageBinding()))
                        .build());

        Operation operation = Operation.builder()
                .description(description1)
                .title("example-producer-topic_subscribe")
                .bindings(Map.of("kafka", new KafkaOperationBinding()))
                //                .message(toMessageObjectOrComposition(messages)) FIXME
                .build();

        ChannelObject expectedChannel = ChannelObject.builder()
                .servers(List.of(ServerReference.builder().ref("kafka1").build())) // First Consumerdata Server Entry
                .bindings(Map.of("kafka", new KafkaChannelBinding()))
                //                .subscribe(operation) FIXME
                .build();

        assertThat(producerChannels.get(channelName)).isEqualTo(expectedChannel);
    }

    private void mockProducers(Collection<ProducerData> producers) {
        AsyncApiDocket asyncApiDocket = AsyncApiDocket.builder()
                .info(Info.builder()
                        .title("ProducerOperationDataScannerTest-title")
                        .version("ProducerOperationDataScannerTest-version")
                        .build())
                .server("kafka1", new Server())
                .server("kafka2", new Server())
                .producers(producers)
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
