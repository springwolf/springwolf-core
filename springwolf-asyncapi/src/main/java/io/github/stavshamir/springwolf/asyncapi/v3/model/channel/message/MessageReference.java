// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Reference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MessageReference implements Message, Reference {

    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }

    @JsonIgnore
    public String getId() {
        return ref.substring(ref.lastIndexOf('/') + 1);
    }

    /**
     * Convenient Builder to create a Message reference to an existing Message
     *
     * @param message Message to create the reference to. This Message MUST have a 'messageId' field
     * @return a Message with the 'ref' field pointing to "#/components/messages/{messageName}"
     */
    public static MessageReference fromMessage(MessageObject message) {
        return fromMessage(message.getName());
    }

    public static MessageReference fromMessage(String messageName) {
        return new MessageReference("#/components/messages/" + messageName);
    }

    public static MessageReference fromSchema(String schemaName) {
        return new MessageReference("#/components/schemas/" + schemaName);
    }
}
