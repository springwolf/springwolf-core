// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.ExternalDocumentation;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The object provides metadata about the API. The metadata can be used by the clients if needed.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#infoObject">Info</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Info extends ExtendableObject {

    /**
     * Required. The title of the application.
     */
    @NotNull
    @JsonProperty(value = "title")
    private String title;

    /**
     * Required. Provides the version of the application API (not to be confused with the specification version).
     */
    @NotNull
    @JsonProperty(value = "version")
    private String version;

    /**
     * A short description of the application. <a href="https://spec.commonmark.org/">CommonMark syntax</a> can be
     * used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * A URL to the Terms of Service for the API. MUST be in the format of a URL.
     */
    @JsonProperty(value = "termsOfService")
    private String termsOfService;

    /**
     * The contact information for the exposed API.
     */
    @JsonProperty(value = "contact")
    private Contact contact;

    /**
     * The license information for the exposed API.
     */
    @JsonProperty(value = "license")
    private License license;

    /**
     * A list of tags for application API documentation control. Tags can be used for logical grouping of applications.
     */
    @JsonProperty(value = "tags")
    private List<Tag> tags;

    /**
     * Additional external documentation of the exposed API.
     */
    @JsonProperty(value = "externalDocs")
    private ExternalDocumentation externalDocs;
}
