// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationClassLevelChannelsScannerIntegrationTest.TestBindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessagesService;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SpringAnnotationClassLevelChannelsScannerTest {

    private final SpringAnnotationMessagesService<TestClassListener> springAnnotationMessagesService =
            mock(SpringAnnotationMessagesService.class);
    private final SpringAnnotationChannelService<TestClassListener> springAnnotationChannelService =
            mock(SpringAnnotationChannelService.class);
    SpringAnnotationClassLevelChannelsScanner<TestClassListener, TestMethodListener> scanner =
            new SpringAnnotationClassLevelChannelsScanner<>(
                    TestClassListener.class,
                    TestMethodListener.class,
                    springAnnotationMessagesService,
                    springAnnotationChannelService);

    @Test
    void scan() {
        // given
        MessageReference message = MessageReference.toComponentMessage("messageId");
        Map<String, Message> messages = Map.of("messageId", message);

        when(springAnnotationChannelService.buildChannel(any(), any(), any()))
                .thenReturn(ChannelObject.builder()
                        .channelId(TestBindingFactory.CHANNEL_ID)
                        .messages(messages)
                        .build());

        // when
        List<ChannelObject> channels = scanner.scan(ClassWithTestListenerAnnotation.class);

        // then
        assertThat(channels)
                .isEqualTo(List.of(ChannelObject.builder()
                        .channelId(TestBindingFactory.CHANNEL_ID)
                        .messages(Map.of("messageId", message))
                        .build()));

        int methodsInClass = 2;
        verify(springAnnotationMessagesService)
                .buildMessages(any(), any(), argThat(list -> list.size() == methodsInClass), any());
    }

    @TestClassListener
    private static class ClassWithTestListenerAnnotation {
        @TestMethodListener
        private void methodWithAnnotation(String payload) {}

        @TestMethodListener
        private void methodWithoutAnnotation(Integer integer) {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestClassListener {}

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestMethodListener {}
}
