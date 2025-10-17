// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.generic_binding.annotation.processor;

import io.github.springwolf.addons.generic_binding.annotation.AsyncGenericOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.StringValueResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AsyncGenericOperationBindingProcessorTest {

    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    private final AsyncGenericOperationBindingProcessor processor =
            new AsyncGenericOperationBindingProcessor(stringValueResolver);

    @Test
    void classWithoutAnnotation() {
        // when
        List<ProcessedOperationBinding> result = getProcessedOperationBindings(ClassWithoutAnnotation.class);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void classWithAnnotationHasABinding() {
        // when
        List<ProcessedOperationBinding> result = getProcessedOperationBindings(ClassWithAnnotation.class);

        // then
        assertThat(result).hasSize(1);
        ProcessedOperationBinding processedOperationBinding = result.get(0);
        assertThat(processedOperationBinding.getType()).isEqualTo("test-binding");
        AsyncGenericOperationBindingProcessor.DefaultAsyncGenerialOperationBinding binding =
                (AsyncGenericOperationBindingProcessor.DefaultAsyncGenerialOperationBinding)
                        processedOperationBinding.getBinding();
        assertThat(binding.getExtensionFields()).isEqualTo(Map.of("binding", Map.of("field", "1"), "field", "true"));
    }

    private List<ProcessedOperationBinding> getProcessedOperationBindings(Class<?> testClass) {
        return Arrays.stream(testClass.getDeclaredMethods())
                .map((m) -> m.getAnnotationsByType(AsyncGenericOperationBinding.class))
                .flatMap(Arrays::stream)
                .map(processor::mapToOperationBinding)
                .toList();
    }

    private static class ClassWithoutAnnotation {
        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithAnnotation {
        @AsyncGenericOperationBinding(
                type = "test-binding",
                fields = {"binding.field=1", "field=true"})
        private void methodWithAnnotation() {}

        private void methodWithoutAnnotation() {}
    }

    @Nested
    class PropertiesUtilTest {

        @Test
        void emptyTest() {
            // given
            String[] strings = {};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEmpty();
        }

        @Test
        void onePropertyTest() {
            // given
            String[] strings = {"key=value"};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEqualTo(Map.of("key", "value"));
        }

        @Test
        void oneLongPropertyTest() {
            // given
            String[] strings = {"key=value is long"};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEqualTo(Map.of("key", "value is long"));
        }

        @Test
        void twoPropertiesTest() {
            // given
            String[] strings = {"key1=value1", "key2=value2"};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEqualTo(Map.of("key1", "value1", "key2", "value2"));
        }

        @Test
        void arrayPropertyTest() {
            // given
            String[] strings = {"key=[value1, value2, value3 is long]"};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEqualTo(Map.of("key", List.of("value1", "value2", "value3 is long")));
        }

        @ValueSource(strings = {"asdf[sdf]", "[sdf][sdf]", "[sd[sdf]]", "[kdkd]dkkd", "[kdkd"})
        @ParameterizedTest
        void arrayParsingShouldBeIgnored(String value) {
            // given
            String[] strings = {"key=" + value};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then value is still a string, ignoring the array conversion
            assertThat(result).isEqualTo(Map.of("key", value));
        }

        @Test
        void simpleMapPropertyTest() {
            // given
            String[] strings = {"map.key1=value1", "map.key2=value2", "map.key3=value3"};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEqualTo(Map.of("map", Map.of("key1", "value1", "key2", "value2", "key3", "value3")));
        }

        @Test
        void nestedPropertyTest() {
            // given
            String[] strings = {"nested.key=value"};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEqualTo(Map.of("nested", Map.of("key", "value")));
        }

        @Test
        void deeplyNestedPropertyTest() {
            // given
            String[] strings = {"very.deeply.nested.key=value"};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEqualTo(Map.of("very", Map.of("deeply", Map.of("nested", Map.of("key", "value")))));
        }

        @Test
        void yamlSyntaxDoesWorkAsWell() {
            // given
            String[] strings = {"key: value"};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEqualTo(Map.of("key", "value"));
        }

        @Test
        void yamlSyntaxArrayPropertyTest() {
            // given
            String[] strings = {"key: [value1, value2, value3 is long]"};

            // when
            Map<String, Object> result = PropertiesUtil.toMap(strings);

            // then
            assertThat(result).isEqualTo(Map.of("key", List.of("value1", "value2", "value3 is long")));
        }
    }
}
