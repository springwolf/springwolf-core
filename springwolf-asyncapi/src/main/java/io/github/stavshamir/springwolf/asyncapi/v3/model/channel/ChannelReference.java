// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Reference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelReference implements Channel, Reference {
    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }

    public static ChannelReference fromChannel(String channelName) {
        return new ChannelReference("#/channels/" + channelName);
    }

    /**
     * Convenient Builder to create a Channel reference to an existing Channel
     * @param channel Channel to create the reference to. This Channel MUST have a 'channelId' field
     * @return a Channel with the 'ref' field pointing to "#/channels/{channelId"
     */
    public static ChannelReference fromChannel(ChannelObject channel) {
        var channelId = channel.getChannelId();
        if (channelId == null) {
            throw new IllegalArgumentException("The channel must have a 'channelId' defined");
        }
        return new ChannelReference("#/channels/" + channelId);
    }
}
