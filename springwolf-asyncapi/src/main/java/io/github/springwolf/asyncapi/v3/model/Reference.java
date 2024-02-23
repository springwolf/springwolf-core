// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

/**
 * A simple object to allow referencing other components in the specification, internally and externally.
 * </p>
 * The Reference Object is defined by JSON Reference and follows the same structure, behavior and rules.
 * A JSON Reference SHALL only be used to refer to a schema that is formatted in either JSON or YAML. In the case of
 * a YAML-formatted Schema, the JSON Reference SHALL be applied to the JSON representation of that schema. The JSON
 * representation SHALL be made by applying the conversion described here.
 * </p>
 * For this specification, reference resolution is done as defined by the JSON Reference specification and not by
 * the JSON Schema specification.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#referenceObject">Reference</a>
 */
public interface Reference {
    /**
     * Required. The reference string.
     */
    @NotNull
    @JsonProperty(value = "$ref")
    String getRef();
}
