// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypeToClassConverterTest {

    private final TypeToClassConverter typeToClassConverter;

    {
        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setPayload(new SpringwolfConfigProperties.Payload());
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
    void getPayloadTypeWithMessageOfStringExtends() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfStringExtends", Message.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        // Unable to resolve optional<String>, fallback to root type Message
        assertThat(result).isEqualTo(Message.class);
    }

    @Test
    void getPayloadTypeWithMessageOfListOfString() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfListOfString", Message.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(String.class);
    }

    @Test
    void getPayloadTypeWithMessageOfString() throws NoSuchMethodException {
        Method m = TestClass.class.getDeclaredMethod("consumeWithMessageOfString", Message.class);

        Class<?> result = typeToClassConverter.extractClass(extractFrom(m));

        assertThat(result).isEqualTo(String.class);
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

    private Type extractFrom(Method method) {
        return method.getGenericParameterTypes()[0];
    }
}
