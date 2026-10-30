// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodService;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpringAnnotationMethodLevelChannelsScannerTest {

    private final PayloadMethodService payloadMethodService = mock();
    private final HeaderClassExtractor headerClassExtractor = mock(HeaderClassExtractor.class);
    private final SpringAnnotationMessageService<TestListener> springAnnotationMessageService =
            mock(SpringAnnotationMessageService.class);
    private final SpringAnnotationChannelService<TestListener> springAnnotationChannelService =
            mock(SpringAnnotationChannelService.class);
    SpringAnnotationMethodLevelChannelsScanner<TestListener> scanner = new SpringAnnotationMethodLevelChannelsScanner<>(
            TestListener.class,
            payloadMethodService,
            headerClassExtractor,
            springAnnotationChannelService,
            springAnnotationMessageService);

    private static final String CHANNEL = "test/channel";
    private static final String CHANNEL_ID = ReferenceUtil.toValidId(CHANNEL);

    @Test
    void scan() {
        MessageObject message =
                MessageObject.builder().messageId(String.class.getName()).build();
        ChannelObject expectedChannelItem = ChannelObject.builder()
                .channelId(CHANNEL_ID)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .build();

        when(springAnnotationMessageService.buildMessage(any(), any(), any())).thenReturn(message);
        when(springAnnotationChannelService.buildChannel(any(), any())).thenReturn(expectedChannelItem);

        // when
        List<ChannelObject> channels = scanner.scan(ClassWithTestListenerAnnotation.class);

        // then
        assertThat(channels).containsExactly(expectedChannelItem);
    }

    private static class ClassWithTestListenerAnnotation {

        @TestListener
        private void methodWithAnnotation(String payload) {}

        private void methodWithoutAnnotation() {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestListener {}
}
