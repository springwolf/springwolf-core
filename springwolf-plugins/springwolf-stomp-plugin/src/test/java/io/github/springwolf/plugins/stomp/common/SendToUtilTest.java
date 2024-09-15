// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.common;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.stomp.StompChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.stomp.StompMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.stomp.StompOperationBinding;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.SendToUtil;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.StringValueResolver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SendToUtilTest {

    @Nested
    public class ChannelName {
        @Test
        void fromTopics() {
            // given
            SendTo annotation = mock(SendTo.class);
            when(annotation.value()).thenReturn(Arrays.array("${topic-1}", "${topic-2}"));

            StringValueResolver stringValueResolver = mock(StringValueResolver.class);
            when(stringValueResolver.resolveStringValue("${topic-1}")).thenReturn("topic-1");
            when(stringValueResolver.resolveStringValue("${topic-2}")).thenReturn("topic-2");

            // when
            String channelName = SendToUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertEquals("topic-1", channelName);
        }
    }

    @Test
    void buildChannelBinding() {
        // when
        Map<String, ChannelBinding> channelBinding = SendToUtil.buildChannelBinding();

        // then
        assertEquals(1, channelBinding.size());
        assertEquals(new StompChannelBinding(), channelBinding.get("stomp"));
    }

    @Test
    void buildOperationBinding() {
        // given
        SendTo annotation = mock(SendTo.class);
        StringValueResolver stringValueResolver = mock(StringValueResolver.class);

        // when
        Map<String, OperationBinding> operationBinding =
                SendToUtil.buildOperationBinding(annotation, stringValueResolver);

        // then
        assertEquals(1, operationBinding.size());
        assertEquals(new StompOperationBinding(), operationBinding.get("stomp"));
    }

    @Test
    void buildMessageBinding() {
        // when
        Map<String, MessageBinding> messageBinding = SendToUtil.buildMessageBinding();

        // then
        assertEquals(1, messageBinding.size());
        assertEquals(new StompMessageBinding(), messageBinding.get("stomp"));
    }
}
