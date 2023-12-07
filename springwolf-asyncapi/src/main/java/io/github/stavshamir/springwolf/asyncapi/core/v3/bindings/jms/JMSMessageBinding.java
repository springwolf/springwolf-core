// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.jms;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.schema.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object contains information about the message representation in JMS.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/jms/README.md#message-binding-object">JMS Message</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JMSMessageBinding extends MessageBinding {
    /**
     * OPTIONAL. A Schema object containing the definitions for JMS specific headers (so-called protocol headers).
     * This schema MUST be of type object and have a properties key. Examples of JMS protocol headers are JMSMessageID,
     * JMSTimestamp, and JMSCorrelationID.
     */
    @JsonProperty(value = "headers")
    private Schema headers;

    /**
     * OPTIONAL, defaults to latest. The version of this binding.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.0.1";
}
