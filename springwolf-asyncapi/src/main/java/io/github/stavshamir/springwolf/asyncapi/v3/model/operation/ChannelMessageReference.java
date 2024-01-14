// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Reference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ChannelMessageReference implements Message, Reference {

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
     * @param message Message to create the reference to.
     * @return a Message with the 'ref' field pointing to "#/components/messages/{messageName}"
     */
    public static ChannelMessageReference fromMessage(String channelName, MessageObject message) {
        return fromMessage(channelName, MessageReference.fromMessage(message));
    }

    public static ChannelMessageReference fromMessage(String channelName, MessageReference message) {
        return new ChannelMessageReference("#/channels/" + channelName + "/messages/" + message.getId());
    }

    public static ChannelMessageReference fromMessage(String channelName, String messageId) {
        return new ChannelMessageReference("#/channels/" + channelName + "/messages/" + messageId);
    }
}
