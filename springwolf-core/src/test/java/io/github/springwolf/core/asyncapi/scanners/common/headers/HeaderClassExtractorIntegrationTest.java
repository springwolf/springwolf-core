// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.schemas.ModelConvertersProvider;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.asyncapi.schemas.converters.SchemaTitleModelConverter;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.converter.ModelConverters;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.Header;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HeaderClassExtractorIntegrationTest {

    private static final SchemaTitleModelConverter titleModelConverter = new SchemaTitleModelConverter();

    private final SwaggerSchemaService schemaService = new SwaggerSchemaService(
            List.of(),
            new SwaggerSchemaUtil(),
            new SpringwolfConfigProperties(),
            new ModelConvertersProvider(List.of(titleModelConverter)));
    private final HeaderClassExtractor headerClassExtractor = new HeaderClassExtractor(schemaService);

    private final PayloadSchemaObject payloadSchemaName = new PayloadSchemaObject(
            "payloadSchemaName", String.class.getSimpleName(), ComponentSchema.of(new SchemaObject()));
    private final SchemaObject stringSchema =
            SchemaObject.builder().type(SchemaType.STRING).build();

    //    @BeforeAll
    //    static void setupClass() {
    //        // make sure hat SpringWolf SchemaTitleModelConverter is registered with ModelConverters static registry.
    //        // this happens in Spring tests automatically but to run only this testclass, this is necessary:
    //        ModelConverters.getInstance().addConverter(titleModelConverter);
    //    }

    @AfterAll
    static void tearDownClass() {
        ModelConverters.getInstance().removeConverter(titleModelConverter);
    }

    @Test
    void getNoDocumentedHeaders() throws Exception {
        // when
        Method m = TestClass.class.getDeclaredMethod("consumeWithoutHeadersAnnotation", String.class);
        val result = headerClassExtractor.extractHeader(m, payloadSchemaName);

        // then
        assertThat(result).isEqualTo(AsyncHeadersNotDocumented.NOT_DOCUMENTED);
    }

    @Test
    void getHeaderWithSingleHeaderAnnotation() throws Exception {
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

    @Test
    void getHeaderWithObjectHeaderAnnotation() throws Exception {
        // when
        Method m = TestClass.class.getDeclaredMethod("consumeWithObjectHeaderAnnotation", TestClass.MyHeader.class);
        val result = headerClassExtractor.extractHeader(m, payloadSchemaName);

        // then
        SchemaObject expectedHeaders = SchemaObject.builder()
                .type(SchemaType.OBJECT)
                .title("payloadSchemaNameHeaders")
                .properties(new HashMap<>())
                .build();
        expectedHeaders
                .getProperties()
                .put(
                        "myHeader",
                        ComponentSchema.of(SchemaObject.builder()
                                .type(SchemaType.OBJECT)
                                .title("MyHeader")
                                .properties(Map.of("key", ComponentSchema.of(stringSchema)))
                                .build()));

        assertThat(result).isEqualTo(expectedHeaders);
    }

    @Test
    void getHeaderWithNestedHeaderAnnotation() throws Exception {
        // when
        Method m = TestClass.class.getDeclaredMethod(
                "consumeWithNestedObjectHeaderAnnotation", TestClass.MyNestedHeader.class);
        val result = headerClassExtractor.extractHeader(m, payloadSchemaName);

        // then
        assertThat(result).isEqualTo(AsyncHeadersNotDocumented.NOT_DOCUMENTED); // currently not supported
    }

    public static class TestClass {

        public void consumeWithoutHeadersAnnotation(String simpleValue) {}

        public void consumeWithSingleHeaderAnnotation(@Header("kafka_receivedMessageKey") String key) {}

        public void consumeWithMultipleHeaderAnnotation(
                @Header("kafka_receivedMessageKey") String key,
                @Header(name = "non-exist", defaultValue = "default") String nonExistingHeader) {}

        public void consumeWithObjectHeaderAnnotation(@Header("myHeader") MyHeader key) {}

        public void consumeWithNestedObjectHeaderAnnotation(@Header("myNestedHeader") MyNestedHeader key) {}

        static class MyHeader {
            public String key;
        }

        static class MyNestedHeader {
            public MyHeader header;
        }
    }
}
