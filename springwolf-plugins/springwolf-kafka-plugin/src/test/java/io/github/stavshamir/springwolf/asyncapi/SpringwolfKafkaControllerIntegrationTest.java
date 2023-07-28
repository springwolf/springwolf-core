package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpringwolfKafkaController.class)
@ContextConfiguration(classes = {SpringwolfKafkaController.class, SpringwolfKafkaProducer.class})
@TestPropertySource(properties = {"springwolf.plugin.kafka.publishing.enabled=true"})
class SpringwolfKafkaControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpringwolfKafkaProducer springwolfKafkaProducer;

    @Captor
    private ArgumentCaptor<Map<String, ?>> payloadCaptor;

    @Captor
    private ArgumentCaptor<Map<String, String>> headerCaptor;

    @Test
    void testControllerShouldReturnBadRequestIfPayloadIsEmpty() {
        try {
            String content = "{\"bindings\": null, \"headers\": null, \"payload\": null }";
            mvc.perform(post("/springwolf/kafka/publish?topic=test-topic")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            verifyNoInteractions(springwolfKafkaProducer);
        }
    }

    @Test
    void testControllerShouldReturnNotFoundIfNoKafkaProducerIsEnabled() throws Exception {
        when(springwolfKafkaProducer.isEnabled()).thenReturn(false);

        String content = "{\"bindings\": null, \"headers\": null, \"payload\": { \"some-key\" : \"some-value\" }}";
        mvc.perform(post("/springwolf/kafka/publish?topic=test-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isNotFound());
    }

    @Test
    void testControllerShouldCallKafkaProducerIfOnlyPayloadIsSend() throws Exception {
        when(springwolfKafkaProducer.isEnabled()).thenReturn(true);

        String content = "{\"bindings\": null, \"headers\": null, \"payload\": { \"some-key\" : \"some-value\" }}";

        mvc.perform(post("/springwolf/kafka/publish")
                        .param("topic", "test-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        verify(springwolfKafkaProducer).send(eq("test-topic"), isNull(), isNull(), payloadCaptor.capture());

        assertThat(payloadCaptor.getValue()).isEqualTo(singletonMap("some-key", "some-value"));
    }

    @Test
    void testControllerShouldCallKafkaProducerIfPayloadAndHeadersAreSend() throws Exception {
        when(springwolfKafkaProducer.isEnabled()).thenReturn(true);

        String content =
                "{\"bindings\": null, \"headers\": { \"some-header-key\" : \"some-header-value\" }, \"payload\": { \"some-payload-key\" : \"some-payload-value\" }}";

        mvc.perform(post("/springwolf/kafka/publish?topic=test-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        verify(springwolfKafkaProducer)
                .send(eq("test-topic"), isNull(), headerCaptor.capture(), payloadCaptor.capture());

        assertThat(headerCaptor.getValue()).isEqualTo(singletonMap("some-header-key", "some-header-value"));
        assertThat(payloadCaptor.getValue()).isEqualTo(singletonMap("some-payload-key", "some-payload-value"));
    }

    @Test
    void testControllerShouldCallKafkaProducerIfPayloadAndHeadersAndBindingsAreSend() throws Exception {
        when(springwolfKafkaProducer.isEnabled()).thenReturn(true);

        String content =
                "{\"bindings\": {\"key\": \"kafka-key-value\"}, \"headers\": { \"some-header-key\" : \"some-header-value\" }, \"payload\": { \"some-payload-key\" : \"some-payload-value\" }}";

        mvc.perform(post("/springwolf/kafka/publish?topic=test-topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        verify(springwolfKafkaProducer)
                .send(eq("test-topic"), eq("kafka-key-value"), headerCaptor.capture(), payloadCaptor.capture());

        assertThat(headerCaptor.getValue()).isEqualTo(singletonMap("some-header-key", "some-header-value"));
        assertThat(payloadCaptor.getValue()).isEqualTo(singletonMap("some-payload-key", "some-payload-value"));
    }
}
