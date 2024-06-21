// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.Header;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HeaderClassExtractorTest {

    private final SwaggerSchemaService schemaService = mock(SwaggerSchemaService.class);
    private final HeaderClassExtractor headerClassExtractor = new HeaderClassExtractor(schemaService);

    private final NamedSchemaObject payloadSchemaName = new NamedSchemaObject("payloadSchemaName", new SchemaObject());
    private final SchemaObject stringSchema =
            SchemaObject.builder().type("string").build();
    private final SchemaObject stringSwaggerSchema =
            SchemaObject.builder().type("string").build();

    @Test
    void getNoDocumentedHeaders() throws NoSuchMethodException {
        // given
        when(schemaService.extractSchema(String.class))
                .thenReturn(new SwaggerSchemaService.ExtractedSchemas("String", Map.of("String", stringSwaggerSchema)));

        // when
        Method m = TestClass.class.getDeclaredMethod("consumeWithoutHeadersAnnotation", String.class);
        val result = headerClassExtractor.extractHeader(m, payloadSchemaName);

        // then
        assertEquals(AsyncHeadersNotDocumented.NOT_DOCUMENTED, result);
    }

    @Test
    void getHeaderWithSingleHeaderAnnotation() throws NoSuchMethodException {
        // given
        when(schemaService.extractSchema(String.class))
                .thenReturn(new SwaggerSchemaService.ExtractedSchemas("String", Map.of("String", stringSwaggerSchema)));

        // when
        Method m = TestClass.class.getDeclaredMethod("consumeWithSingleHeaderAnnotation", String.class);
        val result = headerClassExtractor.extractHeader(m, payloadSchemaName);

        // then
        SchemaObject expectedHeaders = SchemaObject.builder()
                .title("payloadSchemaNameHeaders")
                .properties(new HashMap<>())
                .build();
        expectedHeaders.getProperties().put("kafka_receivedMessageKey", stringSchema);

        assertEquals(expectedHeaders, result);
    }

    @Test
    void getHeaderWithMultipleHeaderAnnotation() throws NoSuchMethodException {
        // given
        when(schemaService.extractSchema(String.class))
                .thenReturn(new SwaggerSchemaService.ExtractedSchemas("String", Map.of("String", stringSwaggerSchema)));

        // when
        Method m = TestClass.class.getDeclaredMethod("consumeWithMultipleHeaderAnnotation", String.class, String.class);
        val result = headerClassExtractor.extractHeader(m, payloadSchemaName);

        // then
        SchemaObject expectedHeaders = SchemaObject.builder()
                .title("payloadSchemaNameHeaders")
                .properties(new HashMap<>())
                .build();
        expectedHeaders.getProperties().put("kafka_receivedMessageKey", stringSchema);
        expectedHeaders.getProperties().put("non-exist", stringSchema);

        assertEquals(expectedHeaders, result);
    }

    public static class TestClass {

        public void consumeWithoutHeadersAnnotation(String simpleValue) {}

        public void consumeWithSingleHeaderAnnotation(@Header("kafka_receivedMessageKey") String key) {}

        public void consumeWithMultipleHeaderAnnotation(
                @Header("kafka_receivedMessageKey") String key,
                @Header(name = "non-exist", defaultValue = "default") String nonExistingHeader) {}
    }
}
