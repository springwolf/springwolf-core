// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.producer;

import io.awspring.cloud.sns.core.SnsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SpringwolfSnsProducerTest {
    private SpringwolfSnsProducer springwolfSqsProducer;

    private SnsTemplate template;

    @BeforeEach
    void setUp() {
        template = mock(SnsTemplate.class);

        springwolfSqsProducer = new SpringwolfSnsProducer(Collections.singletonList(template));
    }

    @Test
    void send_defaultExchangeAndChannelNameAsRoutingKey() {
        Map<String, Object> payload = new HashMap<>();
        Message<Map<String, Object>> message =
                MessageBuilder.withPayload(payload).build();
        springwolfSqsProducer.send("channel-name", message);

        verify(template).send(eq("channel-name"), same(message));
    }
}
