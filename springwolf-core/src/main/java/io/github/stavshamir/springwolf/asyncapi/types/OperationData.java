package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;

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
     * The channel binding.
     */
    Map<String, ? extends ChannelBinding> getChannelBinding();

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
    Map<String, ? extends OperationBinding> getOperationBinding();

    enum OperationType {
        PUBLISH("publish"), SUBSCRIBE("subscribe");

        public final String operationName;
        OperationType(String operationName) {
            this.operationName = operationName;
        }
    }
}
