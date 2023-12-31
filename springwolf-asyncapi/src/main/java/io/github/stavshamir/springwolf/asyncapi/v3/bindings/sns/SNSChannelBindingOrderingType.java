// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns;

public enum SNSChannelBindingOrderingType {
    STANDARD("standard"),
    FIFO("FIFO");

    public final String type;

    SNSChannelBindingOrderingType(String type) {
        this.type = type;
    }

    public static SNSChannelBindingOrderingType fromString(String type) {
        return valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
