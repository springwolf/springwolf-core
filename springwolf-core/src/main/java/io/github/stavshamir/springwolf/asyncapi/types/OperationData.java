// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ServerReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Generic, internal interface used by springwolf scanners to process listener and producer data.
 */
public interface OperationData {
    /**
     * The name of the channel (topic, queue etc.).
     */
    String getChannelName();

    /**
     * Optional, additional information about the channel and/or its message
     */
    String getDescription();

    /**
     * Optional, List of server names the channel is assigned to. If empty, the
     * channel is available on all defined servers. May be null.
     */
    @Nullable
    List<ServerReference> getServers();

    /**
     * The channel binding.
     */
    Map<String, ChannelBinding> getChannelBinding();

    /**
     * The class object of the payload.
     */
    Class<?> getPayloadType();

    /**
     * The message headers, usually describing the payload.
     */
    AsyncHeaders getHeaders();

    /**
     * The operation binding.
     */
    Map<String, OperationBinding> getOperationBinding();

    /**
     * The message binding.
     */
    Map<String, MessageBinding> getMessageBinding();

    MessageObject getMessage();

    // FIXME: With AsyncAPI v3 this should be Action: SEND / RECEIVER
    enum OperationType {
        PUBLISH("publish"),
        SUBSCRIBE("subscribe");

        public final String operationName;

        OperationType(String operationName) {
            this.operationName = operationName;
        }
    }
}
