// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.operation.AsyncAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AsyncAnnotationMethodLevelOperationsScannerTest {

    private final AsyncAnnotationProvider<AsyncListener> asyncAnnotationProvider = new AsyncAnnotationProvider<>() {
        @Override
        public Class<AsyncListener> getAnnotation() {
            return AsyncListener.class;
        }

        @Override
        public AsyncOperation getAsyncOperation(AsyncListener annotation) {
            return annotation.operation();
        }

        @Override
        public OperationAction getOperationType() {
            return OperationAction.SEND;
        }
    };
    private final OperationCustomizer operationCustomizer = mock(OperationCustomizer.class);

    private final StringValueResolverProxy stringValueResolver = mock(StringValueResolverProxy.class);
    private final AsyncAnnotationOperationService<AsyncListener> asyncAnnotationOperationService =
            mock(AsyncAnnotationOperationService.class);

    private final AsyncAnnotationMethodLevelOperationsScanner<AsyncListener> operationsScanner =
            new AsyncAnnotationMethodLevelOperationsScanner<>(
                    asyncAnnotationProvider,
                    asyncAnnotationOperationService,
                    List.of(operationCustomizer),
                    stringValueResolver);

    @BeforeEach
    public void setup() {
        doAnswer(invocation -> invocation.getArgument(0))
                .when(stringValueResolver)
                .resolveStringValue(any());
    }

    @Test
    void scan_componentOperationHasListenerMethod() {
        // given
        Operation operation = Operation.builder().build();
        when(asyncAnnotationOperationService.buildOperation(any(), any(Method.class), any()))
                .thenReturn(operation);

        // when
        Map<String, Operation> actualOperations = operationsScanner
                .scan(ClassWithListenerAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // then
        assertThat(actualOperations).containsExactly(Map.entry("test-channel_id_send_methodWithAnnotation", operation));
    }

    @Test
    void operationCustomizerIsCalled() {
        // given
        Operation operation = Operation.builder().build();
        when(asyncAnnotationOperationService.buildOperation(any(), any(Method.class), any()))
                .thenReturn(operation);

        // when
        operationsScanner
                .scan(ClassWithListenerAnnotation.class)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // then
        verify(operationCustomizer).customize(any(), any());
    }

    private static class ClassWithListenerAnnotation {

        @AsyncListener(
                operation = @AsyncOperation(channelName = "test-channel", description = "test-channel-1-description"))
        private void methodWithAnnotation(String payload) {}
    }
}
