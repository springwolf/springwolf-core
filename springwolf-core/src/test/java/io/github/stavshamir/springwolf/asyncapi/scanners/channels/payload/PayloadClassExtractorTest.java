// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload;

import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PayloadClassExtractorTest {

    private final PayloadClassExtractor extractor;

    {
        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setPayload(new SpringwolfConfigProperties.Payload());
        extractor = new PayloadClassExtractor(properties);
    }

    @Test
    void getPayloadType() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithString", String.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(String.class, result);
    }

    @Test
    void getPayloadTypeWithPayloadAnnotation() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithPayloadAnnotation", String.class, Integer.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(String.class, result);
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
    void getPayloadTypeWithMessageOfInterfaces() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfGenericClasses", Message.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(Collection.class, result);
    }

    @Test
    void getPayloadTypeWithInterface() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithGenericClass", Collection.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(Collection.class, result);
    }

    @Test
    void getPayloadTypeWithMessageOfStringExtends() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfStringExtends", Message.class);

        Class<?> result = extractor.extractFrom(m);

        // Unable to resolve optional<String>, fallback to root type Message
        assertEquals(Message.class, result);
    }

    @Test
    void getPayloadTypeWithMessageOfListOfString() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfListOfString", Message.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(String.class, result);
    }

    @Test
    void getPayloadTypeWithMessageOfString() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfString", Message.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(String.class, result);
    }

    @Test
    void getPayloadTypeWithCustomType() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithCustomType", TestClass.MyType.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(TestClass.MyType.class, result);
    }

    public static class TestClass {
        public void consumeWithPayloadAnnotation(@Payload String value, Integer value2) {}

        public void consumeWithoutPayloadAnnotation(String value, Integer value2) {}

        public void consumeWithString(String value) {}

        public void consumeWithGenericClass(Collection<String> value) {}

        public void consumeWithMessageOfGenericClasses(Message<Collection<String>> value) {}

        public void consumeWithMessageOfStringExtends(Message<? extends String> value) {}

        public void consumeWithMessageOfListOfString(Message<List<String>> value) {}

        public void consumeWithMessageOfString(Message<String> value) {}

        public void consumeWithCustomType(MyType value) {}

        public static class MyType extends GenericMessage<String> {
            public MyType(String payload) {
                super(payload);
            }
        }
    }
}
