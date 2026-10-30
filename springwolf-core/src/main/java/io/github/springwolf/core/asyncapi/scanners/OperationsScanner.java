// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;

import java.util.List;

public interface OperationsScanner {

    /**
     * @return The operations found for a given protocol.
     */
    List<Operation> scan();
}
