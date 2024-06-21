// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.springwolf.asyncapi.v3.model.Reference;
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

    public static ChannelReference fromChannel(ChannelObject channel) {
        return new ChannelReference("#/channels/" + channel.getChannelId());
    }

    public static ChannelReference fromChannel(String channelId) {
        return new ChannelReference("#/channels/" + channelId);
    }
}
