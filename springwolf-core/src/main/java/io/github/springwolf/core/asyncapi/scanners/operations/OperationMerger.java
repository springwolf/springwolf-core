// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Util to merge multiple {@link Operation}s
 */
public class OperationMerger {

    private OperationMerger() {}

    /**
     * Merges multiple operations by operation name
     * <p>
     * Given two operations for the same operation name, the first seen Operation is used
     * If an operation is null, the next non-null operation is used
     * Messages within operations are merged
     *
     * @param operationEntries Ordered pairs of operation name to Operation
     * @return A map of operationId to a single Operation
     */
    public static Map<String, Operation> mergeOperations(List<Map.Entry<String, Operation>> operationEntries) {
        Map<String, Operation> mergedOperations = new HashMap<>();

        for (Map.Entry<String, Operation> entry : operationEntries) {
            if (!mergedOperations.containsKey(entry.getKey())) {
                mergedOperations.put(entry.getKey(), entry.getValue());
            } else {
                Operation operation = mergeOperation(mergedOperations.get(entry.getKey()), entry.getValue());
                mergedOperations.put(entry.getKey(), operation);
            }
        }

        return mergedOperations;
    }

    private static Operation mergeOperation(Operation operation, Operation otherOperation) {
        Operation mergedOperation = operation != null ? operation : otherOperation;

        List<MessageReference> mergedMessages =
                mergeMessageReferences(operation.getMessages(), otherOperation.getMessages());
        if (!mergedMessages.isEmpty()) {
            mergedOperation.setMessages(mergedMessages);
        }
        return mergedOperation;
    }

    private static List<MessageReference> mergeMessageReferences(
            Collection<MessageReference> messages, Collection<MessageReference> otherMessages) {
        var messageReferences = new HashSet<MessageReference>();
        if (messages != null) {
            messageReferences.addAll(messages);
        }
        if (otherMessages != null) {
            messageReferences.addAll(otherMessages);
        }

        return messageReferences.stream()
                .sorted(Comparator.comparing(MessageReference::getRef))
                .toList();
    }
}
