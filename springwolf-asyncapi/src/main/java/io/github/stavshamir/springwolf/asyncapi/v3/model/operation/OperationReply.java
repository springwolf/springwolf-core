// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Reference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes the reply part that MAY be applied to an Operation Object. If an operation implements the request/reply
 * pattern, the reply object represents the response message.
 *
 * @see https://www.asyncapi.com/docs/reference/specification/v3.0.0#operationReplyObject
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OperationReply extends ExtendableObject implements Reference {

    /**
     * Definition of the address that implementations MUST use for the reply.
     */
    @JsonProperty("address")
    private OperationReplyAddress address;

    /**
     * A $ref pointer to the definition of the channel in which this operation is performed. When address is specified,
     * the address property of the channel referenced by this property MUST be either null or not defined. If the
     * operation reply is located inside a root Operation Object, it MUST point to a channel definition located in
     * the root Channels Object, and MUST NOT point to a channel definition located in the Components Object or
     * anywhere else. If the operation reply is located inside an [Operation Object] in the Components Object or in
     * the Replies Object in the Components Object, it MAY point to a Channel Object in any location. Please note the
     * channel property value MUST be a Reference Object and, therefore, MUST NOT contain a Channel Object. However,
     * it is RECOMMENDED that parsers (or other software) dereference this property for a better development experience.
     */
    @JsonProperty("channel")
    private ChannelReference channel;

    /**
     * A list of $ref pointers pointing to the supported Message Objects that can be processed by this operation as
     * reply. It MUST contain a subset of the messages defined in the channel referenced in this operation reply,
     * and MUST NOT point to a subset of message definitions located in the Components Object or anywhere else. Every
     * message processed by this operation MUST be valid against one, and only one, of the message objects referenced
     * in this list. Please note the messages property value MUST be a list of Reference Objects and, therefore, MUST
     * NOT contain Message Objects. However, it is RECOMMENDED that parsers (or other software) dereference this
     * property for a better development experience.
     */
    @JsonProperty("messages")
    private List<MessageReference> messages;

    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
