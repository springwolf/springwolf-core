// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SQSChannelBindingDeduplicationScope {
    @JsonProperty("messageGroup")
    MESSAGE_GROUP,
    @JsonProperty("queue")
    QUEUE
}
