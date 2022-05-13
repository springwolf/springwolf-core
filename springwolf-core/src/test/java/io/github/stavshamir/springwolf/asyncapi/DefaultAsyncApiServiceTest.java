package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ProducerChannelScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.DefaultClassPathComponentsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        DefaultAsyncApiService.class,
        DefaultChannelsService.class,
        DefaultSchemasService.class,
        ProducerChannelScanner.class
})
@Import(DefaultAsyncApiServiceTest.DefaultAsyncApiServiceTestConfiguration.class)
public class DefaultAsyncApiServiceTest {

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
                    .payloadType(String.class)
                    .binding(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                    .build();

            return AsyncApiDocket.builder()
                    .info(info)
                    .basePackage("package")
                    .server("kafka", Server.builder().protocol("kafka").url("kafka:9092").build())
                    .producer(kafkaProducerData)
                    .build();
        }

    }

    @Autowired
    private AsyncApiDocket docket;

    @Autowired
    private DefaultAsyncApiService asyncApiService;

    @Test
    public void getAsyncAPI_info_should_be_correct() {
        Info actualInfo = asyncApiService.getAsyncAPI().getInfo();

        assertThat(actualInfo).
                isEqualTo(docket.getInfo());
    }

    @Test
    public void getAsyncAPI_servers_should_be_correct() {
        Map<String, Server> actualServers = asyncApiService.getAsyncAPI().getServers();

        assertThat(actualServers).
                isEqualTo(docket.getServers());
    }

    @Test
    public void getAsyncAPI_producers_should_be_correct() {
        Map<String, ChannelItem> actualChannels = asyncApiService.getAsyncAPI().getChannels();

        assertThat(actualChannels)
                .isNotEmpty()
                .containsKey("producer-topic");

        final ChannelItem channel = actualChannels.get("producer-topic");
        assertThat(channel.getSubscribe()).isNotNull();
    }

}
