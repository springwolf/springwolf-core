// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.ExternalDocumentation;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.Reference;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.Tag;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.CorrelationID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Describes a trait that MAY be applied to a Message Object. This object MAY contain any property from the
 * Message Object, except payload and traits.
 * </p>
 * If you're looking to apply traits to an operation, see the Operation Trait Object.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#messageTraitObject">Message Trait Object</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageTrait extends ExtendableObject implements Reference {
    /**
     * Schema definition of the application headers. Schema MUST be a map of key-value pairs. It MUST NOT define the
     * protocol headers. If this is a Schema Object, then the schemaFormat will be assumed to be
     * "application/vnd.aai.asyncapi+json;version=asyncapi" where the version is equal to the AsyncAPI Version String.
     */
    @JsonProperty(value = "headers")
    private MessageHeaders headers;

    /**
     * Definition of the correlation ID used for message tracing or matching.
     */
    @JsonProperty(value = "correlationId")
    private CorrelationID correlationId;

    /**
     * The content type to use when encoding/decoding a message's payload. The value MUST be a specific media type
     * (e.g. application/json). When omitted, the value MUST be the one specified on the defaultContentType field.
     */
    @JsonProperty(value = "contentType")
    private String contentType;

    /**
     * A machine-friendly name for the message.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * A human-friendly title for the message.
     */
    @JsonProperty(value = "title")
    private String title;

    /**
     * A short summary of what the message is about.
     */
    @JsonProperty(value = "summary")
    private String summary;

    /**
     * A verbose explanation of the message. <a href="https://spec.commonmark.org/">CommonMark syntax</a> can be used
     * for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * A list of tags for logical grouping and categorization of messages.
     */
    @JsonProperty(value = "tags")
    private List<Tag> tags;

    /**
     * Additional external documentation for this message.
     */
    @JsonProperty(value = "externalDocs")
    private ExternalDocumentation externalDocs;

    /**
     * A map where the keys describe the name of the protocol and the values describe protocol-specific definitions
     * for the message.
     */
    @JsonProperty(value = "bindings")
    private Map<String, MessageBinding> bindings;

    /**
     * List of examples.
     */
    @JsonProperty(value = "examples")
    private List<MessageExample> examples;

    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
