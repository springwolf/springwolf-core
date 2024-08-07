// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload.internal;

import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class PayloadClassExtractorTest {

    private final PayloadClassExtractor extractor = new PayloadClassExtractor();

    @Test
    void getPayloadType() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithString", String.class);

        Optional<Type> result = extractor.extractFrom(m);

        assertThat(result).hasValue(String.class);
    }

    @Test
    void getPayloadTypeWithPayloadAnnotation() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithPayloadAnnotation", String.class, Integer.class);

        Optional<Type> result = extractor.extractFrom(m);

        assertThat(result).hasValue(String.class);
    }

    @Test
    void getPayloadTypeWithoutPayloadAnnotation() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithoutPayloadAnnotation", String.class, Integer.class);

        try {
            extractor.extractFrom(m);
            fail();
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(IllegalArgumentException.class);
            assertThat(ex).hasMessageContaining("@Payload");
        }
    }

    @Test
    void getNoPayload() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithoutPayload");

        Optional<Type> result = extractor.extractFrom(m);

        assertThat(result).isEmpty();
    }

    public static class TestClass {
        public void consumeWithPayloadAnnotation(@Payload String value, Integer value2) {}

        public void consumeWithoutPayloadAnnotation(String value, Integer value2) {}

        public void consumeWithString(String value) {}

        public void consumeWithoutPayload() {}
    }
}
