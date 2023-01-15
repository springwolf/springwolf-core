package io.github.stavshamir.springwolf.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SpringwolfKafkaProducerTest {

    private SpringwolfKafkaProducer springwolfKafkaProducer;

    @Mock
    private KafkaTemplate<Object, Map<String, ?>> kafkaTemplate;

    @Captor
    private ArgumentCaptor<ProducerRecord<Object, Map<String, ?>>> recordArgumentCaptor;

    @BeforeEach
    public void setUp() {
        springwolfKafkaProducer = new SpringwolfKafkaProducer(Optional.of(kafkaTemplate));
    }

    @Test
    public void testSpringwolfKafkaProducerIsNotEnabledWhenThereIsNoKafkaTemplateConfigured() {
        Optional<KafkaTemplate<Object, Map<String, ?>>> kafkaTemplateMock = Optional.empty();

        springwolfKafkaProducer = new SpringwolfKafkaProducer(kafkaTemplateMock);

        assertThat(springwolfKafkaProducer.isEnabled()).isFalse();
    }

    @Test
    public void testSendingKafkaMessageWithoutHeaders() {
        Map<String, Object> payload = Collections.singletonMap("some", "field");

        springwolfKafkaProducer.send("test-topic", null, payload);

        verify(kafkaTemplate).send(recordArgumentCaptor.capture());

        ProducerRecord<Object, Map<String, ?>> capturedRecord = recordArgumentCaptor.getValue();

        assertThat(capturedRecord.value()).isEqualTo(payload);
        assertThat(capturedRecord.topic()).isEqualTo("test-topic");

        List<Header> headersFromRecord = Lists.newArrayList(capturedRecord.headers().iterator());
        assertThat(headersFromRecord).isEmpty();
    }

    @Test
    public void testSendingKafkaMessageWithHeaders() {
        Map<String, Object> payload = Collections.singletonMap("some", "field");
        Map<String, String> headers = Collections.singletonMap("header-key", "header");

        springwolfKafkaProducer.send("test-topic", headers, payload);

        verify(kafkaTemplate).send(recordArgumentCaptor.capture());

        ProducerRecord<Object, Map<String, ?>> capturedRecord = recordArgumentCaptor.getValue();

        assertThat(capturedRecord.value()).isEqualTo(payload);
        assertThat(capturedRecord.topic()).isEqualTo("test-topic");

        List<Header> headersFromRecord = Lists.newArrayList(capturedRecord.headers().iterator());
        assertThat(headersFromRecord).hasSize(1);

        assertThat(headersFromRecord.get(0).key()).isEqualTo("header-key");
        assertThat(new String(headersFromRecord.get(0).value())).isEqualTo(headers.get("header-key"));
    }
}
