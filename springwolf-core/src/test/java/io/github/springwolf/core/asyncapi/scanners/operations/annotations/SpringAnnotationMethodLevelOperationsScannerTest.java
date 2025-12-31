// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationCustomizer;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SpringAnnotationMethodLevelOperationsScannerTest {

    private final PayloadMethodParameterService payloadMethodParameterService = mock();
    private final HeaderClassExtractor headerClassExtractor = mock(HeaderClassExtractor.class);
    private final OperationCustomizer operationCustomizer = mock(OperationCustomizer.class);
    private final SpringAnnotationOperationService<TestMethodListener> springAnnotationOperationService =
            mock(SpringAnnotationOperationService.class);
    SpringAnnotationMethodLevelOperationsScanner<TestMethodListener> scanner =
            new SpringAnnotationMethodLevelOperationsScanner<>(
                    TestMethodListener.class,
                    headerClassExtractor,
                    payloadMethodParameterService,
                    springAnnotationOperationService,
                    List.of(operationCustomizer));

    @Test
    void scan_componentHasTestListenerMethods() {
        // given
        Operation operation = Operation.builder().operationId("operationId").build();
        when(springAnnotationOperationService.buildOperation(any(), any(), any()))
                .thenReturn(operation);

        // when
        List<Operation> operations =
                scanner.scan(ClassWithTestListenerAnnotation.class).toList();

        // then
        assertThat(operations).containsExactly(operation);
    }

    @Test
    void operationCustomizerIsCalled() {
        // given
        Operation operation = Operation.builder().operationId("operationId").build();
        when(springAnnotationOperationService.buildOperation(any(), any(), any()))
                .thenReturn(operation);

        // when
        scanner.scan(ClassWithTestListenerAnnotation.class).toList();

        // then
        verify(operationCustomizer).customize(any(), any());
    }

    private static class ClassWithTestListenerAnnotation {
        @TestMethodListener
        private void methodWithAnnotation(String payload) {}

        private void methodWithoutAnnotation() {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestMethodListener {}
}
