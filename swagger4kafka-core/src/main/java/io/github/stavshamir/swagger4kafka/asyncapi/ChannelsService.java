package io.github.stavshamir.swagger4kafka.asyncapi;

import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.Channel;

import java.util.Map;

public interface ChannelsService {

    Map<String, Channel> getChannels();

}
