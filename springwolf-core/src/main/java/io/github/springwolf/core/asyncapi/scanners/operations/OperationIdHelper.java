// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations;

import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;

public class OperationIdHelper {
    public static String buildOperationId(String channelId, OperationAction operationAction, String componentName) {
        return String.join("_", channelId, operationAction.type, componentName);
    }
}
