// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SpringwolfJmsProducerTest {
    private SpringwolfJmsProducer springwolfJmsProducer;

    private JmsTemplate template;

    @BeforeEach
    void setUp() {
        template = mock(JmsTemplate.class);

        springwolfJmsProducer = new SpringwolfJmsProducer(Collections.singletonList(template));
    }

    @Test
    void send_defaultExchangeAndChannelNameAsRoutingKey() {
        Map<String, Object> payload = new HashMap<>();
        springwolfJmsProducer.send("channel-name", Map.of(), payload);

        verify(template).convertAndSend(eq("channel-name"), same(payload), any(MessagePostProcessor.class));
    }
}
