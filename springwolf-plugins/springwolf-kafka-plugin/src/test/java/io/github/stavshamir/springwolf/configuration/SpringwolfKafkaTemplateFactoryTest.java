package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpringwolfKafkaTemplateFactoryTest {

    @InjectMocks
    private SpringwolfKafkaTemplateFactory springwolfKafkaTemplateFactory;

    @Mock
    private AsyncApiDocketService asyncApiDocketService;

    private AsyncApiDocket.AsyncApiDocketBuilder builder = AsyncApiDocket.builder()
            .info(Info.builder()
                    .title("some-title")
                    .version("some-version")
                    .build());

    @Test
    public void testNoSpringwolfKafkaProducerCreatedIfNoKafkaInstanceConfigured() {
        Server noKafkaServer = Server.builder()
                .url("some-url")
                .protocol("not-kafka")
                .build();
        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(builder.servers(Collections.singletonMap("some-server", noKafkaServer)).build());

        Optional<KafkaTemplate<Object, Map<String, ?>>> kafkaTemplate = springwolfKafkaTemplateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isNotPresent();
    }

    @Test
    public void testNoSpringwolfKafkaProducerCreatedIfNoServersConfigured() {
        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(builder.servers(Collections.emptyMap()).build());

        Optional<KafkaTemplate<Object, Map<String, ?>>> kafkaTemplate = springwolfKafkaTemplateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isNotPresent();
    }

    @Test
    public void testSpringwolfKafkaProducerCreatedIfKafkaInstanceIsConfigured() {
        Server noKafkaServer = Server.builder()
                .url("some-url")
                .protocol("kafka")
                .build();
        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(builder.servers(Collections.singletonMap("some-server", noKafkaServer)).build());

        Optional<KafkaTemplate<Object, Map<String, ?>>> kafkaTemplate = springwolfKafkaTemplateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isPresent();
    }
}
