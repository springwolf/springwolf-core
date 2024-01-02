// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp;

public enum AMQPChannelType {
    QUEUE("queue"),
    ROUTING_KEY("routingKey");

    public final String type;

    AMQPChannelType(String type) {
        this.type = type;
    }

    public static AMQPChannelType fromString(String type) {
        return valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
