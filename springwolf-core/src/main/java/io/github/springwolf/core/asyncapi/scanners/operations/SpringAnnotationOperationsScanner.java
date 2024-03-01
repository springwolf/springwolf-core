// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationOperationsScannerDelegator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class SpringAnnotationOperationsScanner implements OperationsScanner {

    private final ClassScanner classScanner;

    private final SpringAnnotationOperationsScannerDelegator classProcessor;

    @Override
    public Map<String, Operation> scan() {
        Set<Class<?>> components = classScanner.scan();

        List<Map.Entry<String, Operation>> operations = mapToOperations(components);

        return OperationMerger.mergeOperations(operations);
    }

    private List<Map.Entry<String, Operation>> mapToOperations(Set<Class<?>> components) {
        return components.stream().flatMap(classProcessor::scan).toList();
    }

}
