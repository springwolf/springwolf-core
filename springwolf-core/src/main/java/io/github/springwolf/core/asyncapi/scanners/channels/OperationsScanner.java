// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;

import java.util.Map;

public interface OperationsScanner {

    /**
     * @return A mapping of operation names to their respective operation object for a given protocol.
     */
    Map<String, Operation> scan();
}
