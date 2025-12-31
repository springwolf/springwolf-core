// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.asyncapi.scanners.bindings;

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

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
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
            StringValueResolver stringValueResolver = mock(StringValueResolver.class);
            when(stringValueResolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            String channelName = JmsListenerUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertThat(channelName).isEqualTo("queue-1");
        }

        @Test
        void buildChannelBinding() {
            // given
            JmsListener annotation = getAnnotation(ClassWithFullJmsListenerConfiguration.class);
            StringValueResolver stringValueResolver = mock(StringValueResolver.class);

            // when
            Map<String, ChannelBinding> channelBinding =
                    JmsListenerUtil.buildChannelBinding(annotation, stringValueResolver);

            // then
            assertThat(channelBinding.size()).isEqualTo(1);
            assertThat(channelBinding.keySet()).isEqualTo(Sets.newTreeSet("jms"));
            assertThat(channelBinding.get("jms")).isEqualTo(new JMSChannelBinding());
        }

        @Test
        void buildOperationBinding() {
            // given
            JmsListener annotation = getAnnotation(ClassWithFullJmsListenerConfiguration.class);
            StringValueResolver stringValueResolver = mock(StringValueResolver.class);

            // when
            Map<String, OperationBinding> operationBinding =
                    JmsListenerUtil.buildOperationBinding(annotation, stringValueResolver);

            // then
            assertThat(operationBinding.size()).isEqualTo(1);
            assertThat(operationBinding.keySet()).isEqualTo(Sets.newTreeSet("jms"));
            assertThat(operationBinding.get("jms")).isEqualTo(new JMSOperationBinding());
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, MessageBinding> messageBinding = JmsListenerUtil.buildMessageBinding();

            // then
            assertThat(messageBinding.size()).isEqualTo(1);
            assertThat(messageBinding.keySet()).isEqualTo(Sets.newTreeSet("jms"));
            assertThat(messageBinding.get("jms")).isEqualTo(new JMSMessageBinding());
        }

        private static class ClassWithFullJmsListenerConfiguration {

            @JmsListener(destination = "${queue-1}")
            private void methodWithAnnotation(String payload) {}
        }
    }

    private static JmsListener getAnnotation(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> !method.isSynthetic())
                .findFirst()
                .get()
                .getAnnotation(JmsListener.class);
    }
}
