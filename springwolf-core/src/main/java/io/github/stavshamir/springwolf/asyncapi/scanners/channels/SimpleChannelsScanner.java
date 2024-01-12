// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SimpleChannelsScanner implements ChannelsScanner {

    private final ClassScanner classScanner;

    private final ClassProcessor classProcessor;

    @Override
    public Map<String, ChannelObject> scanChannels() {
        Set<Class<?>> components = classScanner.scan();

        List<Map.Entry<String, ChannelObject>> channels = mapToChannels(components);

        return ChannelMerger.mergeChannels(channels);
    }

    @Override
    public Map<String, Operation> scanOperations() {
        Set<Class<?>> components = classScanner.scan();

        List<Map.Entry<String, Operation>> operations = mapToOperations(components);

        return ChannelMerger.mergeOperations(operations);
    }

    private List<Map.Entry<String, ChannelObject>> mapToChannels(Set<Class<?>> components) {
        return components.stream().flatMap(classProcessor::processChannels).toList();
    }

    private List<Map.Entry<String, Operation>> mapToOperations(Set<Class<?>> components) {
        return components.stream().flatMap(classProcessor::processOperations).toList();
    }

    public interface ClassProcessor {
        Stream<Map.Entry<String, ChannelObject>> processChannels(Class<?> clazz);

        Stream<Map.Entry<String, Operation>> processOperations(Class<?> clazz);
    }
}
