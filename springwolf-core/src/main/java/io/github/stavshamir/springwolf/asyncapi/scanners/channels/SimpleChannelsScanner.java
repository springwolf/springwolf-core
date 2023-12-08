// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SimpleChannelsScanner implements ChannelsScanner {

    private final ClassScanner classScanner;

    private final ClassProcessor classProcessor;

    @Override
    public Map<String, ChannelItem> scan() {
        Set<Class<?>> components = classScanner.scan();

        List<Map.Entry<String, ChannelItem>> channels = mapToChannels(components);

        return ChannelMerger.merge(channels);
    }

    private List<Map.Entry<String, ChannelItem>> mapToChannels(Set<Class<?>> components) {
        return components.stream().flatMap(classProcessor::process).collect(Collectors.toList());
    }

    public interface ClassProcessor {
        Stream<Map.Entry<String, ChannelItem>> process(Class<?> clazz);
    }
}
