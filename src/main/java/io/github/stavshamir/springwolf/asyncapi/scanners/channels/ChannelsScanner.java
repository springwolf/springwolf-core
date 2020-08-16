package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.types.channel.Channel;

import java.util.Map;

public interface ChannelsScanner {

    /**
     * @return A mapping of channel names to their respective channel object for a given protocol.
     */
    Map<String, Channel> scan();

}
