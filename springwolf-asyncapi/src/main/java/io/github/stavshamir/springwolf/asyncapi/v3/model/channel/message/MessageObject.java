// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Tag;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.CorrelationID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Describes a message received on a given channel and operation.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageObject extends ExtendableObject implements Message {

    /**
     * The key represents the message identifier. The messageId value is case-sensitive. Tools and libraries MAY use
     * the messageId value to uniquely identify a message, therefore, it is RECOMMENDED to follow common programming
     * naming conventions.
     */
    @JsonIgnore
    private String messageId;

    /**
     * Schema definition of the application headers. Schema MUST be a map of key-value pairs. It MUST NOT define the
     * protocol headers. If this is a Schema Object, then the schemaFormat will be assumed to be
     * "application/vnd.aai.asyncapi+json;version=asyncapi" where the version is equal to the AsyncAPI Version String.
     */
    @JsonProperty(value = "headers")
    private MessageHeaders headers;

    /**
     * Definition of the message payload. If this is a Schema Object, then the schemaFormat will be assumed to be
     * "application/vnd.aai.asyncapi+json;version=asyncapi" where the version is equal to the AsyncAPI Version String.
     */
    @JsonProperty(value = "payload")
    private MessagePayload payload;

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
     * A map where the keys describe the name of the protocol and the values describe protocol-specific definitions for the message.
     */
    @JsonProperty(value = "bindings")
    private Map<String, MessageBinding> bindings;

    /**
     * List of examples.
     */
    @JsonProperty(value = "examples")
    private List<MessageExample> examples;

    /**
     * A list of traits to apply to the message object. Traits MUST be merged using traits merge mechanism.
     * The resulting object MUST be a valid Message Object.
     */
    @JsonProperty(value = "traits")
    private List<MessageTrait> traits;
}
