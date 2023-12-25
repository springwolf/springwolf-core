// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Reference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Tag;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.security_scheme.SecurityScheme;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Describes a specific operation.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#operationObject">Operation</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Operation extends ExtendableObject {
    /**
     * Required. Use send when it's expected that the application will send a message to the given channel,
     * and receive when the application should expect receiving messages from the given channel.
     */
    @NotNull
    @JsonProperty(value = "action")
    private OperationAction action;

    /**
     * Required. A $ref pointer to the definition of the channel in which this operation is performed. If the
     * operation is located in the root Operations Object, it MUST point to a channel definition located in the root
     * Channels Object, and MUST NOT point to a channel definition located in the Components Object or anywhere else.
     * If the operation is located in the Components Object, it MAY point to a Channel Object in any location. Please
     * note the channel property value MUST be a Reference Object and, therefore, MUST NOT contain a Channel Object.
     * However, it is RECOMMENDED that parsers (or other software) dereference this property for a better development
     * experience.
     */
    @NotNull
    @JsonProperty(value = "channel")
    private ChannelReference channel;

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
     * A verbose explanation of the operation. <a href="https://spec.commonmark.org/">CommonMark syntax</a> can be used
     * for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * A declaration of which security schemes are associated with this operation. Only one of the security scheme
     * objects MUST be satisfied to authorize an operation. In cases where Server Security also applies, it MUST
     * also be satisfied.
     */
    @JsonProperty(value = "security")
    private List<SecurityScheme> security;

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

    /**
     * A list of traits to apply to the operation object. Traits MUST be merged using traits merge mechanism.
     * The resulting object MUST be a valid Operation Object.
     */
    @JsonProperty(value = "traits")
    private List<OperationTraits> traits;

    /**
     * A list of $ref pointers pointing to the supported Message Objects that can be processed by this operation.
     * It MUST contain a subset of the messages defined in the channel referenced in this operation, and MUST NOT point
     * to a subset of message definitions located in the Messages Object in the Components Object or anywhere else.
     * Every message processed by this operation MUST be valid against one, and only one, of the message objects
     * referenced in this list. Please note the messages property value MUST be a list of Reference Objects and,
     * therefore, MUST NOT contain Message Objects. However, it is RECOMMENDED that parsers (or other software)
     * dereference this property for a better development experience.
     */
    @JsonProperty(value = "messages")
    private List<Reference> messages;

    /**
     * The definition of the reply in a request-reply operation.
     */
    @JsonProperty(value = "reply")
    private OperationReply reply;
}
