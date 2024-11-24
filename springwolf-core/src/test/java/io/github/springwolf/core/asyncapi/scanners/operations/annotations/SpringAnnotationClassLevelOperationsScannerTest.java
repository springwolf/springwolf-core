// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationsService;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SpringAnnotationClassLevelOperationsScannerTest {

    private final OperationCustomizer operationCustomizer = mock(OperationCustomizer.class);
    private final SpringAnnotationOperationsService<TestClassListener> springAnnotationOperationsService =
            mock(SpringAnnotationOperationsService.class);
    SpringAnnotationClassLevelOperationsScanner<TestClassListener, TestMethodListener> scanner =
            new SpringAnnotationClassLevelOperationsScanner<>(
                    TestClassListener.class,
                    TestMethodListener.class,
                    springAnnotationOperationsService,
                    List.of(operationCustomizer));

    @Test
    void scan() {
        // given
        Operation operation = Operation.builder().operationId("operationId").build();
        when(springAnnotationOperationsService.buildOperation(any(), any(), anySet()))
                .thenReturn(operation);

        // when
        List<Map.Entry<String, Operation>> operations =
                scanner.scan(ClassWithTestListenerAnnotation.class).toList();

        // then
        assertThat(operations).containsExactly(Map.entry("operationId", operation));
    }

    @Test
    void operationCustomizerIsCalled() {
        // given
        Operation operation = Operation.builder().operationId("operationId").build();
        when(springAnnotationOperationsService.buildOperation(any(), any(), anySet()))
                .thenReturn(operation);

        // when
        scanner.scan(ClassWithTestListenerAnnotation.class).toList();

        // then
        verify(operationCustomizer).customize(any(), any());
    }

    @TestClassListener
    private static class ClassWithTestListenerAnnotation {
        @TestMethodListener
        private void methodWithAnnotation(String payload) {}

        private void methodWithoutAnnotation() {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestClassListener {}

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestMethodListener {}
}
