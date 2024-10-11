// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.asyncapi.scanners.operation.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodReturnService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings.StompBindingSendToFactory;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SendToCustomizerTest {

    private static final String CHANNEL_ID = "queue";
    private static final String MESSAGE_ID = "schema-name";

    private StompBindingSendToFactory bindingFactory = mock(StompBindingSendToFactory.class);
    private PayloadMethodReturnService payloadService = mock(PayloadMethodReturnService.class);

    private SendToCustomizer sendToCustomizer = new SendToCustomizer(bindingFactory, payloadService);

    @Test
    void customize() throws NoSuchMethodException {
        // given
        Operation operation = new Operation();
        when(bindingFactory.getChannelId(any())).thenReturn(CHANNEL_ID);
        when(bindingFactory.getChannelName(any())).thenReturn(CHANNEL_ID);
        when(payloadService.extractSchema(any())).thenReturn(new PayloadSchemaObject(MESSAGE_ID, MESSAGE_ID, null));

        // when
        sendToCustomizer.customize(operation, this.getClass().getDeclaredMethod("testMethod"));

        // then
        assertThat(operation.getReply()).isNotNull();
        assertThat(operation.getReply().getChannel()).isEqualTo(ChannelReference.fromChannel(CHANNEL_ID));
        assertThat(operation.getReply().getMessages())
                .isEqualTo(List.of(MessageReference.toChannelMessage(CHANNEL_ID, MESSAGE_ID)));
    }

    @SendTo(CHANNEL_ID)
    void testMethod() {}
}
