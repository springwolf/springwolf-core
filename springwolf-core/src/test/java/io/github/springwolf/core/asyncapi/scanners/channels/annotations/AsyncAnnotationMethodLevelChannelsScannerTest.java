// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.channel.AsyncAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncAnnotationMethodLevelChannelsScannerTest {
    private static final String CHANNEL = "test/channel";
    private static final String CHANNEL_ID = ReferenceUtil.toValidId(CHANNEL);

    private final AsyncAnnotationProvider<TestListener> asyncAnnotationProvider = new AsyncAnnotationProvider<>() {
        @Override
        public Class<TestListener> getAnnotation() {
            return TestListener.class;
        }

        @Override
        public AsyncOperation getAsyncOperation(TestListener annotation) {
            return annotation.operation();
        }

        @Override
        public OperationAction getOperationType() {
            return OperationAction.SEND;
        }
    };
    private final AsyncAnnotationMessageService asyncAnnotationMessageService =
            mock(AsyncAnnotationMessageService.class);
    private final AsyncAnnotationChannelService<TestListener> asyncAnnotationChannelService =
            mock(AsyncAnnotationChannelService.class);

    AsyncAnnotationMethodLevelChannelsScanner<TestListener> channelScanner =
            new AsyncAnnotationMethodLevelChannelsScanner<>(asyncAnnotationProvider, asyncAnnotationChannelService);

    @Test
    void scan() {
        MessageObject message =
                MessageObject.builder().messageId(String.class.getName()).build();
        ChannelObject expectedChannelItem = ChannelObject.builder()
                .channelId(CHANNEL_ID)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .build();

        when(asyncAnnotationMessageService.buildMessage(any(), any())).thenReturn(message);
        when(asyncAnnotationChannelService.buildChannel(any())).thenReturn(expectedChannelItem);

        // when
        List<ChannelObject> channels = channelScanner.scan(ClassWithTestListenerAnnotation.class);

        // then
        assertThat(channels).containsExactly(expectedChannelItem);
    }

    private static class ClassWithTestListenerAnnotation {

        @TestListener
        private void methodWithAnnotation(String payload) {}

        private void methodWithoutAnnotation() {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestListener {
        AsyncOperation operation() default @AsyncOperation(channelName = "");
    }
}
