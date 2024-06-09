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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageMappingUtilTest {

    @Nested
    public class ChannelName {
        @Test
        void fromTopics() {
            // given
            MessageMapping annotation = mock(MessageMapping.class);
            when(annotation.value()).thenReturn(Arrays.array("${topic-1}", "${topic-2}"));

            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${topic-1}")).thenReturn("topic-1");
            when(resolver.resolveStringValue("${topic-2}")).thenReturn("topic-2");

            // when
            String channelName = MessageMappingUtil.getChannelName(annotation, resolver);

            // then
            assertEquals("topic-1", channelName);
        }
    }

    @Test
    void buildChannelBinding() {
        // when
        Map<String, ChannelBinding> channelBinding = MessageMappingUtil.buildChannelBinding();

        // then
        assertEquals(1, channelBinding.size());
        assertEquals(new StompChannelBinding(), channelBinding.get("stomp"));
    }

    @Test
    void buildOperationBinding() {
        // given
        MessageMapping annotation = mock(MessageMapping.class);
        StringValueResolver resolver = mock(StringValueResolver.class);

        // when
        Map<String, OperationBinding> operationBinding = MessageMappingUtil.buildOperationBinding(annotation, resolver);

        // then
        assertEquals(1, operationBinding.size());
        assertEquals(new StompOperationBinding(), operationBinding.get("stomp"));
    }

    @Test
    void buildMessageBinding() {
        // when
        Map<String, MessageBinding> messageBinding = MessageMappingUtil.buildMessageBinding();

        // then
        assertEquals(1, messageBinding.size());
        assertEquals(new StompMessageBinding(), messageBinding.get("stomp"));
    }
}
