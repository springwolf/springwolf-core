package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.channel.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2._6_0.model.info.Info;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.swagger.v3.oas.annotations.media.Schema;
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

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ConsumerOperationDataScanner.class, DefaultSchemasService.class})
class ConsumerOperationDataScannerTest {

    @Autowired
    private ConsumerOperationDataScanner scanner;

    @MockBean
    private AsyncApiDocketService asyncApiDocketService;

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
        Map<String, ChannelItem> consumerChannels = scanner.scan();

        // Then the channel should be created correctly
        assertThat(consumerChannels)
                .containsKey(channelName);

        String messageDescription = "Example Payload DTO Description";
        Operation operation = Operation.builder()
                .description(description)
                .operationId("example-consumer-topic-foo1_publish")
                .bindings(Map.of("kafka", new KafkaOperationBinding()))
                .message(Message.builder()
                        .name(ExamplePayloadDto.class.getName())
                        .description(messageDescription)
                        .title(ExamplePayloadDto.class.getSimpleName())
                        .payload(PayloadReference.fromModelName(ExamplePayloadDto.class.getSimpleName()))
                        .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                        .bindings(Map.of("kafka", new KafkaMessageBinding()))
                        .build())
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(Map.of("kafka", new KafkaChannelBinding()))
                .publish(operation)
                .build();

        assertThat(consumerChannels.get(channelName))
                .isEqualTo(expectedChannel);
    }

    @Test
    void missingFieldConsumerData() {
        // Given a consumer data with missing fields
        String channelName = "example-consumer-topic-foo1";
        ConsumerData consumerData = ConsumerData.builder()
                .channelName(channelName)
                .build();

        mockConsumers(List.of(consumerData));

        // When scanning for consumers
        Map<String, ChannelItem> consumerChannels = scanner.scan();

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
                .channelBinding(Map.of("kafka", new KafkaChannelBinding()))
                .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        ConsumerData consumerData2 = ConsumerData.builder()
                .channelName(channelName)
                .description(description2)
                .channelBinding(Map.of("kafka", new KafkaChannelBinding()))
                .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                .payloadType(AnotherExamplePayloadDto.class)
                .headers(AsyncHeaders.NOT_USED)
                .build();

        mockConsumers(List.of(consumerData1, consumerData2));

        // When scanning for consumers
        Map<String, ChannelItem> consumerChannels = scanner.scan();

        // Then one channel is created for the ConsumerData objects with multiple messages
        assertThat(consumerChannels)
                .hasSize(1)
                .containsKey(channelName);

        String messageDescription1 = "Example Payload DTO Description";
        String messageDescription2 = "Another Example Payload DTO Description";
        Set<Message> messages = Set.of(
                Message.builder()
                        .name(ExamplePayloadDto.class.getName())
                        .description(messageDescription1)
                        .title(ExamplePayloadDto.class.getSimpleName())
                        .payload(PayloadReference.fromModelName(ExamplePayloadDto.class.getSimpleName()))
                        .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                        .bindings(Map.of("kafka", new KafkaMessageBinding()))
                        .build(),
                Message.builder()
                        .name(AnotherExamplePayloadDto.class.getName())
                        .description(messageDescription2)
                        .title(AnotherExamplePayloadDto.class.getSimpleName())
                        .payload(PayloadReference.fromModelName(AnotherExamplePayloadDto.class.getSimpleName()))
                        .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                        .bindings(Map.of("kafka", new KafkaMessageBinding()))
                        .build()
        );

        Operation operation = Operation.builder()
                .description(description1)
                .operationId("example-consumer-topic_publish")
                .bindings(Map.of("kafka", new KafkaOperationBinding()))
                .message(toMessageObjectOrComposition(messages))
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(Map.of("kafka", new KafkaChannelBinding()))
                .publish(operation)
                .build();

        assertThat(consumerChannels.get(channelName))
                .isEqualTo(expectedChannel);
    }

    private void mockConsumers(Collection<ConsumerData> consumers) {
        AsyncApiDocket asyncApiDocket = AsyncApiDocket.builder()
                .info(Info.builder()
                        .title("ConsumerOperationDataScannerTest-title")
                        .version("ConsumerOperationDataScannerTest-version")
                        .build())
                .consumers(consumers)
                .build();
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
