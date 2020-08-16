package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.types.channel.Channel;

import java.util.Map;

public interface ChannelsService {

    Map<String, Channel> getChannels();

}
