package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;

import java.util.Map;
import java.util.stream.Stream;

public interface SpringAnnotationOperationsScannerDelegator {
    Stream<Map.Entry<String, Operation>> scan(Class<?> clazz);
}
