// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OperationsInClassScannerAdapterTest {

    private final ClassScanner classScanner = mock(ClassScanner.class);
    private final OperationsInClassScanner classProcessor = mock(OperationsInClassScanner.class);

    private final OperationsInClassScannerAdapter operationsInClassScannerAdapter =
            new OperationsInClassScannerAdapter(classScanner, classProcessor);

    @Test
    void noClassFoundTest() {
        // when
        Map<String, Operation> operations = operationsInClassScannerAdapter.scan();

        // then
        assertThat(operations).isEmpty();
    }

    @Test
    void processClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Operation operation1 = Operation.builder().operationId("operation1").build();
        Operation operation2 = Operation.builder().operationId("operation2").build();
        when(classProcessor.scan(any())).thenReturn(Stream.of(operation1, operation2));

        // when
        Map<String, Operation> operations = operationsInClassScannerAdapter.scan();

        // then
        assertThat(operations).containsExactlyEntriesOf(Map.of("operation1", operation1, "operation2", operation2));
    }

    @Test
    void sameOperationsAreMergedTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        Operation operation1 =
                Operation.builder().operationId("operation1").description("op1").build();
        Operation operation2 =
                Operation.builder().operationId("operation1").description("op2").build();
        when(classProcessor.scan(any())).thenReturn(Stream.of(operation1, operation2));

        // when
        Map<String, Operation> operations = operationsInClassScannerAdapter.scan();

        // then
        assertThat(operations).hasSize(1);
    }

    @Test
    void processEmptyClassTest() {
        // given
        when(classScanner.scan()).thenReturn(Set.of(String.class));
        when(classProcessor.scan(any())).thenReturn(Stream.of());

        // when
        Map<String, Operation> operations = operationsInClassScannerAdapter.scan();

        // then
        assertThat(operations).isEmpty();
    }
}
