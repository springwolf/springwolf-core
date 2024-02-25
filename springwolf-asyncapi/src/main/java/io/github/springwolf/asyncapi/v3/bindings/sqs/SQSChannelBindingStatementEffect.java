// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.sqs;

public enum SQSChannelBindingStatementEffect {
    ALLOW("Allow"),
    DENY("Deny");

    public final String type;

    SQSChannelBindingStatementEffect(String type) {
        this.type = type;
    }

    public static SQSChannelBindingStatementEffect fromString(String type) {
        return valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
