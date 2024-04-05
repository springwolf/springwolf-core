// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.channel.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.springwolf.asyncapi.v3.model.Reference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
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

    /**
     * Convenient Builder to create a Message reference to an existing Message
     *
     * @param message Message to create the reference to. This Message MUST have a 'messageName' field
     * @return a Message with the 'ref' field pointing to "#/components/messages/{messageName}"
     */
    public static MessageReference toComponentMessage(MessageObject message) {
        return toComponentMessage(message.getName());
    }

    public static MessageReference toComponentMessage(String messageName) {
        return new MessageReference("#/components/messages/" + messageName);
    }

    public static MessageReference toChannelMessage(String channelName, MessageObject message) {
        return new MessageReference("#/channels/" + channelName + "/messages/" + message.getName());
    }

    public static MessageReference toChannelMessage(String channelName, String messageName) {
        return new MessageReference("#/channels/" + channelName + "/messages/" + messageName);
    }

    public static MessageReference toSchema(String schemaName) {
        return new MessageReference("#/components/schemas/" + schemaName);
    }

    public static String extractRefName(String ref) {
        if (ref.contains("/")) {
            return ref.substring(ref.lastIndexOf('/') + 1);
        }
        return ref;
    }
}
