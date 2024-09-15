// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;

import java.util.Map;
import java.util.stream.Stream;

public interface OperationsInClassScanner {
    Stream<Map.Entry<String, Operation>> scan(Class<?> clazz);
}
