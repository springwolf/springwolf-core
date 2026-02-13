// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Allows referencing an external resource for extended documentation.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.1.0#externalDocumentationObject">ExternalDocumentation</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExternalDocumentation extends ExtendableObject implements Reference {

    /**
     * A short description of the target documentation. <a href="https://spec.commonmark.org/">CommonMark syntax</a>
     * can be used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * Required.
     * <p>
     * The URL for the target documentation. Value MUST be in the format of a URL.
     */
    @NotNull
    @JsonProperty(value = "url")
    private String url;

    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
