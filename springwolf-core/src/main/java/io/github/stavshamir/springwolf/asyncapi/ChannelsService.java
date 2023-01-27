package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2.model.channel.ChannelItem;

import java.util.Map;

public interface ChannelsService {

    Map<String, ChannelItem> getChannels();
}
