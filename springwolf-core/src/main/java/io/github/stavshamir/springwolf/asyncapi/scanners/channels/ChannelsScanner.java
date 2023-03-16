package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2._0_0.model.channel.ChannelItem;

import java.util.Map;

public interface ChannelsScanner {

    /**
     * @return A mapping of channel names to their respective channel object for a given protocol.
     */
    Map<String, ChannelItem> scan();
}
