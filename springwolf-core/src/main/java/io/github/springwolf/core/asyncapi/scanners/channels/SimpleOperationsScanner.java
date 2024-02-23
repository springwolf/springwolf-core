// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels;

import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SimpleOperationsScanner implements OperationsScanner {

    private final ClassScanner classScanner;

    private final ClassProcessor classProcessor;

    @Override
    public Map<String, Operation> scan() {
        Set<Class<?>> components = classScanner.scan();

        List<Map.Entry<String, Operation>> operations = mapToOperations(components);

        return OperationMerger.mergeOperations(operations);
    }

    private List<Map.Entry<String, Operation>> mapToOperations(Set<Class<?>> components) {
        return components.stream().flatMap(classProcessor::process).toList();
    }

    public interface ClassProcessor {
        Stream<Map.Entry<String, Operation>> process(Class<?> clazz);
    }
}
