// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.annotation.processor;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.annotation.AsyncGenericOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedOperationBinding;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AsyncGenericOperationBindingProcessorTest {

    private final AsyncGenericOperationBindingProcessor processor = new AsyncGenericOperationBindingProcessor();

    @Test
    void testClassWithoutAnnotation() {
        // when
        List<ProcessedOperationBinding> result = getProcessedOperationBindings(ClassWithoutAnnotation.class);

        // then
        assertThat(result).hasSize(0);
    }

    @Test
    void testClassWithAnnotationHasABinding() {
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

    @Test
    void testClassWithMultipleAnnotationHasABinding() {
        // when
        List<ProcessedOperationBinding> result = getProcessedOperationBindings(ClassWithMultipleAnnotation.class);

        // then
        assertThat(result).hasSize(2);

        ProcessedOperationBinding processedOperationBinding = result.get(0);
        assertThat(processedOperationBinding.getType()).isEqualTo("test-binding");
        AsyncGenericOperationBindingProcessor.DefaultAsyncGenerialOperationBinding binding =
                (AsyncGenericOperationBindingProcessor.DefaultAsyncGenerialOperationBinding)
                        processedOperationBinding.getBinding();
        assertThat(binding.getExtensionFields()).isEqualTo(Map.of("binding", Map.of("field", "1"), "field", "true"));

        ProcessedOperationBinding processedOperationBinding2 = result.get(1);
        assertThat(processedOperationBinding2.getType()).isEqualTo("another-binding");
        assertThat(processedOperationBinding2.getBinding())
                .isEqualTo(new AsyncGenericOperationBindingProcessor.DefaultAsyncGenerialOperationBinding(Map.of()));
    }

    private List<ProcessedOperationBinding> getProcessedOperationBindings(Class<?> testClass) {
        List<ProcessedOperationBinding> result = Arrays.stream(testClass.getDeclaredMethods())
                .map((m) -> m.getAnnotationsByType(AsyncGenericOperationBinding.class))
                .flatMap(Arrays::stream)
                .map(processor::mapToOperationBinding)
                .toList();
        return result;
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

    private static class ClassWithMultipleAnnotation {
        @AsyncGenericOperationBinding(
                type = "test-binding",
                fields = {"binding.field=1", "field=true"})
        @AsyncGenericOperationBinding(type = "another-binding")
        private void methodWithAnnotation() {}

        private void methodWithoutAnnotation() {}
    }
}
