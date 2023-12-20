// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.jms;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.ChannelBinding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Protocol-specific information for a JMS channel.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/amqp/README.md#channel">JMS Channel</a>
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JMSChannelBinding extends ChannelBinding {
    /**
     * OPTIONAL, defaults to the channel name. The destination (queue) name for this channel. SHOULD only be specified
     * if the channel name differs from the actual destination name, such as when the channel name is not a valid
     * destination name according to the JMS Provider.
     */
    @JsonProperty("destination")
    private String destination;

    /**
     * OPTIONAL, defaults to queue. The type of destination, which MUST be either queue, or fifo-queue. SHOULD be
     * specified to document the messaging model (point-to-point, or strict message ordering) supported by this channel.
     */
    @JsonProperty("destinationType")
    private JMSChannelBindingDestinationType destinationType;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.0.1";
}
