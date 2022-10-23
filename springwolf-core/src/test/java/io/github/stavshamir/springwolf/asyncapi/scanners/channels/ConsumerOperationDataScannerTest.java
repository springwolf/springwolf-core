package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ConsumerOperationDataScanner.class, DefaultSchemasService.class})
public class ConsumerOperationDataScannerTest {

    @Autowired
    private ConsumerOperationDataScanner scanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    @Test
    public void allFieldsConsumerData() {
        // Given a consumer data with all fields set
        String channelName = "example-consumer-topic-foo1";
        String description = channelName + "-description";
        ConsumerData consumerData = ConsumerData.builder()
                .channelName(channelName)
                .description(description)
                .channelBinding(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .operationBinding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        when(asyncApiDocket.getConsumers()).thenReturn(ImmutableList.of(consumerData));

        // When scanning for consumers
        Map<String, ChannelItem> consumerChannels = scanner.scan();

        // Then the channel should be created correctly
        assertThat(consumerChannels)
                .containsKey(channelName);

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("example-consumer-topic-foo1_publish")
                .bindings(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .message(Message.builder()
                        .name(ExamplePayloadDto.class.getName())
                        .description(description)
                        .title(ExamplePayloadDto.class.getSimpleName())
                        .payload(PayloadReference.fromModelName(ExamplePayloadDto.class.getSimpleName()))
                        .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                        .build())
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .publish(operation)
                .build();

        assertThat(consumerChannels.get(channelName))
                .isEqualTo(expectedChannel);
    }

    @Test
    public void missingFieldConsumerData() {
        // Given a consumer data with missing fields
        String channelName = "example-consumer-topic-foo1";
        ConsumerData consumerData = ConsumerData.builder()
                .channelName(channelName)
                .build();

        when(asyncApiDocket.getConsumers()).thenReturn(ImmutableList.of(consumerData));

        // When scanning for consumers
        Map<String, ChannelItem> consumerChannels = scanner.scan();

        // Then the channel is not created, and no exception is thrown
        assertThat(consumerChannels).isEmpty();
    }

    @Test
    public void multipleConsumersForSameTopic() {
        // Given a multiple ConsumerData objects for the same topic
        String channelName = "example-consumer-topic";
        String description1 = channelName + "-description1";
        String description2 = channelName + "-description2";

        ConsumerData consumerData1 = ConsumerData.builder()
                .channelName(channelName)
                .description(description1)
                .channelBinding(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .operationBinding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        ConsumerData consumerData2 = ConsumerData.builder()
                .channelName(channelName)
                .description(description2)
                .channelBinding(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .operationBinding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .payloadType(AnotherExamplePayloadDto.class)
                .headers(AsyncHeaders.NOT_USED)
                .build();

        when(asyncApiDocket.getConsumers()).thenReturn(ImmutableList.of(consumerData1, consumerData2));

        // When scanning for consumers
        Map<String, ChannelItem> consumerChannels = scanner.scan();

        // Then one channel is created for the ConsumerData objects with multiple messages
        assertThat(consumerChannels)
                .hasSize(1)
                .containsKey(channelName);

        Set<Message> messages = ImmutableSet.of(
                Message.builder()
                        .name(ExamplePayloadDto.class.getName())
                        .description(description1)
                        .title(ExamplePayloadDto.class.getSimpleName())
                        .payload(PayloadReference.fromModelName(ExamplePayloadDto.class.getSimpleName()))
                        .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                        .build(),
                Message.builder()
                        .name(AnotherExamplePayloadDto.class.getName())
                        .description(description2)
                        .title(AnotherExamplePayloadDto.class.getSimpleName())
                        .payload(PayloadReference.fromModelName(AnotherExamplePayloadDto.class.getSimpleName()))
                        .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                        .build()
        );

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("example-consumer-topic_publish")
                .bindings(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .message(toMessageObjectOrComposition(messages))
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .publish(operation)
                .build();

        assertThat(consumerChannels.get(channelName))
                .isEqualTo(expectedChannel);
    }

    static class ExamplePayloadDto {
        private String foo;
    }

    static class AnotherExamplePayloadDto {
        private String bar;
    }

}
