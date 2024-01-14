// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Tag;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Describes a shared communication channel.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#channelObject">Channel Object</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChannelObject extends ExtendableObject implements Channel {

    /**
     * An identifier for the described channel. The channelId value is case-sensitive. Tools and libraries MAY use the
     * channelId to uniquely identify a channel, therefore, it is RECOMMENDED to follow common programming naming
     * conventions.
     */
    @JsonIgnore
    private String channelId;

    /**
     * An optional string representation of this channel's address. The address is typically the "topic name",
     * "routing key", "event type", or "path". When null or absent, it MUST be interpreted as unknown. This is useful
     * when the address is generated dynamically at runtime or can't be known upfront. It MAY contain Channel Address
     * Expressions. Query parameters and fragments SHALL NOT be used, instead use bindings to define them.
     */
    @JsonProperty(value = "address")
    private String address;

    /**
     * A map of the messages that will be sent to this channel by any application at any time. Every message sent to
     * this channel MUST be valid against one, and only one, of the message objects defined in this map.
     */
    @JsonProperty(value = "messages")
    private Map<String, MessageReference> messages;

    /**
     * A human-friendly title for the channel.
     */
    @JsonProperty(value = "title")
    private String title;

    /**
     * A short summary of the channel.
     */
    @JsonProperty(value = "summary")
    private String summary;

    /**
     * An optional description of this channel. <a href="https://spec.commonmark.org/">CommonMark syntax</a> can be
     * used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * An array of $ref pointers to the definition of the servers in which this channel is available. If the channel
     * is located in the root Channels Object, it MUST point to a subset of server definitions located in the root
     * Servers Object, and MUST NOT point to a subset of server definitions located in the Components Object or
     * anywhere else. If the channel is located in the Components Object, it MAY point to a Server Objects in any
     * location. If servers is absent or empty, this channel MUST be available on all the servers defined in the
     * Servers Object. Please note the servers property value MUST be an array of Reference Objects and, therefore,
     * MUST NOT contain an array of Server Objects. However, it is RECOMMENDED that parsers (or other software)
     * dereference this property for a better development experience.
     */
    @JsonProperty(value = "servers")
    private List<ServerReference> servers;

    /**
     * A map of the parameters included in the channel address. It MUST be present only when the address contains
     * Channel Address Expressions.
     */
    @JsonProperty(value = "parameters")
    private Map<String, ChannelParameter> parameters;

    /**
     * A map of the parameters included in the channel address. It MUST be present only when the address contains
     * Channel Address Expressions.
     */
    @JsonProperty(value = "tags")
    private List<Tag> tags;

    /**
     * Additional external documentation of the exposed API.
     */
    @JsonProperty(value = "externalDocs")
    private ExternalDocumentation externalDocs;

    /**
     * A map where the keys describe the name of the protocol and the values describe protocol-specific definitions
     * for the channel.
     */
    @JsonProperty(value = "bindings")
    private Map<String, ChannelBinding> bindings;
}
