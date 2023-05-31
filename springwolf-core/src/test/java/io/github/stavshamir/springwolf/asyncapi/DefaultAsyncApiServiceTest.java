package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import com.asyncapi.v2._0_0.model.channel.ChannelItem;
import com.asyncapi.v2._0_0.model.info.Info;
import com.asyncapi.v2._0_0.model.server.Server;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ConsumerOperationDataScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProducerOperationDataScanner;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        DefaultAsyncApiDocketService.class,
        DefaultAsyncApiService.class,
        DefaultChannelsService.class,
        DefaultSchemasService.class,
        ProducerOperationDataScanner.class,
        ConsumerOperationDataScanner.class
})
@Import(DefaultAsyncApiServiceTest.DefaultAsyncApiServiceTestConfiguration.class)
class DefaultAsyncApiServiceTest {

    @TestConfiguration
    public static class DefaultAsyncApiServiceTestConfiguration {

        @Bean
        public AsyncApiDocket docket() {
            Info info = Info.builder()
                    .title("Test")
                    .version("1.0.0")
                    .build();

            ProducerData kafkaProducerData = ProducerData.builder()
                    .channelName("producer-topic")
                    .description("producer-topic-description")
                    .payloadType(String.class)
                    .operationBinding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                    .messageBinding(ImmutableMap.of("kafka", new KafkaMessageBinding()))
                    .build();

            ConsumerData kafkaConsumerData = ConsumerData.builder()
                    .channelName("consumer-topic")
                    .description("consumer-topic-description")
                    .payloadType(String.class)
                    .operationBinding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                    .messageBinding(ImmutableMap.of("kafka", new KafkaMessageBinding()))
                    .build();

            return AsyncApiDocket.builder()
                    .info(info)
                    .basePackage("package")
                    .server("kafka", Server.builder().protocol("kafka").url("kafka:9092").build())
                    .producer(kafkaProducerData)
                    .consumer(kafkaConsumerData)
                    .build();
        }

    }

    @Autowired
    private AsyncApiDocket docket;

    @Autowired
    private DefaultAsyncApiService asyncApiService;

    @Test
    void getAsyncAPI_info_should_be_correct() {
        Info actualInfo = asyncApiService.getAsyncAPI().getInfo();

        assertThat(actualInfo).
                isEqualTo(docket.getInfo());
    }

    @Test
    void getAsyncAPI_servers_should_be_correct() {
        Map<String, Server> actualServers = asyncApiService.getAsyncAPI().getServers();

        assertThat(actualServers).
                isEqualTo(docket.getServers());
    }

    @Test
    void getAsyncAPI_producers_should_be_correct() {
        Map<String, ChannelItem> actualChannels = asyncApiService.getAsyncAPI().getChannels();

        assertThat(actualChannels)
                .isNotEmpty()
                .containsKey("producer-topic");

        final ChannelItem channel = actualChannels.get("producer-topic");
        assertThat(channel.getSubscribe()).isNotNull();
        final Message message = (Message) channel.getSubscribe().getMessage();
        assertThat(message.getDescription()).isEqualTo("producer-topic-description");
        assertThat(message.getBindings()).isEqualTo(ImmutableMap.of("kafka", new KafkaMessageBinding()));
    }

    @Test
    void getAsyncAPI_consumers_should_be_correct() {
        Map<String, ChannelItem> actualChannels = asyncApiService.getAsyncAPI().getChannels();

        assertThat(actualChannels)
                .isNotEmpty()
                .containsKey("consumer-topic");

        final ChannelItem channel = actualChannels.get("consumer-topic");
        assertThat(channel.getPublish()).isNotNull();
        final Message message = (Message) channel.getPublish().getMessage();
        assertThat(message.getDescription()).isEqualTo("consumer-topic-description");
        assertThat(message.getBindings()).isEqualTo(ImmutableMap.of("kafka", new KafkaMessageBinding()));
    }

}
