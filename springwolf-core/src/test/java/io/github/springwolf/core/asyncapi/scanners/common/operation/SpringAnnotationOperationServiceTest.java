// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.operation;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpringAnnotationOperationServiceTest {
    private final BindingFactory<TestMethodListener> bindingFactory = mock(BindingFactory.class);
    private final SpringAnnotationMessageService<TestMethodListener> springAnnotationMessageService =
            mock(SpringAnnotationMessageService.class);
    SpringAnnotationOperationService<TestMethodListener> springAnnotationOperationService =
            new SpringAnnotationOperationService<>(bindingFactory, springAnnotationMessageService);

    private static final String CHANNEL_ID = "test-channel-id";
    private static final Map<String, OperationBinding> defaultOperationBinding =
            Map.of("protocol", new AMQPOperationBinding());

    @BeforeEach
    void setUp() {
        // when
        when(bindingFactory.getChannelId(any())).thenReturn(CHANNEL_ID);
        doReturn(defaultOperationBinding).when(bindingFactory).buildOperationBinding(any());
    }

    @Test
    void scan_componentHasTestListenerMethods() throws NoSuchMethodException {
        // given
        Method method = ClassWithTestListenerAnnotation.class.getDeclaredMethod("methodWithAnnotation", String.class);
        MethodAndAnnotation<TestMethodListener> methodAndAnnotation =
                new MethodAndAnnotation<>(method, method.getAnnotation(TestMethodListener.class));
        PayloadSchemaObject payloadSchemaName = new PayloadSchemaObject("name", "full.name", null);
        SchemaObject headerSchema = new SchemaObject();
        MessageObject messageObject =
                MessageObject.builder().messageId(String.class.getName()).build();

        when(springAnnotationMessageService.buildMessage(
                        methodAndAnnotation.annotation(), payloadSchemaName, headerSchema))
                .thenReturn(messageObject);

        // when
        Operation operations =
                springAnnotationOperationService.buildOperation(methodAndAnnotation, payloadSchemaName, headerSchema);

        // then
        Operation expectedOperation = Operation.builder()
                .operationId(CHANNEL_ID + "_receive_methodWithAnnotation")
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(CHANNEL_ID))
                .messages(List.of(MessageReference.toChannelMessage(CHANNEL_ID, messageObject)))
                .bindings(Map.of("protocol", AMQPOperationBinding.builder().build()))
                .build();
        assertThat(expectedOperation).isEqualTo(operations);
    }

    private static class ClassWithTestListenerAnnotation {
        @TestMethodListener
        private void methodWithAnnotation(String payload) {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestMethodListener {}
}
