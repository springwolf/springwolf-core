package io.github.stavshamir.springwolf.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpringwolfKafkaProducerTest {

    private SpringwolfKafkaProducer springwolfKafkaProducer;

    @Mock
    KafkaTemplate<Object, Object> kafkaTemplate;

    @Captor
    private ArgumentCaptor<ProducerRecord<Object, Object>> recordArgumentCaptor;

    @BeforeEach
    void setUp() {
        springwolfKafkaProducer = new SpringwolfKafkaProducer(Optional.of(kafkaTemplate));
    }

    @Test
    void testSpringwolfKafkaProducerIsNotEnabledWhenThereIsNoKafkaTemplateConfigured() {
        Optional<KafkaTemplate<Object, Object>> kafkaTemplateMock = Optional.empty();

        springwolfKafkaProducer = new SpringwolfKafkaProducer(kafkaTemplateMock);

        assertThat(springwolfKafkaProducer.isEnabled()).isFalse();
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSendingKafkaMessageWithoutHeaders() {
        CompletableFuture<SendResult<Object, Object>> future = new CompletableFuture<>();
        when(kafkaTemplate.send(ArgumentMatchers.<ProducerRecord<Object, Object>>any())).thenReturn(future);
        future.complete(mock(SendResult.class));

        Map<String, Object> payload = Collections.singletonMap("some", "field");

        springwolfKafkaProducer.send("test-topic", null, null, payload);

        verify(kafkaTemplate).send(recordArgumentCaptor.capture());

        ProducerRecord<Object, Object> capturedRecord = recordArgumentCaptor.getValue();

        assertThat(capturedRecord.value()).isEqualTo(payload);
        assertThat(capturedRecord.topic()).isEqualTo("test-topic");

        List<Header> headersFromRecord = Lists.newArrayList(capturedRecord.headers().iterator());
        assertThat(headersFromRecord).isEmpty();
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSendingKafkaMessageWithHeaders() {
        CompletableFuture<SendResult<Object, Object>> future = new CompletableFuture<>();
        when(kafkaTemplate.send(ArgumentMatchers.<ProducerRecord<Object, Object>>any())).thenReturn(future);
        future.complete(mock(SendResult.class));

        Map<String, Object> payload = Collections.singletonMap("some", "field");
        Map<String, String> headers = Collections.singletonMap("header-key", "header");

        springwolfKafkaProducer.send("test-topic", null, headers, payload);

        verify(kafkaTemplate).send(recordArgumentCaptor.capture());

        ProducerRecord<Object, Object> capturedRecord = recordArgumentCaptor.getValue();

        assertThat(capturedRecord.value()).isEqualTo(payload);
        assertThat(capturedRecord.topic()).isEqualTo("test-topic");

        List<Header> headersFromRecord = Lists.newArrayList(capturedRecord.headers().iterator());
        assertThat(headersFromRecord).hasSize(1);

        assertThat(headersFromRecord.get(0).key()).isEqualTo("header-key");
        assertThat(new String(headersFromRecord.get(0).value())).isEqualTo(headers.get("header-key"));
    }
}
