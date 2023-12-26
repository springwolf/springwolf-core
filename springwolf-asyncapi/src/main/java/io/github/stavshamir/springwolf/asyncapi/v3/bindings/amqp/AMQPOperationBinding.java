// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information about the operation representation in AMQP.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/amqp/README.md#operation-binding-object">AMQP Operation</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AMQPOperationBinding extends OperationBinding {

    /**
     * TTL (Time-To-Live) for the message. It MUST be greater than or equal to zero.
     * </p>
     * Applies to: receive, send
     */
    @PositiveOrZero
    @JsonProperty("expiration")
    private Integer expiration;

    /**
     * Identifies the user who has sent the message.
     * </p>
     * Applies to: receive, send
     */
    @JsonProperty("userId")
    private String userId;

    /**
     * The routing keys the message should be routed to at the time of publishing.
     * </p>
     * Applies to: receive, send
     */
    @JsonProperty("cc")
    private List<String> cc;

    /**
     * A priority for the message.
     * </p>
     * Applies to: receive, send
     */
    @JsonProperty("priority")
    private Integer priority;

    /**
     * Delivery mode of the message. Its value MUST be either 1 (transient) or 2 (persistent).
     * </p>
     * Applies to: receive, send
     */
    @Min(value = 1, message = "Delivery mode of the message must be either 1 (transient) or 2 (persistent)")
    @Max(value = 2, message = "Delivery mode of the message must be either 1 (transient) or 2 (persistent)")
    @JsonProperty("deliveryMode")
    private Integer deliveryMode;

    /**
     * Whether the message is mandatory or not.
     * </p>
     * Applies to: receive
     */
    @JsonProperty("mandatory")
    private Boolean mandatory;

    /**
     * Like cc but consumers will not receive this information.
     * </p>
     * Applies to: receive
     */
    @JsonProperty("bcc")
    private List<String> bcc;

    /**
     * Whether the message should include a timestamp or not.
     * </p>
     * Applies to: receive, send
     */
    @JsonProperty("timestamp")
    private Boolean timestamp;

    /**
     * Whether the consumer should ack the message or not.
     * </p>
     * Applies to: Subscribe
     */
    @JsonProperty("ack")
    private Boolean ack;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     * </p>
     * Applies to: receive, send
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.3.0";
}
