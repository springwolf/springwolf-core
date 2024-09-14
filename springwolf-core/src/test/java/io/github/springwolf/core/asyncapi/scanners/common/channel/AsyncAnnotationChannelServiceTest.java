// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.channel;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.server.ServerReference;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.AsyncAnnotationOperationService;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncAnnotationChannelServiceTest {
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
    private final AsyncAnnotationOperationService<TestListener> asyncAnnotationOperationService =
            mock(AsyncAnnotationOperationService.class);
    private final StringValueResolver resolver = mock(StringValueResolver.class);
    private final AsyncApiDocketService asyncApiDocketService = mock(AsyncApiDocketService.class);

    AsyncAnnotationChannelService<TestListener> asyncAnnotationChannelService = new AsyncAnnotationChannelService<>(
            asyncAnnotationProvider,
            asyncAnnotationOperationService,
            asyncAnnotationMessageService,
            resolver,
            asyncApiDocketService);

    @BeforeEach
    void setUp() {
        doAnswer(invocation -> invocation.getArgument(0)).when(resolver).resolveStringValue(any());
    }

    @Test
    void scan() throws NoSuchMethodException {
        // given
        Method method = ClassWithListenerAnnotation.class.getDeclaredMethod("methodWithAnnotation", String.class);
        MethodAndAnnotation<TestListener> methodAndAnnotation =
                new MethodAndAnnotation<>(method, method.getAnnotation(TestListener.class));

        MessageObject message =
                MessageObject.builder().messageId(String.class.getName()).build();
        when(asyncAnnotationMessageService.buildMessage(any(), any())).thenReturn(message);

        // when
        ChannelObject channel = asyncAnnotationChannelService.buildChannel(methodAndAnnotation);

        // then
        assertThat(channel)
                .isEqualTo(ChannelObject.builder()
                        .channelId(CHANNEL_ID)
                        .address(CHANNEL)
                        .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                        .build());
    }

    @Nested
    class Server {
        @Test
        void scan() throws NoSuchMethodException {
            // given
            Method method =
                    ClassWithListenerAnnotationWithServer.class.getDeclaredMethod("methodWithAnnotation", String.class);
            MethodAndAnnotation<TestListener> methodAndAnnotation =
                    new MethodAndAnnotation<>(method, method.getAnnotation(TestListener.class));

            when(asyncAnnotationOperationService.buildOperation(any(), any(Method.class), any()))
                    .thenReturn(Operation.builder().title("operationId").build());
            AsyncApiDocket docket = AsyncApiDocket.builder()
                    .info(Info.builder().build())
                    .server("server1", null)
                    .build();
            when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(docket);

            MessageObject message =
                    MessageObject.builder().messageId(String.class.getName()).build();
            when(asyncAnnotationMessageService.buildMessage(any(), any())).thenReturn(message);

            // when
            ChannelObject channel = asyncAnnotationChannelService.buildChannel(methodAndAnnotation);

            // then
            assertThat(channel)
                    .isEqualTo(ChannelObject.builder()
                            .channelId(CHANNEL_ID)
                            .address(CHANNEL)
                            .servers(List.of(ServerReference.fromServer("server1")))
                            .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                            .build());
        }

        @Test
        void scanInvalid() throws NoSuchMethodException {
            // given
            Method method = ClassWithListenerAnnotationWithInvalidServer.class.getDeclaredMethod(
                    "methodWithAnnotation", String.class);
            MethodAndAnnotation<TestListener> methodAndAnnotation =
                    new MethodAndAnnotation<>(method, method.getAnnotation(TestListener.class));

            when(asyncAnnotationOperationService.buildOperation(any(), any(Method.class), any()))
                    .thenReturn(Operation.builder().title("operationId").build());
            AsyncApiDocket docket = AsyncApiDocket.builder()
                    .info(Info.builder().build())
                    .server("server1", null)
                    .server("server2", null)
                    .build();
            when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(docket);

            // when
            assertThatThrownBy(() -> asyncAnnotationChannelService.buildChannel(methodAndAnnotation))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(
                            "Operation 'operationId' defines unknown server ref 'server3'. This AsyncApi defines these server(s): [server1, server2]");
        }

        private static class ClassWithListenerAnnotationWithServer {

            @TestListener(
                    operation =
                            @AsyncOperation(
                                    channelName = CHANNEL,
                                    servers = {"server1"}))
            private void methodWithAnnotation(String payload) {}
        }

        private static class ClassWithListenerAnnotationWithInvalidServer {

            @TestListener(
                    operation =
                            @AsyncOperation(
                                    channelName = CHANNEL,
                                    servers = {"server3"}))
            private void methodWithAnnotation(String payload) {}
        }
    }

    private static class ClassWithListenerAnnotation {

        @TestListener
        private void methodWithAnnotation(String payload) {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestListener {
        AsyncOperation operation() default @AsyncOperation(channelName = CHANNEL);
    }
}
