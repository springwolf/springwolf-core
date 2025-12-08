// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller;

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
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublishingPayloadCreatorTest {

    @Mock
    private ComponentsService componentsService;

    @Mock
    private JsonMapper jsonMapper;

    @InjectMocks
    private PublishingPayloadCreator publishingPayloadCreator;

    @Test
    void shouldResolveEmptyPayload() {
        // given
        String type = ObjectClass.class.getName();
        String payload = "";
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

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
        String type = Boolean.class.getName();
        String payload = "true";
        Boolean typed = true;
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.BOOLEAN).build())));
        when(jsonMapper.readValue(payload, Boolean.class)).thenReturn(typed);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(typed);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveIntegerPayload() throws Exception {
        // given
        String type = Integer.class.getName();
        String payload = "12345678";
        Long typed = 12345678L;
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.INTEGER).build())));
        when(jsonMapper.readValue(payload, Long.class)).thenReturn(typed);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(typed);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveLongPayload() throws Exception {
        // given
        String type = Long.class.getName();
        String payload = Long.valueOf(Long.MAX_VALUE).toString();
        Long typed = Long.MAX_VALUE;
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.INTEGER).build())));
        when(jsonMapper.readValue(payload, Long.class)).thenReturn(typed);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(typed);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveFloatPayload() throws Exception {
        // given
        String type = Float.class.getName();
        String payload = "12345678.123";
        Double typed = 12345678.123;
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.NUMBER).build())));
        when(jsonMapper.readValue(payload, Double.class)).thenReturn(typed);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(typed);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveDoublePayload() throws Exception {
        // given
        String type = Double.class.getName();
        String payload = Double.valueOf(Double.MAX_VALUE).toString();
        Double typed = Double.MAX_VALUE;
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.NUMBER).build())));
        when(jsonMapper.readValue(payload, Double.class)).thenReturn(typed);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(typed);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveObjectPayload() throws Exception {
        // given
        String type = ObjectClass.class.getName();
        String payload = "{\"value\":\"test\"}";
        ObjectClass typed = new ObjectClass("test");
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.OBJECT).build())));
        when(jsonMapper.readValue(payload, ObjectClass.class)).thenReturn(typed);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(typed);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldResolveStringPayload() throws Exception {
        // given
        String type = String.class.getName();
        String payload = "\"test\"";
        String typed = "test";
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.STRING).build())));
        when(jsonMapper.readValue(payload, String.class)).thenReturn(typed);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isEqualTo(typed);
        assertThat(result.errorMessage()).isEqualTo(Optional.empty());
    }

    @Test
    void shouldReturnEmptyPayloadForUnknownClass() {
        // given
        String type = "MyUnknownClass";
        String payload = "payload-data";
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
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
        String type = ObjectClass.class.getName();
        String payload = "---invalid-json---";
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
                        ComponentSchema.of(
                                SchemaObject.builder().type(SchemaType.OBJECT).build())));
        when(jsonMapper.readValue(payload, ObjectClass.class)).thenThrow(new JacksonException("invalid json") {});

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
        String type = ObjectClass.class.getName();
        String payload = "{}";
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        when(componentsService.getSchemas())
                .thenReturn(Map.of(
                        type,
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
    void shouldReturnEmptyPayloadForUnmatchedType() {
        // given
        String type = String.class.getName();
        String payload = "{\"value\":\"test\"}";
        MessageDto message = new MessageDto(Map.of(), Map.of(), type, payload);

        // when
        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);

        // then
        assertThat(result).isNotNull();
        assertThat(result.payload()).isNull();
        assertThat(result.errorMessage())
                .isEqualTo(Optional.of("Specified type java.lang.String is not a registered springwolf schema."));
    }

    record ObjectClass(String value) {}
}
