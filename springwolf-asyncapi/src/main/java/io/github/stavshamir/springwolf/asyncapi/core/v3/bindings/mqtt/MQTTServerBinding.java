// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.mqtt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.ServerBinding;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.Reference;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.schema.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object contains information about the server representation in MQTT.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#server-binding-object">MQTT Channel</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MQTTServerBinding extends ServerBinding {
    /**
     * The client identifier.
     */
    @JsonProperty("clientId")
    private String clientId;

    /**
     * Whether to create a persistent connection or not. When false, the connection will be persistent.
     * This is called clean start in MQTTv5.
     */
    @JsonProperty("cleanSession")
    private Boolean cleanSession;

    /**
     * Last Will and Testament configuration. topic, qos, message and retain are properties of this object as shown below.
     */
    @JsonProperty("lastWill")
    private MQTTServerLastWill lastWill;

    /**
     * Interval in seconds of the longest period of time the broker and the client can endure without sending a message.
     */
    @JsonProperty("keepAlive")
    private Integer keepAlive;

    /**
     * Interval in seconds or a Schema Object containing the definition of the interval. The broker maintains a session
     * for a disconnected client until this interval expires.
     */
    @JsonProperty("sessionExpiryInterval")
    private Object sessionExpiryInterval;

    /**
     * Number of bytes or a Schema Object representing the maximum packet size the client is willing to accept.
     */
    @JsonProperty("maximumPacketSize")
    private Object maximumPacketSize;

    /**
     * OPTIONAL, defaults to latest. The version of this binding.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.2.0";

    public static class MQTTServerBindingBuilder {}

    public static MQTTServerBindingBuilder builder() {
        return new CustomMQTTServerBindingBuilder();
    }

    public static class CustomMQTTServerBindingBuilder extends MQTTServerBindingBuilder {
        private Object sessionExpiryInterval;
        private Object maximumPacketSize;

        public MQTTServerBindingBuilder sessionExpiryInterval(Integer integer) {
            this.sessionExpiryInterval = integer;
            return this;
        }

        public MQTTServerBindingBuilder sessionExpiryInterval(Schema schema) {
            this.sessionExpiryInterval = schema;
            return this;
        }

        public MQTTServerBindingBuilder sessionExpiryInterval(Reference reference) {
            this.sessionExpiryInterval = reference;
            return this;
        }

        public MQTTServerBindingBuilder maximumPacketSize(Integer integer) {
            this.maximumPacketSize = integer;
            return this;
        }

        public MQTTServerBindingBuilder maximumPacketSize(Schema schema) {
            this.maximumPacketSize = schema;
            return this;
        }

        public MQTTServerBindingBuilder maximumPacketSize(Reference reference) {
            this.maximumPacketSize = reference;
            return this;
        }
    }
}
