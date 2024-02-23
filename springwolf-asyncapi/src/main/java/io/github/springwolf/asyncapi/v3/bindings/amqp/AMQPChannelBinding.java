// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Protocol-specific information for an AMQP 0-9-1 channel.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/amqp/README.md#channel">AMQP Channel</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AMQPChannelBinding extends ChannelBinding {
    @Builder.Default
    @NotNull
    @JsonProperty(value = "is", required = true, defaultValue = "routingKey")
    private AMQPChannelType is = AMQPChannelType.ROUTING_KEY;

    @JsonProperty("exchange")
    private AMQPChannelExchangeProperties exchange;

    /**
     * When is=queue, this object defines the queue properties.
     */
    @JsonProperty("queue")
    private AMQPChannelQueueProperties queue;

    @Builder.Default
    @JsonProperty(value = "bindingVersion")
    private final String bindingVersion = "0.3.0";
}
