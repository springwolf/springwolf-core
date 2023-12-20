// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum KafkaChannelTopicCleanupPolicy {
    @JsonProperty("compact")
    COMPACT,

    @JsonProperty("delete")
    DELETE
}
