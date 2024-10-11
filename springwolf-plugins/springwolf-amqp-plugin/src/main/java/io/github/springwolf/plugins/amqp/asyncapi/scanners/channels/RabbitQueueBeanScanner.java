// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings.RabbitListenerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class RabbitQueueBeanScanner implements ChannelsScanner {
    private final List<Queue> queues;
    private final List<Binding> bindings;

    @Override
    public Map<String, ChannelObject> scan() {
        return Stream.concat(
                        queues.stream().map(RabbitListenerUtil::buildChannelObject),
                        bindings.stream()
                                .map(RabbitListenerUtil::buildChannelObject)
                                .flatMap(List::stream))
                .collect(Collectors.toMap(ChannelObject::getChannelId, c -> c, (a, b) -> a));
    }
}
