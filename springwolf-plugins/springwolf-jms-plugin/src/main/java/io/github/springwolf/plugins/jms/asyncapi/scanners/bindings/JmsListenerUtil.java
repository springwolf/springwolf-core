// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.jms.JMSChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.jms.JMSMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.jms.JMSOperationBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@Slf4j
public class JmsListenerUtil {

    public static String getChannelName(JmsListener annotation, StringValueResolver resolver) {
        return resolver.resolveStringValue(annotation.destination());
    }

    public static Map<String, ChannelBinding> buildChannelBinding(
            JmsListener annotation, StringValueResolver resolver) {
        return Map.of("jms", new JMSChannelBinding());
    }

    public static Map<String, OperationBinding> buildOperationBinding(
            JmsListener annotation, StringValueResolver resolver) {
        return Map.of("jms", new JMSOperationBinding());
    }

    public static Map<String, MessageBinding> buildMessageBinding() {
        return Map.of("jms", new JMSMessageBinding());
    }
}
