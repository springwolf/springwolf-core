// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object contains information about the message representation in AMQP.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AMQPMessageBinding extends MessageBinding {
    /**
     * A MIME encoding for the message content.
     */
    @JsonProperty("contentEncoding")
    private String contentEncoding;

    /**
     * Application-specific message type.
     */
    @JsonProperty("messageType")
    private String messageType;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty(value = "bindingVersion")
    private final String bindingVersion = "0.3.0";
}
