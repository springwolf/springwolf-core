// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.controller.dtos.MessageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublishingPayloadCreatorTest {

    @Mock
    private ComponentsService componentsService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PublishingPayloadCreator publishingPayloadCreator;

    @Test
    void shouldResolveEmptyPayload() {
        // given
        String payloadType = ObjectClass.class.getName();
        String payload = "";
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isNull();
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveBooleanPayload() throws Exception {
        // given
        String payloadType = Boolean.class.getName();
        String payload = "true";
        Boolean payloadTyped = true;
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.BOOLEAN).build())));
        when(objectMapper.readValue(payload, Boolean.class)).thenReturn(payloadTyped);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(payloadTyped);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveIntegerPayload() throws Exception {
        // given
        String payloadType = Integer.class.getName();
        String payload = "12345678";
        Long payloadTyped = 12345678L;
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.INTEGER).build())));
        when(objectMapper.readValue(payload, Long.class)).thenReturn(payloadTyped);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(payloadTyped);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveLongPayload() throws Exception {
        // given
        String payloadType = Long.class.getName();
        String payload = Long.valueOf(Long.MAX_VALUE).toString();
        Long payloadTyped = Long.MAX_VALUE;
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.INTEGER).build())));
        when(objectMapper.readValue(payload, Long.class)).thenReturn(payloadTyped);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(payloadTyped);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveFloatPayload() throws Exception {
        // given
        String payloadType = Float.class.getName();
        String payload = "12345678.123";
        Double payloadTyped = 12345678.123;
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.NUMBER).build())));
        when(objectMapper.readValue(payload, Double.class)).thenReturn(payloadTyped);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(payloadTyped);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveDoublePayload() throws Exception {
        // given
        String payloadType = Double.class.getName();
        String payload = Double.valueOf(Double.MAX_VALUE).toString();
        Double payloadTyped = Double.MAX_VALUE;
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.NUMBER).build())));
        when(objectMapper.readValue(payload, Double.class)).thenReturn(payloadTyped);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(payloadTyped);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveObjectPayload() throws Exception {
        // given
        String payloadType = ObjectClass.class.getName();
        String payload = "{\"value\":\"test\"}";
        ObjectClass payloadTyped = new ObjectClass("test");
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.OBJECT).build())));
        when(objectMapper.readValue(payload, ObjectClass.class)).thenReturn(payloadTyped);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(payloadTyped);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveStringPayload() throws Exception {
        // given
        String payloadType = String.class.getName();
        String payload = "\"test\"";
        String payloadTyped = "test";
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.STRING).build())));
        when(objectMapper.readValue(payload, String.class)).thenReturn(payloadTyped);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(payloadTyped);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldReturnEmptyPayloadForUnknownClass() {
        // given
        String payloadType = "MyUnknownClass";
        String payload = "payload-data";
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.OBJECT).build())));

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isNull();
        assertThat(result.errorMessage())
                .isEqualTo(Optional.of("Unable to create payload MyUnknownClass from data: " + payload));
    }

    @Test
    void shouldReturnEmptyPayloadForInvalidJson() throws Exception {
        // given
        String payloadType = ObjectClass.class.getName();
        String payload = "---invalid-json---";
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.OBJECT).build())));
        when(objectMapper.readValue(payload, ObjectClass.class))
                .thenThrow(new JsonProcessingException("invalid json") {});

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isNull();
        assertThat(result.errorMessage())
                .isEqualTo(Optional.of(
                        "Unable to create payload io.github.springwolf.core.controller.PublishingPayloadCreatorTest$ObjectClass from data: "
                                + payload));
    }

    @Test
    void shouldReturnEmptyPayloadForUnsupportedSchemaType() {
        // given
        String payloadType = ObjectClass.class.getName();
        String payload = "{}";
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        payloadType,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.ARRAY).build())));

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isNull();
        assertThat(result.errorMessage())
                .isEqualTo(Optional.of(
                        "Unable to create payload io.github.springwolf.core.controller.PublishingPayloadCreatorTest$ObjectClass from data: "
                                + payload));
    }

    @Test
    void shouldReturnEmptyPayloadForUnmatchedPayloadType() {
        // given
        String payloadType = String.class.getName();
        String payload = "{\"value\":\"test\"}";
        MessageDto message = new MessageDto(Map.of(), Map.of(), payloadType, payload);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isNull();
        assertThat(result.errorMessage())
                .isEqualTo(
                        Optional.of("Specified payloadType java.lang.String is not a registered springwolf schema."));
    }

    record ObjectClass(String value) {}
}
