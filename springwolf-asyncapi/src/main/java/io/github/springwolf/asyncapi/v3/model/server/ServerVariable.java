// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.model.ExtendableObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * An object representing a Server Variable for server URL template substitution.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.1.0#serverVariableObject">ServerVariable</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServerVariable extends ExtendableObject {

    /**
     * An enumeration of string values to be used if the substitution options are from a limited set.
     */
    @JsonProperty(value = "enum")
    private List<String> enumValues;

    /**
     * The default value to use for substitution, and to send, if an alternate value is not supplied.
     */
    @JsonProperty("default")
    private String defaultValue;

    /**
     * An optional description for the server variable. <a href="https://spec.commonmark.org/">CommonMark syntax</a>
     * MAY be used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * An array of examples of the server variable.
     */
    @JsonProperty(value = "examples")
    private List<String> examples;
}
