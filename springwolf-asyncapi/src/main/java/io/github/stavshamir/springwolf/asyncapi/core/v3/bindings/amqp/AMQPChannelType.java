// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AMQPChannelType {
    @JsonProperty("queue")
    QUEUE,

    @JsonProperty("routingKey")
    ROUTING_KEY
}
