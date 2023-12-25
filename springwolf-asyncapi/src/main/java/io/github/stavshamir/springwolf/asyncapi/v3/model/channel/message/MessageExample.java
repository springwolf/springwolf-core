// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExtendableObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageExample extends ExtendableObject {
    /**
     * The value of this field MUST validate against the Message Object's headers field.
     */
    @JsonProperty(value = "headers")
    private Map<String, Object> headers;

    /**
     * The value of this field MUST validate against the Message Object's payload field.
     */
    @JsonProperty(value = "payload")
    private Map<String, Object> payload;

    /**
     * A machine-friendly name.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * A short summary of what the example is about.
     */
    @JsonProperty(value = "summary")
    private String summary;
}
