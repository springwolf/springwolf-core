// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
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

class SpringwolfSqsProducerTest {
    private SpringwolfSqsProducer springwolfSqsProducer;

    private SqsTemplate template;

    @BeforeEach
    void setUp() {
        template = mock(SqsTemplate.class);

        springwolfSqsProducer = new SpringwolfSqsProducer(Collections.singletonList(template));
    }

    @Test
    void send_defaultExchangeAndChannelNameAsRoutingKey() {
        Map<String, Object> payload = new HashMap<>();
        Message<?> message = MessageBuilder.withPayload(payload).build();

        springwolfSqsProducer.send("channel-name", message);

        verify(template).send(eq("channel-name"), same(message));
    }
}
