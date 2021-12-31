package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ProducerChannelScanner.class, DefaultSchemasService.class})
public class ProducerChannelScannerTest {

    @Autowired
    private ProducerChannelScanner scanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    @Test
    public void allFieldsProducerData() {
        // Given a producer data with all fields set
        String channelName = "example-producer-topic-foo1";
        ProducerData producerData = ProducerData.builder()
                .channelName(channelName)
                .binding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .payloadType(ExamplePayloadDto.class)
                .build();

        when(asyncApiDocket.getProducers()).thenReturn(ImmutableList.of(producerData));

        // When scanning for producers
        Map<String, ChannelItem> producerChannels = scanner.scan();

        // Then the channel should be created correctly
        assertThat(producerChannels)
                .containsKey(channelName);
    }

    @Test
    public void missingFieldProducerData() {
        // Given a producer data with missing fields
        String channelName = "example-producer-topic-foo1";
        ProducerData producerData = ProducerData.builder()
                .channelName(channelName)
                .build();

        when(asyncApiDocket.getProducers()).thenReturn(ImmutableList.of(producerData));

        // When scanning for producers
        Map<String, ChannelItem> producerChannels = scanner.scan();

        // Then the channel is not created, and no exception is thrown
        assertThat(producerChannels).isEmpty();
    }
    static class ExamplePayloadDto {
        private String foo;
    }

}