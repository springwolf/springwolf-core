// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.asyncapi.scanners.common;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.stomp.StompChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.stomp.StompMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.stomp.StompOperationBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.util.StringValueResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
public class SendToUserUtil {

    public static String getChannelName(SendToUser annotation, StringValueResolver stringValueResolver) {
        Stream<String> destinations = Arrays.stream(annotation.value());
        List<String> resolvedDestinations =
                destinations.map(stringValueResolver::resolveStringValue).collect(toList());

        log.trace("Found destinations: {}", String.join(", ", resolvedDestinations));
        return resolvedDestinations.get(0);
    }

    public static Map<String, ChannelBinding> buildChannelBinding() {
        return Map.of("stomp", new StompChannelBinding());
    }

    public static Map<String, OperationBinding> buildOperationBinding(
            SendToUser annotation, StringValueResolver stringValueResolver) {
        return Map.of("stomp", new StompOperationBinding());
    }

    public static Map<String, MessageBinding> buildMessageBinding() {
        return Map.of("stomp", new StompMessageBinding());
    }
}
