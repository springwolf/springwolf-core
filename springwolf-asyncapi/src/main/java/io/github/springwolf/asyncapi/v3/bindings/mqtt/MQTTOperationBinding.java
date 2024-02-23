// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.mqtt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object MUST NOT contain any properties. Its name is reserved for future use.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#operation-binding-object">MQTT Operation</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MQTTOperationBinding extends OperationBinding {
    /**
     * Defines the Quality of Service (QoS) levels for the message flow between client and server.
     * Its value MUST be either 0 (At most once delivery), 1 (At least once delivery), or 2 (Exactly once delivery).
     * </p>
     * Applies to: Publish, Subscribe
     */
    @Min(0)
    @Max(2)
    @JsonProperty("qos")
    private Integer qos;

    /**
     * Whether the broker should retain the message or not.
     * </p>
     * Applies to: Publish
     */
    @JsonProperty("retain")
    private Boolean retain;

    /**
     * Interval in seconds or a Schema Object containing the definition of the lifetime of the message.
     * </p>
     * Applies to: Publish
     */
    @JsonProperty("messageExpiryInterval")
    private Object messageExpiryInterval;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.2.0";

    public static class MQTTOperationBindingBuilder {}

    public static MQTTOperationBindingBuilder builder() {
        return new CustomMQTTOperationBindingBuilder();
    }

    public static class CustomMQTTOperationBindingBuilder extends MQTTOperationBindingBuilder {
        private Object messageExpiryInterval;

        public MQTTOperationBindingBuilder messageExpiryInterval(Integer integer) {
            this.messageExpiryInterval = integer;
            return this;
        }

        public MQTTOperationBindingBuilder messageExpiryInterval(Schema schema) {
            this.messageExpiryInterval = schema;
            return this;
        }
    }
}
