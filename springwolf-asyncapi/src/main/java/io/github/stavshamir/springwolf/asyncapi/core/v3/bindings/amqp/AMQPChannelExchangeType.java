// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AMQPChannelExchangeType {
    @JsonProperty("topic")
    TOPIC,

    @JsonProperty("direct")
    DIRECT,

    @JsonProperty("fanout")
    FANOUT,

    @JsonProperty("default")
    DEFAULT,

    @JsonProperty("headers")
    HEADERS
}
