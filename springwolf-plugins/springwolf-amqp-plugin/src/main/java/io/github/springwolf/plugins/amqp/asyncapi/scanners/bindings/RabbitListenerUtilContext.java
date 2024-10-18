// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public record RabbitListenerUtilContext(
        Map<String, Queue> queueMap, Map<String, Exchange> exchangeMap, Map<String, Binding> bindingMap) {

    public static RabbitListenerUtilContext create(
            List<Queue> queues, List<Exchange> exchanges, List<Binding> bindings) {
        Map<String, Queue> queueMap =
                queues.stream().collect(Collectors.toMap(Queue::getName, Function.identity(), (e1, e2) -> e1));
        Map<String, Exchange> exchangeMap =
                exchanges.stream().collect(Collectors.toMap(Exchange::getName, Function.identity(), (e1, e2) -> e1));
        Map<String, Binding> bindingMap = bindings.stream()
                .filter(Binding::isDestinationQueue)
                .collect(Collectors.toMap(Binding::getDestination, Function.identity(), (e1, e2) -> e1));
        return new RabbitListenerUtilContext(queueMap, exchangeMap, bindingMap);
    }
}
