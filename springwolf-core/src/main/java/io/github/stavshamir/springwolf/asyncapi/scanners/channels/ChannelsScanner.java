package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.types.channel.Channel;

import java.util.Map;
import java.util.Set;

public interface ChannelsScanner {

    Map<String, Channel> scanForChannels(Set<Class<?>> classes);

}
