// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.channel;

import io.github.springwolf.asyncapi.v3.bindings.EmptyChannelBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.asyncapi.v3.model.server.ServerReference;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ProcessedChannelBinding;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.channel.inferrer.ChannelNameResolver;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
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
    private final ChannelBindingProcessor channelBindingProcessor = mock(ChannelBindingProcessor.class);
    private final List<ChannelBindingProcessor> channelBindingProcessors = List.of(channelBindingProcessor);
    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    private final AsyncApiDocketService asyncApiDocketService = mock(AsyncApiDocketService.class);
    private final ChannelNameResolver channelNameResolver = mock(ChannelNameResolver.class);

    AsyncAnnotationChannelService<TestListener> asyncAnnotationChannelService = new AsyncAnnotationChannelService<>(
            asyncAnnotationProvider,
            asyncAnnotationOperationService,
            asyncAnnotationMessageService,
            channelBindingProcessors,
            stringValueResolver,
            asyncApiDocketService,
            channelNameResolver);

    @BeforeEach
    void setUp() {
        doAnswer(invocation -> invocation.getArgument(0))
                .when(stringValueResolver)
                .resolveStringValue(any());

        doAnswer(invocation -> ((AsyncOperation) invocation.getArgument(0)).channelName())
                .when(channelNameResolver)
                .resolve(any(), any());

        when(channelBindingProcessor.process(any()))
                .thenReturn(Optional.of(new ProcessedChannelBinding("test-binding", new EmptyChannelBinding())));
    }

    @Test
    void scan() throws Exception {
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
                        .bindings(Map.of("test-binding", new EmptyChannelBinding()))
                        .build());
    }

    @Nested
    class ServersField {
        @Test
        void scan() throws Exception {
            // given
            Method method =
                    ClassWithListenerAnnotationWithServer.class.getDeclaredMethod("methodWithAnnotation", String.class);
            MethodAndAnnotation<TestListener> methodAndAnnotation =
                    new MethodAndAnnotation<>(method, method.getAnnotation(TestListener.class));

            when(asyncAnnotationOperationService.buildOperation(any(), anySet()))
                    .thenReturn(Operation.builder().title("operationId").build());
            AsyncApiDocket docket = AsyncApiDocket.builder()
                    .info(Info.builder().build())
                    .servers(Map.of("server1", Server.builder().build()))
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
                            .bindings(Map.of("test-binding", new EmptyChannelBinding()))
                            .build());
        }

        @Test
        void scanInvalid() throws Exception {
            // given
            Method method = ClassWithListenerAnnotationWithInvalidServer.class.getDeclaredMethod(
                    "methodWithAnnotation", String.class);
            MethodAndAnnotation<TestListener> methodAndAnnotation =
                    new MethodAndAnnotation<>(method, method.getAnnotation(TestListener.class));

            when(asyncAnnotationOperationService.buildOperation(any(), anySet()))
                    .thenReturn(Operation.builder().title("operationId").build());
            AsyncApiDocket docket = AsyncApiDocket.builder()
                    .info(Info.builder().build())
                    .servers(Map.of(
                            "server1",
                            Server.builder().build(),
                            "server2",
                            Server.builder().build()))
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
