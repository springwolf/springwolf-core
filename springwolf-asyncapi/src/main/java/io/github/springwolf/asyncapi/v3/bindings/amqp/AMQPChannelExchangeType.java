// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.amqp;

public enum AMQPChannelExchangeType {
    TOPIC("topic"),
    DIRECT("direct"),
    FANOUT("fanout"),
    DEFAULT("default"),
    HEADERS("headers");

    public final String type;

    AMQPChannelExchangeType(String type) {
        this.type = type;
    }

    public static AMQPChannelExchangeType fromString(String type) {
        return valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
