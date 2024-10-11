// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.operation.AsyncAnnotationOperationService;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AsyncAnnotationClassLevelOperationsScannerTest {

    private final AsyncAnnotationProvider<TestListener> asyncAnnotationProvider = new AsyncAnnotationProvider<>() {
        @Override
        public Class<TestListener> getAnnotation() {
            return TestListener.class;
        }

        @Override
        public AsyncOperation getAsyncOperation(TestListener annotation) {
            return annotation.operation();
        }

        @Override
        public OperationAction getOperationType() {
            return OperationAction.SEND;
        }
    };
    private final AsyncAnnotationOperationService<TestListener> asyncAnnotationOperationService =
            mock(AsyncAnnotationOperationService.class);
    private final OperationCustomizer operationCustomizer = mock(OperationCustomizer.class);

    private final AsyncAnnotationClassLevelOperationsScanner<TestListener> operationsScanner =
            new AsyncAnnotationClassLevelOperationsScanner<>(
                    TestListener.class,
                    asyncAnnotationProvider,
                    asyncAnnotationOperationService,
                    List.of(operationCustomizer));

    @Test
    void scan() {
        // given
        Operation operation = Operation.builder().build();
        when(asyncAnnotationOperationService.buildOperation(any(), anySet(), any()))
                .thenReturn(operation);

        // when
        Map<String, Operation> actualOperations = operationsScanner
                .scan(ClassWithListenerAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // then
        assertThat(actualOperations).hasSize(1);
        assertThat(actualOperations)
                .containsExactlyEntriesOf(Map.of("test-channel_id_send_ClassWithListenerAnnotation", operation));
    }

    @Test
    void operationCustomizerIsCalled() {
        // given
        Operation operation = Operation.builder().build();
        when(asyncAnnotationOperationService.buildOperation(any(), anySet(), any()))
                .thenReturn(operation);

        // when
        operationsScanner.scan(ClassWithListenerAnnotation.class).toList();

        // then
        verify(operationCustomizer).customize(any(), any());
    }

    @TestListener
    private static class ClassWithListenerAnnotation {
        private void method(String payload) {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestListener {
        AsyncOperation operation() default @AsyncOperation(channelName = "test-channel");
    }
}
