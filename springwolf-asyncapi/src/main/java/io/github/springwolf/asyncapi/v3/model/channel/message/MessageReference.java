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
     * @param message Message to create the reference to. This Message MUST have a 'messageId' field
     * @return a Message with the 'ref' field pointing to "#/components/messages/{messageId}"
     */
    public static MessageReference toComponentMessage(MessageObject message) {
        return toComponentMessage(message.getMessageId());
    }

    public static MessageReference toComponentMessage(String messageId) {
        return new MessageReference("#/components/messages/" + messageId);
    }

    public static MessageReference toChannelMessage(String channelId, MessageObject message) {
        return new MessageReference("#/channels/" + channelId + "/messages/" + message.getMessageId());
    }

    public static MessageReference toChannelMessage(String channelId, String messageId) {
        return new MessageReference("#/channels/" + channelId + "/messages/" + messageId);
    }
}
