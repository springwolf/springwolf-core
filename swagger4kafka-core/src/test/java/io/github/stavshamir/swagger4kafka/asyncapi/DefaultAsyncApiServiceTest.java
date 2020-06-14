package io.github.stavshamir.swagger4kafka.asyncapi;

import io.github.stavshamir.swagger4kafka.asyncapi.examples.consumers.KafkaConsumerClass;
import io.github.stavshamir.swagger4kafka.asyncapi.types.Components;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.Channel;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.Operation;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.swagger4kafka.asyncapi.types.info.Info;
import io.github.stavshamir.swagger4kafka.asyncapi.types.server.Server;
import io.github.stavshamir.swagger4kafka.configuration.AsyncApiDocket;
import io.github.stavshamir.swagger4kafka.configuration.protocol.AsyncApiProtocolConfiguration;
import io.github.stavshamir.swagger4kafka.configuration.protocol.KafkaProtocolConfiguration;
import io.github.stavshamir.swagger4kafka.scanners.channels.KafkaChannelsScanner;
import io.github.stavshamir.swagger4kafka.scanners.components.DefaultComponentsScanner;
import io.github.stavshamir.swagger4kafka.schemas.DefaultSchemasService;
import io.github.stavshamir.swagger4kafka.schemas.SchemasService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        DefaultAsyncApiService.class,
        DefaultChannelsService.class,
        KafkaChannelsScanner.class,
        DefaultComponentsScanner.class,
        DefaultSchemasService.class
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

            AsyncApiProtocolConfiguration kafkaProtocol = KafkaProtocolConfiguration.builder()
                    .basePackage(KafkaConsumerClass.class.getPackage().getName())
                    .build();

            return AsyncApiDocket.builder()
                    .info(info)
                    .protocol(kafkaProtocol)
                    .server("kafka", Server.kafka().url("kafka:9092").build())
                    .build();
        }

    }

    @Autowired
    private AsyncApiDocket docket;

    @Autowired
    private SchemasService schemasService;

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
    public void getAsyncAPI_channels_should_be_correct() {
        Map<String, Channel> actualChannels = asyncApiService.getAsyncAPI().getChannels();

        assertThat(actualChannels).
                isEqualTo(kafkaChannels());
    }

    @Test
    public void getAsyncAPI_components_should_be_correct() {
        Components actualComponents = asyncApiService.getAsyncAPI().getComponents();

        Components components = Components.builder()
                .schemas(schemasService.getDefinitions())
                .build();

        assertThat(actualComponents).
                isEqualTo(components);
    }

    /**
     * Manually build the kafka channel of class KafkaConsumerClass
     */
    private Map<String, Channel> kafkaChannels() {
        Message message = Message.builder()
                .name(KafkaConsumerClass.ExamplePayload.class.getName())
                .title(KafkaConsumerClass.ExamplePayload.class.getSimpleName())
                .payload(PayloadReference.fromModelName(KafkaConsumerClass.ExamplePayload.class.getSimpleName()))
                .build();
        Operation operation = Operation.builder().message(message).build();
        Channel channel = Channel.builder().subscribe(operation).build();
        return Collections.singletonMap(KafkaConsumerClass.TOPIC, channel);
    }

}