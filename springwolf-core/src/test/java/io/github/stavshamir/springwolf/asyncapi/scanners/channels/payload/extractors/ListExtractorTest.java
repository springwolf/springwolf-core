// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.extractors;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ListExtractorTest {

    private final ListExtractor listExtractor = new ListExtractor();

    @Test
    void getGenericType() {}

    @Test
    void canExtract() throws NoSuchMethodException {
        Method method = ClassWithListParameter.class.getDeclaredMethod("methodWithListParameter", List.class);
        Type genericParameterType = method.getGenericParameterTypes()[0];

        boolean canExtract = listExtractor.canExtract((ParameterizedType) genericParameterType);

        assertThat(canExtract).isTrue();
    }

    @Test
    void canNotExtract() throws NoSuchMethodException {
        Method method = ClassWithListParameter.class.getDeclaredMethod("methodWithSetParameter", Set.class);
        Type genericParameterType = method.getGenericParameterTypes()[0];

        boolean canExtract = listExtractor.canExtract((ParameterizedType) genericParameterType);

        assertThat(canExtract).isFalse();
    }

    class ClassWithListParameter {

        public void methodWithListParameter(List<String> stringList) {}

        public void methodWithSetParameter(Set<String> stringMap) {}
    }
}
