// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings.RabbitListenerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RabbitQueueBeanScanner implements ChannelsScanner {
    private final List<Queue> queues;

    @Override
    public Map<String, ChannelObject> scan() {
        return queues.stream()
                .map(RabbitListenerUtil::buildChannelObject)
                .collect(Collectors.toMap(
                        o -> ((AMQPChannelBinding) o.getBindings().get(RabbitListenerUtil.BINDING_NAME))
                                .getQueue()
                                .getName(),
                        c -> c,
                        (a, b) -> a));
    }
}
