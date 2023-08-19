package io.github.stavshamir.springwolf.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        springwolfSqsProducer.send("channel-name", payload);

        verify(template).send(eq("channel-name"), same(payload));
    }
}
