// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.operation;

public enum OperationAction {
    SEND("send"),
    RECEIVE("receive");

    public final String type;

    OperationAction(String type) {
        this.type = type;
    }

    public static OperationAction fromString(String type) {
        return valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
