// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class OperationsInClassScannerAdapter implements OperationsScanner {

    private final ClassScanner classScanner;

    private final OperationsInClassScanner classProcessor;

    @Override
    public List<Operation> scan() {
        Set<Class<?>> components = classScanner.scan();

        return mapToOperations(components);
    }

    private List<Operation> mapToOperations(Set<Class<?>> components) {
        return components.stream().flatMap(classProcessor::scan).toList();
    }
}
