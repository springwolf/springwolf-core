// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationOperationsScannerDelegator;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpringAnnotationOperationsScannerTest {

    private final ClassScanner classScanner = mock(ClassScanner.class);
    private final SpringAnnotationOperationsScannerDelegator classProcessor =
            mock(SpringAnnotationOperationsScannerDelegator.class);

    private final SpringAnnotationOperationsScanner springAnnotationOperationsScanner =
            new SpringAnnotationOperationsScanner(classScanner, classProcessor);

    @Test
    void noClassFoundTest() {
        // when
        Map<String, Operation> operations = springAnnotationOperationsScanner.scan();

        // then
        assertThat(operations).isEmpty();
    }

    @Test
    void processClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Map.Entry<String, Operation> operation1 =
                Map.entry("operation1", Operation.builder().build());
        Map.Entry<String, Operation> operation2 =
                Map.entry("operation2", Operation.builder().build());
        when(classProcessor.scan(any())).thenReturn(Stream.of(operation1, operation2));

        // when
        Map<String, Operation> operations = springAnnotationOperationsScanner.scan();

        // then
        assertThat(operations).containsExactly(operation2, operation1);
    }

    @Test
    void sameOperationsAreMergedTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Map.Entry<String, Operation> operation1 =
                Map.entry("operation1", Operation.builder().build());
        Map.Entry<String, Operation> operation2 =
                Map.entry("operation1", Operation.builder().build());
        when(classProcessor.scan(any())).thenReturn(Stream.of(operation1, operation2));

        // when
        Map<String, Operation> operations = springAnnotationOperationsScanner.scan();

        // then
        assertThat(operations)
                .containsExactly(Map.entry("operation1", Operation.builder().build()));
    }

    @Test
    void processEmptyClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        when(classProcessor.scan(any())).thenReturn(Stream.of());

        // when
        Map<String, Operation> operations = springAnnotationOperationsScanner.scan();

        // then
        assertThat(operations).isEmpty();
    }
}
