// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.jms.JMSChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.jms.JMSMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.jms.JMSOperationBinding;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.util.StringValueResolver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JmsListenerUtilTest {
    private static final String QUEUE = "test-queue";

    @Nested
    class FullConfiguration {
        @Test
        void getChannelName() {
            // given
            JmsListener annotation = getAnnotation(ClassWithFullJmsListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            String channelName = JmsListenerUtil.getChannelName(annotation, resolver);

            // then
            assertEquals("queue-1", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            JmsListener annotation = getAnnotation(ClassWithFullJmsListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);

            // when
            Map<String, ChannelBinding> channelBinding = JmsListenerUtil.buildChannelBinding(annotation, resolver);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("jms"), channelBinding.keySet());
            assertEquals(new JMSChannelBinding(), channelBinding.get("jms"));
        }

        @Test
        void buildOperationBinding() {
            // given
            JmsListener annotation = getAnnotation(ClassWithFullJmsListenerConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);

            // when
            Map<String, OperationBinding> operationBinding =
                    JmsListenerUtil.buildOperationBinding(annotation, resolver);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("jms"), operationBinding.keySet());
            assertEquals(new JMSOperationBinding(), operationBinding.get("jms"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, MessageBinding> messageBinding = JmsListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("jms"), messageBinding.keySet());
            assertEquals(new JMSMessageBinding(), messageBinding.get("jms"));
        }

        private static class ClassWithFullJmsListenerConfiguration {

            @JmsListener(destination = "${queue-1}")
            private void methodWithAnnotation(String payload) {}
        }
    }

    private static JmsListener getAnnotation(Class<?> clazz) {
        return clazz.getDeclaredMethods()[0].getAnnotation(JmsListener.class);
    }
}
