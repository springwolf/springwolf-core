// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaders;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersNotDocumented;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeadersClassExtractorTest {

    @Test
    void getNoDocumentedHeaders() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithoutHeadersAnnotation", String.class);

        var result = HeaderClassExtractor.extractFrom(m, String.class);

        // Expected
        assertEquals(AsyncHeadersNotDocumented.NOT_DOCUMENTED, result);
    }

    @Test
    void getHeaderWithSingleHeaderAnnotation() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithSingleHeaderAnnotation", String.class);

        var result = HeaderClassExtractor.extractFrom(m, String.class);

        // Expected
        AsyncHeaders expectedHeaders = new AsyncHeaders("");
        expectedHeaders.put("kafka_receivedMessageKey", new StringSchema());

        assertEquals(expectedHeaders, result);
    }

    @Test
    void getHeaderWithMultipleHeaderAnnotation() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMultipleHeaderAnnotation", String.class, String.class);

        var result = HeaderClassExtractor.extractFrom(m, String.class);

        // Expected
        AsyncHeaders expectedHeaders = new AsyncHeaders("");
        expectedHeaders.put("kafka_receivedMessageKey", new StringSchema());
        expectedHeaders.put("non-exist", new StringSchema());

        assertEquals(expectedHeaders, result);
    }

    @Test
    void getHeadersWithHeadersAnnotation() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithHeadersAnnotation", MessageHeaders.class);

        var result = HeaderClassExtractor.extractFrom(m, String.class);

        // Expected
        AsyncHeaders expectedHeaders = new AsyncHeaders("");
        expectedHeaders.put("headers", new MapSchema());

        assertEquals(expectedHeaders, result);
    }

    @Test
    void getHeadersWithAllHeadersAnnotation() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod(
                "consumeWithAllHeadersAnnotation", String.class, String.class, MessageHeaders.class);

        var result = HeaderClassExtractor.extractFrom(m, String.class);

        // Expected
        AsyncHeaders expectedHeaders = new AsyncHeaders("");
        expectedHeaders.put("kafka_receivedMessageKey", new StringSchema());
        expectedHeaders.put("non-exist", new StringSchema());
        expectedHeaders.put("headers", new MapSchema());

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

        public void consumeWithHeadersAnnotation(@Headers MessageHeaders headers) {}

        public void consumeWithAllHeadersAnnotation(
                @Header("kafka_receivedMessageKey") String key,
                @Header(name = "non-exist", defaultValue = "default") String nonExistingHeader,
                @Headers MessageHeaders headers) {}
    }
}
