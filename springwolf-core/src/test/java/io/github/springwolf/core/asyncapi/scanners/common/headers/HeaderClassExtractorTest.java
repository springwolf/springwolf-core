// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.TypeToClassConverter;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.Header;

import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeaderClassExtractorTest {
    private final TypeToClassConverter typeToClassConverter =
            new TypeToClassConverter(new SpringwolfConfigProperties());
    private final HeaderClassExtractor headerClassExtractor = new HeaderClassExtractor(typeToClassConverter);

    private final NamedSchemaObject payloadSchemaName = new NamedSchemaObject("payloadSchemaName", new SchemaObject());
    private final SchemaObject stringSchema =
            SchemaObject.builder().type("string").build();

    @Test
    void getNoDocumentedHeaders() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithoutHeadersAnnotation", String.class);

        val result = headerClassExtractor.extractFrom(m, payloadSchemaName);

        // then
        assertEquals(AsyncHeadersNotDocumented.NOT_DOCUMENTED, result);
    }

    @Test
    void getHeaderWithSingleHeaderAnnotation() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithSingleHeaderAnnotation", String.class);

        val result = headerClassExtractor.extractFrom(m, payloadSchemaName);

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
        Method m = TestClass.class.getDeclaredMethod("consumeWithMultipleHeaderAnnotation", String.class, String.class);

        val result = headerClassExtractor.extractFrom(m, payloadSchemaName);

        // then
        SchemaObject expectedHeaders = SchemaObject.builder()
                .title("payloadSchemaNameHeaders")
                .properties(new HashMap<>())
                .build();
        expectedHeaders.getProperties().put("kafka_receivedMessageKey", stringSchema);
        expectedHeaders.getProperties().put("non-exist", stringSchema);

        assertEquals(expectedHeaders, result);
    }

    // FIXME: We should have more tests
    //   Number, Integer, Boolean and Array Header types
    //   Map type headers can be tricky, since during the parsing process we don't have access to the keys

    public static class TestClass {

        public void consumeWithoutHeadersAnnotation(String simpleValue) {}

        public void consumeWithSingleHeaderAnnotation(@Header("kafka_receivedMessageKey") String key) {}

        public void consumeWithMultipleHeaderAnnotation(
                @Header("kafka_receivedMessageKey") String key,
                @Header(name = "non-exist", defaultValue = "default") String nonExistingHeader) {}
    }
}
