// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.core.asyncapi.scanners.channels.OperationMerger;
import io.github.springwolf.core.asyncapi.scanners.channels.OperationsScanner;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
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
        List<Map.Entry<String, Operation>> foundOperations = new ArrayList<>();

        for (OperationsScanner scanner : operationScanners) {
            try {
                Map<String, Operation> channels = scanner.scan();
                foundOperations.addAll(channels.entrySet());
            } catch (Exception e) {
                log.error("An error was encountered during operation scanning with {}: {}", scanner, e.getMessage(), e);
            }
        }
        return OperationMerger.mergeOperations(foundOperations);
    }
}
