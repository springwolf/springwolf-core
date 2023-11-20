// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload;

import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayloadClassExtractorTest {

    private final PayloadClassExtractor extractor = new PayloadClassExtractor();

    @Test
    void getPayloadType() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithString", String.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(String.class, result);
    }

    @Test
    void getPayloadTypeWithPayloadAnnotation() throws NoSuchMethodException {
        Method m =
                TestClass.class.getDeclaredMethod("consumeWithStringAndPayloadAnnotation", String.class, Integer.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(String.class, result);
    }

    @Test
    void getPayloadTypeWithListOfStrings() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithListOfStrings", List.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(String.class, result);
    }

    @Test
    void getPayloadTypeWithListOfInterfaces() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithListOfGenericClasses", List.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(Optional.class, result);
    }

    @Test
    void getPayloadTypeWithInterface() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithGenericClass", Optional.class);

        Class<?> result = extractor.extractFrom(m);

        assertEquals(Optional.class, result);
    }

    @Test
    void getPayloadTypeWithListOfStringExtends() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithListOfStringExtends", List.class);

        Class<?> result = extractor.extractFrom(m);

        // Unable to resolve optional<String>, fallback to root type list
        assertEquals(List.class, result);
    }

    @Test
    void getPayloadTypeWithListOfListOfString() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithListOfListOfString", List.class);

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
    void getPayloadTypeWithListOfMessageOfString() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithListOfMessageOfString", List.class);

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
        public void consumeWithStringAndPayloadAnnotation(@Payload String value, Integer value2) {}

        public void consumeWithString(String value) {}

        public void consumeWithGenericClass(Optional<String> value) {}

        public void consumeWithListOfStrings(List<String> value) {}

        public void consumeWithListOfGenericClasses(List<Optional<String>> value) {}

        public void consumeWithListOfStringExtends(List<? extends String> value) {}

        public void consumeWithListOfListOfString(List<List<String>> value) {}

        public void consumeWithMessageOfString(Message<String> value) {}

        public void consumeWithListOfMessageOfString(List<Message<String>> value) {}

        public void consumeWithCustomType(MyType value) {}

        public static class MyType extends ArrayList<String> {}
    }
}
