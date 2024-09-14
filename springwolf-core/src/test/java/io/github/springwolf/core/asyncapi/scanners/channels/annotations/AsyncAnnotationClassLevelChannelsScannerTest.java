// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.channel.AsyncAnnotationChannelService;
import io.swagger.v3.oas.annotations.Hidden;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AsyncAnnotationClassLevelChannelsScannerTest {
    private static final String CHANNEL = "test/channel";
    private static final String CHANNEL_ID = ReferenceUtil.toValidId(CHANNEL);

    private final AsyncAnnotationProvider<TestClassListener> asyncAnnotationProvider = new AsyncAnnotationProvider<>() {
        @Override
        public Class<TestClassListener> getAnnotation() {
            return TestClassListener.class;
        }

        @Override
        public AsyncOperation getAsyncOperation(TestClassListener annotation) {
            return annotation.operation();
        }

        @Override
        public OperationAction getOperationType() {
            return OperationAction.SEND;
        }
    };

    private final AsyncAnnotationChannelService<TestClassListener> asyncAnnotationChannelService =
            mock(AsyncAnnotationChannelService.class);

    AsyncAnnotationClassLevelChannelsScanner<TestClassListener> channelScanner =
            new AsyncAnnotationClassLevelChannelsScanner<>(asyncAnnotationProvider, asyncAnnotationChannelService);

    @Test
    void scan() {
        // given
        ChannelObject channel = ChannelObject.builder().channelId(CHANNEL_ID).build();
        when(asyncAnnotationChannelService.buildChannel(any())).thenReturn(channel);

        // when
        List<ChannelObject> actualChannels = channelScanner.scan(ClassWithListenerAnnotation.class);

        // then
        assertThat(actualChannels).isEqualTo(List.of(channel, channel));

        int methodsInClass = 2;
        verify(asyncAnnotationChannelService, times(methodsInClass)).buildChannel(any());
    }

    @TestClassListener
    private static class ClassWithListenerAnnotation {

        private void method(String payload) {}

        private void secondMethod(Integer payload) {}

        @Hidden
        private void hiddenMethod() {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestClassListener {
        AsyncOperation operation() default @AsyncOperation(channelName = "");
    }
}
