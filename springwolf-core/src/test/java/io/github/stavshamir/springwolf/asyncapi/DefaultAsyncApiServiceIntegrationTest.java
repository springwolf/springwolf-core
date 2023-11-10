// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2._6_0.model.server.Server;
import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SpringPayloadAnnotationTypeExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ConsumerOperationDataScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProducerOperationDataScanner;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            SpringwolfConfigProperties.class,
            DefaultAsyncApiDocketService.class,
            DefaultAsyncApiService.class,
            DefaultChannelsService.class,
            DefaultSchemasService.class,
            SpringPayloadAnnotationTypeExtractor.class,
            ExampleJsonGenerator.class,
            ProducerOperationDataScanner.class,
            ConsumerOperationDataScanner.class,
        })
@Import({
    DefaultAsyncApiServiceIntegrationTest.DefaultAsyncApiServiceTestConfiguration.class,
    DefaultAsyncApiServiceIntegrationTest.TestDescriptionCustomizer.class,
    DefaultAsyncApiServiceIntegrationTest.TestDescriptionCustomizer2.class
})
class DefaultAsyncApiServiceIntegrationTest {

    @TestConfiguration
    public static class DefaultAsyncApiServiceTestConfiguration {

        @Bean
        public AsyncApiDocket docket() {
            Info info = Info.builder().title("Test").version("1.0.0").build();

            ProducerData kafkaProducerData = ProducerData.builder()
                    .channelName("producer-topic")
                    .description("producer-topic-description")
                    .payloadType(String.class)
                    .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                    .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                    .build();

            ConsumerData kafkaConsumerData = ConsumerData.builder()
                    .channelName("consumer-topic")
                    .description("consumer-topic-description")
                    .payloadType(String.class)
                    .operationBinding(Map.of("kafka", new KafkaOperationBinding()))
                    .messageBinding(Map.of("kafka", new KafkaMessageBinding()))
                    .build();

            return AsyncApiDocket.builder()
                    .info(info)
                    .basePackage("package")
                    .server(
                            "kafka",
                            Server.builder().protocol("kafka").url("kafka:9092").build())
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

        assertThat(actualInfo).isEqualTo(docket.getInfo());
        assertThat(actualInfo.getDescription()).isEqualTo("AsyncApiInfoDescriptionCustomizer2");
    }

    @Test
    void getAsyncAPI_servers_should_be_correct() {
        Map<String, Server> actualServers = asyncApiService.getAsyncAPI().getServers();

        assertThat(actualServers).isEqualTo(docket.getServers());
    }

    @Test
    void getAsyncAPI_producers_should_be_correct() {
        Map<String, ChannelItem> actualChannels = asyncApiService.getAsyncAPI().getChannels();

        assertThat(actualChannels).isNotEmpty().containsKey("producer-topic");

        final ChannelItem channel = actualChannels.get("producer-topic");
        assertThat(channel.getSubscribe()).isNotNull();
        final Message message = (Message) channel.getSubscribe().getMessage();
        assertThat(message.getDescription()).isNull();
        assertThat(message.getBindings()).isEqualTo(Map.of("kafka", new KafkaMessageBinding()));
    }

    @Test
    void getAsyncAPI_consumers_should_be_correct() {
        Map<String, ChannelItem> actualChannels = asyncApiService.getAsyncAPI().getChannels();

        assertThat(actualChannels).isNotEmpty().containsKey("consumer-topic");

        final ChannelItem channel = actualChannels.get("consumer-topic");
        assertThat(channel.getPublish()).isNotNull();
        final Message message = (Message) channel.getPublish().getMessage();
        assertThat(message.getDescription()).isNull();
        assertThat(message.getBindings()).isEqualTo(Map.of("kafka", new KafkaMessageBinding()));
    }

    @Order(TestDescriptionCustomizer.CUSTOMIZER_ORDER)
    public static class TestDescriptionCustomizer implements AsyncApiCustomizer {
        public static final int CUSTOMIZER_ORDER = 0;

        @Override
        public void customize(AsyncAPI asyncAPI) {
            asyncAPI.getInfo().setDescription("AsyncApiInfoDescriptionCustomizer");
        }
    }

    /**
     * Using a high @Order value, this customizer overwrites the previous customizers value
     */
    @Order(TestDescriptionCustomizer2.CUSTOMIZER_ORDER)
    public static class TestDescriptionCustomizer2 implements AsyncApiCustomizer {
        public static final int CUSTOMIZER_ORDER = TestDescriptionCustomizer.CUSTOMIZER_ORDER + 1;

        @Override
        public void customize(AsyncAPI asyncAPI) {
            asyncAPI.getInfo().setDescription("AsyncApiInfoDescriptionCustomizer2");
        }
    }
}
