// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.operation;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessagesService;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpringAnnotationOperationsServiceTest {
    private final BindingFactory<TestMethodListener> bindingFactory = mock(BindingFactory.class);
    private final SpringAnnotationMessagesService<TestMethodListener> springAnnotationMessageService =
            mock(SpringAnnotationMessagesService.class);
    SpringAnnotationOperationsService<TestMethodListener> springAnnotationOperationsService =
            new SpringAnnotationOperationsService<>(bindingFactory, springAnnotationMessageService);

    private static final String CHANNEL_ID = "test-channel-id";
    private static final Map<String, OperationBinding> defaultOperationBinding =
            Map.of("protocol", new AMQPOperationBinding());

    @Test
    void buildOperation() {
        // given
        Class<ClassWithTestListenerAnnotation> clazz = ClassWithTestListenerAnnotation.class;
        TestMethodListener clazzAnnotation = clazz.getAnnotation(TestMethodListener.class);
        Set<Method> methods = Set.of(clazz.getDeclaredMethods());

        when(bindingFactory.getChannelId(clazzAnnotation)).thenReturn(CHANNEL_ID);
        when(bindingFactory.buildOperationBinding(clazzAnnotation)).thenReturn(defaultOperationBinding);
        when(springAnnotationMessageService.buildMessages(
                        clazzAnnotation, methods, SpringAnnotationMessagesService.MessageType.OPERATION))
                .thenReturn(Map.of("message", MessageReference.toComponentMessage("messageId")));

        // when
        Operation operation = springAnnotationOperationsService.buildOperation(
                clazzAnnotation, ClassWithTestListenerAnnotation.class, methods);

        // then
        assertThat(operation)
                .isEqualTo(Operation.builder()
                        .operationId(CHANNEL_ID + "_receive_ClassWithTestListenerAnnotation")
                        .action(OperationAction.RECEIVE)
                        .channel(ChannelReference.fromChannel(CHANNEL_ID))
                        .messages(List.of(MessageReference.toComponentMessage("messageId")))
                        .bindings(defaultOperationBinding)
                        .build());
    }

    @Test
    void buildOperationWithMultipleMethods() {
        // given
        Class<?> clazz = ClassWithMultipleTestListenerAnnotation.class;
        TestMethodListener clazzAnnotation = clazz.getAnnotation(TestMethodListener.class);
        Set<Method> methods = Set.of(clazz.getDeclaredMethods());

        when(bindingFactory.getChannelId(clazzAnnotation)).thenReturn(CHANNEL_ID);
        when(bindingFactory.buildOperationBinding(clazzAnnotation)).thenReturn(defaultOperationBinding);
        when(springAnnotationMessageService.buildMessages(
                        clazzAnnotation, methods, SpringAnnotationMessagesService.MessageType.OPERATION))
                .thenReturn(Map.of(
                        "message1",
                        MessageReference.toComponentMessage("messageId1"),
                        "message2",
                        MessageReference.toComponentMessage("messageId2")));

        // when
        Operation operation = springAnnotationOperationsService.buildOperation(
                clazzAnnotation, ClassWithTestListenerAnnotation.class, methods);

        // then
        assertThat(operation)
                .usingRecursiveComparison()
                .ignoringFields("messages")
                .isEqualTo(Operation.builder()
                        .operationId(CHANNEL_ID + "_receive_ClassWithTestListenerAnnotation")
                        .action(OperationAction.RECEIVE)
                        .channel(ChannelReference.fromChannel(CHANNEL_ID))
                        .bindings(defaultOperationBinding)
                        .build());
        assertThat(operation.getMessages())
                .containsExactlyInAnyOrder(
                        MessageReference.toComponentMessage("messageId1"),
                        MessageReference.toComponentMessage("messageId2"));
    }

    @TestMethodListener
    private static class ClassWithTestListenerAnnotation {
        private void method(String payload) {}
    }

    @TestMethodListener
    private static class ClassWithMultipleTestListenerAnnotation {
        private void method(String payload) {}

        private void method(Integer payload) {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestMethodListener {}
}
