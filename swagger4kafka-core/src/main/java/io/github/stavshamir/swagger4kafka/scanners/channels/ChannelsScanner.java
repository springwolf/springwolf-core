package io.github.stavshamir.swagger4kafka.scanners.channels;

import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.Channel;

import java.util.Map;
import java.util.Set;

public interface ChannelsScanner {

    Map<String, Channel> scanForChannels(Set<Class<?>> classes);

}
