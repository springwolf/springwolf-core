// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.mqtt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
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
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#message-binding-object">MQTT Message</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MQTTMessageBinding extends ChannelBinding {
    /**
     * Either: 0 (zero): Indicates that the payload is unspecified bytes, or 1: Indicates that the payload is UTF-8
     * encoded character data.
     */
    @Min(value = 0, message = "Indicates that the payload is unspecified bytes")
    @Max(value = 1, message = "Indicates that the payload is UTF-8")
    @JsonProperty("payloadFormatIndicator")
    private Integer payloadFormatIndicator;

    /**
     * Correlation Data is used by the sender of the request message to identify which request the response message is
     * for when it is received.
     */
    @JsonProperty("correlationData")
    private Schema correlationData;

    /**
     * String describing the content type of the message payload. This should not conflict with the contentType field
     * of the associated AsyncAPI Message object.
     */
    @JsonProperty("contentType")
    private String contentType;

    /**
     * The topic (channel URI) for a response message.
     */
    @JsonProperty("responseTopic")
    private Object responseTopic;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.2.0";

    public static class MQTTMessageBindingBuilder {}

    public static MQTTMessageBindingBuilder builder() {
        return new CustomMQTTMessageBinding();
    }

    public static class CustomMQTTMessageBinding extends MQTTMessageBindingBuilder {
        private Object responseTopic;

        public MQTTMessageBindingBuilder responseTopic(Schema schema) {
            this.responseTopic = schema;
            return this;
        }

        public MQTTMessageBindingBuilder responseTopic(String uriString) {
            this.responseTopic = uriString;
            return this;
        }
    }
}
