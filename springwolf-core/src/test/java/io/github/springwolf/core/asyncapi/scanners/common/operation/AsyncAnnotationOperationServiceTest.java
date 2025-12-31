// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.operation;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncAnnotationOperationServiceTest {
    private final AsyncAnnotationProvider<TestMethodListener> asyncAnnotationProvider =
            mock(AsyncAnnotationProvider.class);
    private final OperationBindingProcessor operationBindingProcessor = mock(OperationBindingProcessor.class);
    private final List<OperationBindingProcessor> operationBindingProcessors = List.of(operationBindingProcessor);
    private final AsyncAnnotationMessageService asyncAnnotationMessageService =
            mock(AsyncAnnotationMessageService.class);
    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);

    AsyncAnnotationOperationService<TestMethodListener> asyncAnnotationOperationService =
            new AsyncAnnotationOperationService<>(
                    asyncAnnotationProvider,
                    operationBindingProcessors,
                    asyncAnnotationMessageService,
                    stringValueResolver);

    private static final String CHANNEL_ID = "test-channel-id";
    private static final OperationBinding defaultOperationBinding = new AMQPOperationBinding();

    @BeforeEach
    void setUp() {
        doAnswer(invocation -> invocation.getArgument(0))
                .when(stringValueResolver)
                .resolveStringValue(any());

        when(asyncAnnotationProvider.getOperationType()).thenReturn(OperationAction.RECEIVE);
    }

    @Test
    void buildOperation() {
        // given
        Class<?> clazz = ClassWithTestListenerAnnotation.class;
        AsyncOperation asyncOperation =
                clazz.getAnnotation(TestMethodListener.class).operation();
        Set<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> !method.isSynthetic())
                .collect(Collectors.toSet());

        when(asyncAnnotationProvider.getAsyncOperation(any())).thenReturn(asyncOperation);
        when(asyncAnnotationMessageService.buildMessage(any(), any()))
                .thenReturn(MessageObject.builder().messageId("messageId").build());
        when(operationBindingProcessor.process(any()))
                .thenReturn(Optional.of(new ProcessedOperationBinding("type", defaultOperationBinding)));

        // when
        Operation operation = asyncAnnotationOperationService.buildOperation(asyncOperation, methods);

        // then
        assertThat(operation)
                .isEqualTo(Operation.builder()
                        .operationId(CHANNEL_ID + "_receive_method")
                        .action(OperationAction.RECEIVE)
                        .channel(ChannelReference.fromChannel(CHANNEL_ID))
                        .title(CHANNEL_ID + "_receive")
                        .description("Auto-generated description")
                        .messages(List.of(MessageReference.toChannelMessage(CHANNEL_ID, "messageId")))
                        .bindings(Map.of("type", defaultOperationBinding))
                        .build());
    }

    @Test
    void buildOperationWithMultipleMethods() {
        // given
        Class<?> clazz = ClassWithMultipleTestListenerAnnotation.class;
        AsyncOperation asyncOperation =
                clazz.getAnnotation(TestMethodListener.class).operation();
        Set<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> !method.isSynthetic())
                .collect(Collectors.toSet());

        when(asyncAnnotationProvider.getAsyncOperation(any())).thenReturn(asyncOperation);
        when(asyncAnnotationMessageService.buildMessage(any(), any()))
                .thenReturn(MessageObject.builder().messageId("messageId1").build())
                .thenReturn(MessageObject.builder().messageId("messageId2").build());
        when(operationBindingProcessor.process(any()))
                .thenReturn(Optional.of(new ProcessedOperationBinding("type", defaultOperationBinding)));

        // when
        Operation operation = asyncAnnotationOperationService.buildOperation(asyncOperation, methods);

        // then
        assertThat(operation)
                .isEqualTo(Operation.builder()
                        .operationId(CHANNEL_ID + "_receive_method")
                        .action(OperationAction.RECEIVE)
                        .channel(ChannelReference.fromChannel(CHANNEL_ID))
                        .title(CHANNEL_ID + "_receive")
                        .description("Test description")
                        .messages(List.of(
                                MessageReference.toChannelMessage(CHANNEL_ID, "messageId1"),
                                MessageReference.toChannelMessage(CHANNEL_ID, "messageId2")))
                        .bindings(Map.of("type", defaultOperationBinding))
                        .build());
    }

    @TestMethodListener(operation = @AsyncOperation(channelName = CHANNEL_ID))
    private static class ClassWithTestListenerAnnotation {
        private void method(String payload) {}
    }

    @TestMethodListener(operation = @AsyncOperation(channelName = CHANNEL_ID, description = "Test description"))
    private static class ClassWithMultipleTestListenerAnnotation {
        private void method(String payload) {}

        private void method(Integer payload) {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestMethodListener {
        AsyncOperation operation();
    }
}
