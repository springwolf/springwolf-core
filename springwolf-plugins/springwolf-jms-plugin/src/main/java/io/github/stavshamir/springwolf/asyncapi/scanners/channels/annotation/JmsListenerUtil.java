// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.channel.jms.JMSChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.jms.JMSMessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.operation.jms.JMSOperationBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@Slf4j
public class JmsListenerUtil {

    public static String getChannelName(JmsListener annotation, StringValueResolver resolver) {
        return resolver.resolveStringValue(annotation.destination());
    }

    public static Map<String, ? extends ChannelBinding> buildChannelBinding(
            JmsListener annotation, StringValueResolver resolver) {
        return Map.of("jms", new JMSChannelBinding());
    }

    public static Map<String, ? extends OperationBinding> buildOperationBinding(
            JmsListener annotation, StringValueResolver resolver) {
        return Map.of("jms", new JMSOperationBinding());
    }

    public static Map<String, ? extends MessageBinding> buildMessageBinding() {
        return Map.of("jms", new JMSMessageBinding());
    }
}
