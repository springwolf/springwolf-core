// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.jms;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JMSChannelBindingDestinationType {
    @JsonProperty("queue")
    QUEUE,

    @JsonProperty("fifo-queue")
    FIFO_QUEUE
}
