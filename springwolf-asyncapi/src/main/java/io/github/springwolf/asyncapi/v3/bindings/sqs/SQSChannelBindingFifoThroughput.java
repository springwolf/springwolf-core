// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.sqs;

public enum SQSChannelBindingFifoThroughput {
    PER_QUEUE("perQueue"),
    PER_MESSAGE_GROUP_ID("perMessageGroupId");

    public final String type;

    SQSChannelBindingFifoThroughput(String type) {
        this.type = type;
    }

    public static SQSChannelBindingFifoThroughput fromString(String type) {
        return valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
