// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Allows adding meta data to a single tag.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.1.0#tagsObject">Tag</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tag extends ExtendableObject {

    /**
     * Required. The name of the tag.
     */
    @NotNull
    @JsonProperty("name")
    private String name;

    /**
     * A short description for the tag. <a href="https://spec.commonmark.org/">CommonMark syntax</a> can be used for
     * rich text representation.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Additional external documentation for this tag.
     */
    @JsonProperty("externalDocs")
    private ExternalDocumentation externalDocs;
}
