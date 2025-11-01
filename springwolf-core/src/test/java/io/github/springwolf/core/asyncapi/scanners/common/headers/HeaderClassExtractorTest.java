// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.converters.SchemaTitleModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.Header;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HeaderClassExtractorTest {

    private final SwaggerSchemaService schemaService = mock(SwaggerSchemaService.class);
    private final HeaderClassExtractor headerClassExtractor = new HeaderClassExtractor(schemaService);

    private final PayloadSchemaObject payloadSchemaName = new PayloadSchemaObject(
            "payloadSchemaName", String.class.getSimpleName(), ComponentSchema.of(new SchemaObject()));
    private final SchemaObject stringSchema =
            SchemaObject.builder().type(SchemaType.STRING).build();
    private final ComponentSchema stringSwaggerSchema =
            ComponentSchema.of(SchemaObject.builder().type(SchemaType.STRING).build());

    private static final SchemaTitleModelConverter titleModelConverter = new SchemaTitleModelConverter();

    @BeforeAll
    static void setupClass() {
        // make sure hat SpringWolf SchemaTitleModelConverter is registered with ModelConverters static registry.
        // this happens in Spring tests automatically but to run only this testclass, this is necessary:
        ModelConverters.getInstance().addConverter(titleModelConverter);
    }

    @AfterAll
    static void tearDownClass() {
        ModelConverters.getInstance().removeConverter(titleModelConverter);
    }

    @Test
    void getNoDocumentedHeaders() throws Exception {
        // given
        when(schemaService.postProcessSimpleSchema(String.class))
                .thenReturn(new SwaggerSchemaService.ExtractedSchemas(stringSwaggerSchema, Map.of()));

        // when
        Method m = TestClass.class.getDeclaredMethod("consumeWithoutHeadersAnnotation", String.class);
        val result = headerClassExtractor.extractHeader(m, payloadSchemaName);

        // then
        assertThat(result).isEqualTo(AsyncHeadersNotDocumented.NOT_DOCUMENTED);
    }

    @Test
    void getHeaderWithSingleHeaderAnnotation() throws Exception {
        // given
        when(schemaService.postProcessSimpleSchema(String.class))
                .thenReturn(new SwaggerSchemaService.ExtractedSchemas(stringSwaggerSchema, Map.of()));

        // when
        Method m = TestClass.class.getDeclaredMethod("consumeWithSingleHeaderAnnotation", String.class);
        val result = headerClassExtractor.extractHeader(m, payloadSchemaName);

        // then
        SchemaObject expectedHeaders = SchemaObject.builder()
                .type(SchemaType.OBJECT)
                .title("payloadSchemaNameHeaders")
                .properties(new HashMap<>())
                .build();
        expectedHeaders.getProperties().put("kafka_receivedMessageKey", ComponentSchema.of(stringSchema));

        assertThat(result).isEqualTo(expectedHeaders);
    }

    @Test
    void getHeaderWithMultipleHeaderAnnotation() throws Exception {
        // given
        when(schemaService.postProcessSimpleSchema(String.class))
                .thenReturn(new SwaggerSchemaService.ExtractedSchemas(stringSwaggerSchema, Map.of()));

        // when
        Method m = TestClass.class.getDeclaredMethod("consumeWithMultipleHeaderAnnotation", String.class, String.class);
        val result = headerClassExtractor.extractHeader(m, payloadSchemaName);

        // then
        SchemaObject expectedHeaders = SchemaObject.builder()
                .type(SchemaType.OBJECT)
                .title("payloadSchemaNameHeaders")
                .properties(new HashMap<>())
                .build();
        expectedHeaders.getProperties().put("kafka_receivedMessageKey", ComponentSchema.of(stringSchema));
        expectedHeaders.getProperties().put("non-exist", ComponentSchema.of(stringSchema));

        assertThat(result).isEqualTo(expectedHeaders);
    }

    public static class TestClass {

        public void consumeWithoutHeadersAnnotation(String simpleValue) {}

        public void consumeWithSingleHeaderAnnotation(@Header("kafka_receivedMessageKey") String key) {}

        public void consumeWithMultipleHeaderAnnotation(
                @Header("kafka_receivedMessageKey") String key,
                @Header(name = "non-exist", defaultValue = "default") String nonExistingHeader) {}
    }
}
