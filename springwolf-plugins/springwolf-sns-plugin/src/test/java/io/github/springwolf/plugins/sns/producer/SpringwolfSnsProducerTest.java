// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.producer;

import io.awspring.cloud.sns.core.SnsTemplate;
import io.github.springwolf.core.controller.dtos.MessageDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SpringwolfSnsProducerTest {
    private SpringwolfSnsProducer springwolfSnsProducer;

    private SnsTemplate template;
    private final ArgumentCaptor<Message<Object>> messageCaptor = ArgumentCaptor.forClass(Message.class);

    @BeforeEach
    void setUp() {
        template = mock(SnsTemplate.class);

        springwolfSnsProducer = new SpringwolfSnsProducer(Collections.singletonList(template));
    }

    @Test
    void send_defaultExchangeAndChannelNameAsRoutingKey() {
        Map<String, Object> payload = new HashMap<>();
        Map<String, MessageDto.HeaderValue> headers = new HashMap<>() {
            {
                put("header1", new MessageDto.HeaderValue("value1"));
                put("header2", new MessageDto.HeaderValue("value2"));
                put("nullHeader1", new MessageDto.HeaderValue(null));
                put("nullHeader2", null);
            }
        };
        springwolfSnsProducer.send("channel-name", headers, payload);

        verify(template).send(eq("channel-name"), messageCaptor.capture());

        Object producedPayload = messageCaptor.getValue().getPayload();
        Assertions.assertThat(producedPayload).isSameAs(payload);
        MessageHeaders producedHeaders = messageCaptor.getValue().getHeaders();
        Assertions.assertThat(producedHeaders).containsAllEntriesOf(new HashMap<>() {
            {
                put("header1", "value1");
                put("header2", "value2");
            }
        });
        Assertions.assertThat(producedHeaders).doesNotContainKeys("nullHeader1", "nullHeader2");
    }
}
