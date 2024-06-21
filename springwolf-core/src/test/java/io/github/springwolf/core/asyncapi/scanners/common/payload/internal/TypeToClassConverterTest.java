// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload.internal;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypeToClassConverterTest {

    private final TypeToClassConverter typeToClassConverter;

    {
        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setPayload(new SpringwolfConfigProperties.Payload());
        properties
                .getPayload()
                .setExtractableClasses(new HashMap<>(properties.getPayload().getExtractableClasses()));
        properties.getPayload().getExtractableClasses().put(TestClass.CustomPair.class.getName(), 1);
        typeToClassConverter = new TypeToClassConverter(properties);
    }

    @Test
    void getPayloadType() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithString", String.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(String.class);
    }

    @Test
    void getPayloadTypeWithMessageOfInterfaces() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfGenericClasses", Message.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(Collection.class);
    }

    @Test
    void getPayloadTypeWithInterface() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithGenericClass", Collection.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(Collection.class);
    }

    @Test
    void getPayloadTypeWithMessageOfListOfString() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfListOfString", Message.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(String.class);
    }

    @Test
    void getPayloadTypeWithMessageOfListOfCustomPair() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfCustomPair", Message.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        // payload is extracted from the second generic argument
        assertThat(result).isEqualTo(Double.class);
    }

    @Test
    void getPayloadTypeWithMessageOfString() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfString", Message.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(String.class);
    }

    @Test
    void getPayloadTypeWithCustomMessageExtendsInterface() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithCustomMessageExtendsInterface", List.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(TestClass.CustomMessage.class);
    }

    @Test
    void getPayloadTypeWithCustomMessagePairExtendsInterface() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithCustomMessagePairExtendsInterface", List.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        // payload is extracted from the second generic argument
        assertThat(result).isEqualTo(Double.class);
    }

    @Test
    void getPayloadTypeWithCustomMessageSuperInterface() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithCustomMessageSuperInterface", List.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(TestClass.CustomMessage.class);
    }

    @Test
    void getPayloadTypeWithCustomMessagePairSuperInterface() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithCustomMessagePairSuperInterface", List.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        // payload is extracted from the second generic argument
        assertThat(result).isEqualTo(Double.class);
    }

    @Test
    void getPayloadTypeWithPrimitive() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithPrimitive", int.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(int.class);
    }

    @Test
    void getPayloadTypeWithPrimitiveArray() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithPrimitiveArray", int[].class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(int.class);
    }

    @Test
    void getPayloadTypeWithListOfPrimitiveArray() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithCollectionOfPrimitiveArray", List.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(int.class);
    }

    @Test
    void getPayloadTypeWithCustomType() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithCustomType", TestClass.MyType.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(TestClass.MyType.class);
    }

    @Test
    void exceptionsAreTurnedIntoVoid() {
        ParameterizedType spiedType = mock(ParameterizedType.class);
        when(spiedType.getRawType()).thenThrow(new ClassCastException());

        Class<?> result = typeToClassConverter.extractClass(spiedType);

        assertThat(result).isEqualTo(Void.class);
    }

    public static class TestClass {

        public void consumeWithString(String value) {}

        public void consumeWithGenericClass(Collection<String> value) {}

        public void consumeWithMessageOfGenericClasses(Message<Collection<String>> value) {}

        public void consumeWithMessageOfListOfString(Message<List<String>> value) {}

        public void consumeWithMessageOfCustomPair(Message<CustomPair<Integer, Double>> value) {}

        public void consumeWithMessageOfString(Message<String> value) {}

        public void consumeWithCustomMessageExtendsInterface(List<? extends CustomMessage> value) {}

        public void consumeWithCustomMessagePairExtendsInterface(List<? extends CustomPair<Integer, Double>> value) {}

        public void consumeWithCustomMessageSuperInterface(List<? super CustomMessage> value) {}

        public void consumeWithCustomMessagePairSuperInterface(List<? super CustomPair<Integer, Double>> value) {}

        public void consumeWithPrimitive(int value) {}

        public void consumeWithPrimitiveArray(int[] value) {}

        public void consumeWithCollectionOfPrimitiveArray(List<int[]> value) {}

        public void consumeWithCustomType(MyType value) {}

        public static class MyType extends GenericMessage<String> {
            public MyType(String payload) {
                super(payload);
            }
        }

        public interface CustomPair<K, V> {}

        public interface CustomMessage {}
    }

    private Type extractFrom(Method method) {
        return method.getGenericParameterTypes()[0];
    }
}
