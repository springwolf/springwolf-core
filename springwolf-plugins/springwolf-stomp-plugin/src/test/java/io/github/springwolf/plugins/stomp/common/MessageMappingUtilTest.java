// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.common;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.stomp.StompChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.stomp.StompMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.stomp.StompOperationBinding;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.MessageMappingUtil;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.util.StringValueResolver;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageMappingUtilTest {

    @Nested
    class ChannelName {
        @Test
        void fromTopics() {
            // given
            MessageMapping annotation = mock(MessageMapping.class);
            when(annotation.value()).thenReturn(Arrays.array("${topic-1}", "${topic-2}"));

            StringValueResolver stringValueResolver = mock(StringValueResolver.class);
            when(stringValueResolver.resolveStringValue("${topic-1}")).thenReturn("topic-1");
            when(stringValueResolver.resolveStringValue("${topic-2}")).thenReturn("topic-2");

            // when
            String channelName = MessageMappingUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertThat(channelName).isEqualTo("topic-1");
        }
    }

    @Test
    void buildChannelBinding() {
        // when
        Map<String, ChannelBinding> channelBinding = MessageMappingUtil.buildChannelBinding();

        // then
        assertThat(channelBinding.size()).isEqualTo(1);
        assertThat(channelBinding.get("stomp")).isEqualTo(new StompChannelBinding());
    }

    @Test
    void buildOperationBinding() {
        // given
        MessageMapping annotation = mock(MessageMapping.class);
        StringValueResolver stringValueResolver = mock(StringValueResolver.class);

        // when
        Map<String, OperationBinding> operationBinding =
                MessageMappingUtil.buildOperationBinding(annotation, stringValueResolver);

        // then
        assertThat(operationBinding.size()).isEqualTo(1);
        assertThat(operationBinding.get("stomp")).isEqualTo(new StompOperationBinding());
    }

    @Test
    void buildMessageBinding() {
        // when
        Map<String, MessageBinding> messageBinding = MessageMappingUtil.buildMessageBinding();

        // then
        assertThat(messageBinding.size()).isEqualTo(1);
        assertThat(messageBinding.get("stomp")).isEqualTo(new StompMessageBinding());
    }
}
