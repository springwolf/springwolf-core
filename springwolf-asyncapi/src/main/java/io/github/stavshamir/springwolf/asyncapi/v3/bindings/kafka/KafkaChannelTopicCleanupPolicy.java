// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka;

public enum KafkaChannelTopicCleanupPolicy {
    COMPACT("compact"),
    DELETE("delete");

    public final String type;

    KafkaChannelTopicCleanupPolicy(String type) {
        this.type = type;
    }

    public static KafkaChannelTopicCleanupPolicy fromString(String type) {
        return valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
