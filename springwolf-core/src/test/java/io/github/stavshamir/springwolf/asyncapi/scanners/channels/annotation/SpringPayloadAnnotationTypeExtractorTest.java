// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpringPayloadAnnotationTypeExtractorTest {

    @Test
    void getPayloadType() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithString", String.class);

        Class<?> result = SpringPayloadAnnotationTypeExtractor.getPayloadType(m);

        assertEquals(String.class, result);
    }

    @Test
    void getPayloadTypeWithPayloadAnnotation() throws NoSuchMethodException {
        Method m =
                TestClass.class.getDeclaredMethod("consumeWithStringAndPayloadAnnotation", String.class, Integer.class);

        Class<?> result = SpringPayloadAnnotationTypeExtractor.getPayloadType(m);

        assertEquals(String.class, result);
    }

    @Test
    void getPayloadTypeWithListOfStrings() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithListOfStrings", List.class);

        Class<?> result = SpringPayloadAnnotationTypeExtractor.getPayloadType(m);

        assertEquals(String.class, result);
    }

    @Test
    void getPayloadTypeWithListOfInterfaces() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithListOfGenericClasses", List.class);

        Class<?> result = SpringPayloadAnnotationTypeExtractor.getPayloadType(m);

        // Unable to resolve optional<String>, fallback to root type list
        assertEquals(List.class, result);
    }

    @Test
    void getPayloadTypeWithInterface() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithGenericClass", Optional.class);

        Class<?> result = SpringPayloadAnnotationTypeExtractor.getPayloadType(m);

        assertEquals(Optional.class, result);
    }

    @Test
    void getPayloadTypeWithListOfStringExtends() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithListOfStringExtends", List.class);

        Class<?> result = SpringPayloadAnnotationTypeExtractor.getPayloadType(m);

        // Unable to resolve optional<String>, fallback to root type list
        assertEquals(List.class, result);
    }

    @Test
    void getPayloadTypeWithCustomType() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithCustomType", TestClass.MyType.class);

        Class<?> result = SpringPayloadAnnotationTypeExtractor.getPayloadType(m);

        assertEquals(TestClass.MyType.class, result);
    }

    public static class TestClass {
        public void consumeWithStringAndPayloadAnnotation(@Payload String value, Integer value2) {}

        public void consumeWithString(String value) {}

        public void consumeWithGenericClass(Optional<String> value) {}

        public void consumeWithListOfStrings(List<String> value) {}

        public void consumeWithListOfGenericClasses(List<Optional<String>> value) {}

        public void consumeWithListOfStringExtends(List<? extends String> value) {}

        public void consumeWithCustomType(MyType value) {}

        public static class MyType extends ArrayList<String> {}
    }
}
