// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.operations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationMerger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service to detect AsyncAPI operations in the current spring context.
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultOperationsService implements OperationsService {

    private final List<? extends OperationsScanner> operationScanners;

    /**
     * Collects all AsyncAPI Operation using the available {@link OperationsScanner}
     * beans.
     * @return Map of operation names mapping to detected Operation
     */
    @Override
    public Map<String, Operation> findOperations() {
        List<Operation> foundOperations = new ArrayList<>();

        for (OperationsScanner scanner : operationScanners) {
            try {
                Map<String, Operation> channels = scanner.scan();
                foundOperations.addAll(channels.values());
            } catch (Exception e) {
                log.warn("An error was encountered during operation scanning with {}: {}", scanner, e.getMessage(), e);
            }
        }
        return OperationMerger.mergeOperations(foundOperations);
    }
}
