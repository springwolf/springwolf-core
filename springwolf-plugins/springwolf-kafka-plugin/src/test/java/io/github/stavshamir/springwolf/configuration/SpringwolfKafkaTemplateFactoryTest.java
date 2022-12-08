package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2.model.server.Server;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpringwolfKafkaTemplateFactoryTest {

    @InjectMocks
    private  SpringwolfKafkaTemplateFactory springwolfKafkaTemplateFactory;

    @Mock
    private AsyncApiDocket asyncApiDocket;

    @Test
    public void testNoSpringwolfKafkaProducerCreatedIfNoKafkaInstanceConfigured() {
        Server noKafkaServer = Server.builder()
                .url("some-url")
                .protocol("not-kafka")
                .build();
        when(asyncApiDocket.getServers()).thenReturn(Collections.singletonMap("some-server", noKafkaServer));

        Optional<KafkaTemplate<Object, Map<String, ?>>> kafkaTemplate = springwolfKafkaTemplateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isNotPresent();
    }

    @Test
    public void testNoSpringwolfKafkaProducerCreatedIfNoServersConfigured() {
        when(asyncApiDocket.getServers()).thenReturn(Collections.emptyMap());

        Optional<KafkaTemplate<Object, Map<String, ?>>> kafkaTemplate = springwolfKafkaTemplateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isNotPresent();
    }

    @Test
    public void testSpringwolfKafkaProducerCreatedIfKafkaInstanceIsConfigured() {
        Server noKafkaServer = Server.builder()
                .url("some-url")
                .protocol("kafka")
                .build();
        when(asyncApiDocket.getServers()).thenReturn(Collections.singletonMap("some-server", noKafkaServer));

        Optional<KafkaTemplate<Object, Map<String, ?>>> kafkaTemplate = springwolfKafkaTemplateFactory.buildKafkaTemplate();

        assertThat(kafkaTemplate).isPresent();
    }
}
