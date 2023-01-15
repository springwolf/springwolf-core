package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.dtos.KafkaMessageDto;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpringwolfKafkaControllerTest {

    @InjectMocks
    private SpringwolfKafkaController springwolfKafkaController;

    @Mock
    private SpringwolfKafkaProducer springwolfKafkaProducer;

    @Captor
    private ArgumentCaptor<Map<String, ?>> payloadCaptor;

    @Captor
    private ArgumentCaptor<Map<String, String>> headerCaptor;

    @Test
    public void testControllerShouldReturnBadRequestIfPayloadIsEmpty() {
        try {
            springwolfKafkaController.publish("test-topic", new KafkaMessageDto(null, null));
            failBecauseExceptionWasNotThrown(ResponseStatusException.class);
        } catch (ResponseStatusException e) {
            assertThat(e.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
            verifyZeroInteractions(springwolfKafkaProducer);
        }
    }

    @Test
    public void testControllerShouldReturnNotFoundIfNoKafkaProducerIsEnabled() {
        when(springwolfKafkaProducer.isEnabled()).thenReturn(false);

        Map<String, String> payload = Collections.singletonMap("some-key", "some-value");
        KafkaMessageDto messageToPublish = new KafkaMessageDto(null, payload);

        try {
            springwolfKafkaController.publish("test-topic", messageToPublish);
            failBecauseExceptionWasNotThrown(ResponseStatusException.class);
        } catch (ResponseStatusException e) {
            assertThat(e.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testControllerShouldCallKafkaProducerIfOnlyPayloadIsSend() {
        when(springwolfKafkaProducer.isEnabled()).thenReturn(true);

        Map<String, String> payload = Collections.singletonMap("some-key", "some-value");
        KafkaMessageDto messageToPublish = new KafkaMessageDto(null, payload);

        springwolfKafkaController.publish("test-topic", messageToPublish);

        verify(springwolfKafkaProducer).send(eq("test-topic"), isNull(), payloadCaptor.capture());

        assertThat(payloadCaptor.getValue()).isEqualTo(payload);
    }

    @Test
    public void testControllerShouldCallKafkaProducerIfPayloadAndHeadersAreSend() {
        when(springwolfKafkaProducer.isEnabled()).thenReturn(true);

        Map<String, String> headers = Collections.singletonMap("some-header-key", "some-header-value");
        Map<String, String> payload = Collections.singletonMap("some-payload-key", "some-payload-value");

        KafkaMessageDto messageToPublish = new KafkaMessageDto(headers, payload);

        springwolfKafkaController.publish("test-topic", messageToPublish);

        verify(springwolfKafkaProducer).send(eq("test-topic"), headerCaptor.capture(), payloadCaptor.capture());

        assertThat(headerCaptor.getValue()).isEqualTo(headers);
        assertThat(payloadCaptor.getValue()).isEqualTo(payload);
    }
}
