package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.asyncapi.v2.model.info.Info;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {ProducerOperationDataScanner.class, DefaultSchemasService.class})
public class ProducerOperationDataScannerTest {

    @Autowired
    private ProducerOperationDataScanner scanner;

    @MockBean
    private AsyncApiDocketService asyncApiDocketService;

    @Test
    public void allFieldsProducerData() {
        // Given a producer data with all fields set
        String channelName = "example-producer-topic-foo1";
        String description = channelName + "-description";
        ProducerData producerData = ProducerData.builder()
                .channelName(channelName)
                .description(description)
                .channelBinding(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .operationBinding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        mockProducers(ImmutableList.of(producerData));

        // When scanning for producers
        Map<String, ChannelItem> producerChannels = scanner.scan();

        // Then the channel should be created correctly
        assertThat(producerChannels)
                .containsKey(channelName);

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("example-producer-topic-foo1_subscribe")
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
                .subscribe(operation)
                .build();

        assertThat(producerChannels.get(channelName))
                .isEqualTo(expectedChannel);
    }

    @Test
    public void missingFieldProducerData() {
        // Given a producer data with missing fields
        String channelName = "example-producer-topic-foo1";
        ProducerData producerData = ProducerData.builder()
                .channelName(channelName)
                .build();

        mockProducers(ImmutableList.of(producerData));

        // When scanning for producers
        Map<String, ChannelItem> producerChannels = scanner.scan();

        // Then the channel is not created, and no exception is thrown
        assertThat(producerChannels).isEmpty();
    }

    @Test
    public void multipleProducersForSameTopic() {
        // Given a multiple ProducerData objects for the same topic
        String channelName = "example-producer-topic";
        String description1 = channelName + "-description1";
        String description2 = channelName + "-description2";

        ProducerData producerData1 = ProducerData.builder()
                .channelName(channelName)
                .description(description1)
                .channelBinding(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .operationBinding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        ProducerData producerData2 = ProducerData.builder()
                .channelName(channelName)
                .description(description2)
                .channelBinding(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .operationBinding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .payloadType(AnotherExamplePayloadDto.class)
                .headers(AsyncHeaders.NOT_USED)
                .build();

        mockProducers(ImmutableList.of(producerData1, producerData2));

        // When scanning for producers
        Map<String, ChannelItem> producerChannels = scanner.scan();

        // Then one channel is created for the ProducerData objects with multiple messages
        assertThat(producerChannels)
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
                .operationId("example-producer-topic_subscribe")
                .bindings(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .message(toMessageObjectOrComposition(messages))
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .subscribe(operation)
                .build();

        assertThat(producerChannels.get(channelName))
                .isEqualTo(expectedChannel);
    }

    private void mockProducers(Collection<ProducerData> producers) {
        AsyncApiDocket asyncApiDocket = AsyncApiDocket.builder()
                .info(Info.builder()
                        .title("ProducerOperationDataScannerTest-title")
                        .version("ProducerOperationDataScannerTest-version")
                        .build())
                .producers(producers)
                .build();
        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(asyncApiDocket);
    }

    static class ExamplePayloadDto {
        private String foo;
    }

    static class AnotherExamplePayloadDto {
        private String bar;
    }

}
