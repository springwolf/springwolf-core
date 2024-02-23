// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.springwolf.asyncapi.v3.model.Reference;
import io.github.springwolf.asyncapi.v3.model.Tag;
import io.github.springwolf.asyncapi.v3.model.security_scheme.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Describes a trait that MAY be applied to an Operation Object. This object MAY contain any property from the
 * Operation Object, except the action, channel and traits ones.
 * </p>
 * If you're looking to apply traits to a message, see the Message Trait Object.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#operationTraitObject">Operation Trait Object</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OperationTraits extends ExtendableObject implements Reference {
    /**
     * A human-friendly title for the operation.
     */
    @JsonProperty(value = "title")
    private String title;

    /**
     * A short summary of what the operation is about.
     */
    @JsonProperty(value = "summary")
    private String summary;

    /**
     * A verbose explanation of the operation. <a href="https://spec.commonmark.org/">CommonMark syntax</a> can be
     * used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * A declaration of which security schemes are associated with this operation. Only one of the security scheme
     * objects MUST be satisfied to authorize an operation. In cases where Server Security also applies, it MUST also be satisfied.
     */
    @JsonProperty(value = "security")
    private SecurityScheme security;

    /**
     * A list of tags for logical grouping and categorization of operations.
     */
    @JsonProperty(value = "tags")
    private List<Tag> tags;

    /**
     * Additional external documentation for this operation.
     */
    @JsonProperty(value = "externalDocs")
    private ExternalDocumentation externalDocs;

    /**
     * A map where the keys describe the name of the protocol and the values describe protocol-specific definitions
     * for the operation.
     */
    @JsonProperty(value = "bindings")
    private Map<String, OperationBinding> bindings;

    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
