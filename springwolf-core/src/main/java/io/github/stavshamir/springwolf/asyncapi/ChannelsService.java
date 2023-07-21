package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;

import java.util.Map;

/**
 * Service to detect AsyncAPI channels in the current spring context.
 */
public interface ChannelsService {

    /**
     * Detects all available AsyncAPI ChannelItem in the spring context.
     *
     * @return Map of channel names mapping to detected ChannelItems
     */
    Map<String, ChannelItem> findChannels();

}
