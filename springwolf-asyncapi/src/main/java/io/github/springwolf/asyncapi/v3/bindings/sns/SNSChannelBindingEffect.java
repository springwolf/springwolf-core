// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.sns;

public enum SNSChannelBindingEffect {
    ALLOW("Allow"),
    DENY("Deny");

    public final String type;

    SNSChannelBindingEffect(String type) {
        this.type = type;
    }

    public static SNSChannelBindingEffect fromString(String type) {
        return valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
