package io.github.stavshamir.swagger4kafka.asyncapi;

import io.github.stavshamir.swagger4kafka.asyncapi.examples.consumers.KafkaConsumerClass;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.Channel;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.Operation;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.swagger4kafka.asyncapi.types.info.Info;
import io.github.stavshamir.swagger4kafka.configuration.AsyncApiDocket;
import io.github.stavshamir.swagger4kafka.configuration.protocol.AsyncApiProtocolConfiguration;
import io.github.stavshamir.swagger4kafka.configuration.protocol.KafkaProtocolConfiguration;
import io.github.stavshamir.swagger4kafka.asyncapi.scanners.channels.KafkaChannelsScanner;
import io.github.stavshamir.swagger4kafka.asyncapi.scanners.components.DefaultComponentsScanner;
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
        DefaultChannelsService.class,
        KafkaChannelsScanner.class,
        DefaultComponentsScanner.class,
        DefaultSchemasService.class
})
@Import(DefaultChannelsServiceTest.DefaultChannelsServiceTestConfiguration.class)
public class DefaultChannelsServiceTest {

    @TestConfiguration
    public static class DefaultChannelsServiceTestConfiguration {

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
                    .build();
        }

    }

    @Autowired
    private DefaultChannelsService defaultChannelsService;

    @Autowired
    private SchemasService schemasService;

    @Test
    public void getChannels() {
        Map<String, Channel> actualChannels = defaultChannelsService.getChannels();

        assertThat(actualChannels)
                .containsAllEntriesOf(kafkaChannels());

        assertThat(schemasService.getDefinitions())
                .containsKeys(KafkaConsumerClass.ExamplePayload.class.getSimpleName());
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