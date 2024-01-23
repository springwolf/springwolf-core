// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;

import java.util.Map;

/**
 * Service to detect AsyncAPI channels in the current spring context.
 */
public interface OperationsService {

    /**
     * Detects all available AsyncAPI Operation in the spring context.
     *
     * @return Map of operation names mapping to detected Operations
     */
    Map<String, Operation> findOperations();
}
