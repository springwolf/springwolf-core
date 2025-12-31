// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;

import java.lang.reflect.Method;

/**
 * Allows for customization of the Operation object after it has been finalized by the scanner.
 */
public interface OperationCustomizer {
    void customize(Operation operation, Method method);
}
