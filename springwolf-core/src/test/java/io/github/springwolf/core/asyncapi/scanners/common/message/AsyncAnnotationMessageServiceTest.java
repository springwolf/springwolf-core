// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.message;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AsyncAnnotationMessageServiceTest {

    private final PayloadAsyncOperationService payloadAsyncOperationService = mock(PayloadAsyncOperationService.class);
    private final ComponentsService componentsService = mock(ComponentsService.class);
    private final MessageBindingProcessor messageBindingProcessor = mock(MessageBindingProcessor.class);
    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    AsyncAnnotationMessageService asyncAnnotationMessageService = new AsyncAnnotationMessageService(
            payloadAsyncOperationService, componentsService, List.of(messageBindingProcessor), stringValueResolver);

    private final PayloadSchemaObject payloadSchema = new PayloadSchemaObject("full.name", "name", null);

    @BeforeEach
    void setUp() {
        doAnswer(invocation -> invocation.getArgument(0))
                .when(stringValueResolver)
                .resolveStringValue(any());

        when(componentsService.registerSchema(any())).thenReturn("headerSchemaName");
        when(messageBindingProcessor.process(any())).thenReturn(Optional.empty());

        when(payloadAsyncOperationService.extractSchema(any(), any())).thenReturn(payloadSchema);
    }

    @Test
    void buildMessage() throws NoSuchMethodException {
        // given
        Method method = ClassWithAnnotation.class.getDeclaredMethod("methodWithAnnotation", String.class);
        AsyncOperation operationData = method.getAnnotation(TestListener.class).operation();

        // when
        MessageObject message = asyncAnnotationMessageService.buildMessage(operationData, method);

        // then
        assertThat(message)
                .isEqualTo(MessageObject.builder()
                        .title("full.name")
                        .name("full.name")
                        .title("name")
                        .description(null)
                        .headers(MessageHeaders.of(SchemaReference.fromSchema("headerSchemaName")))
                        .payload(MessagePayload.of(MultiFormatSchema.builder()
                                .schema(payloadSchema.payload())
                                .build()))
                        .bindings(Map.of())
                        .build());
    }

    @Nested
    class Description {
        @Test
        public void testAsyncOperationDescription() throws NoSuchMethodException {
            // given
            Method method =
                    ClassWithAsyncOperationDescription.class.getDeclaredMethod("methodWithAnnotation", String.class);
            AsyncOperation operationData =
                    method.getAnnotation(TestListener.class).operation();

            // when
            MessageObject message = asyncAnnotationMessageService.buildMessage(operationData, method);

            // then
            assertThat(message.getDescription()).isNull();
        }

        @Test
        public void testAsyncMessageDescription() throws NoSuchMethodException {
            // given
            Method method =
                    ClassWithAsyncMessageDescription.class.getDeclaredMethod("methodWithAnnotation", String.class);
            AsyncOperation operationData =
                    method.getAnnotation(TestListener.class).operation();

            // when
            MessageObject message = asyncAnnotationMessageService.buildMessage(operationData, method);

            // then
            assertThat(message.getDescription()).isEqualTo("test-description");
        }

        @Test
        public void testSchemaDescription() throws NoSuchMethodException {
            // given
            Method method = ClassWithSchemaDescription.class.getDeclaredMethod("methodWithAnnotation", Foo.class);
            AsyncOperation operationData =
                    method.getAnnotation(TestListener.class).operation();

            // when
            MessageObject message = asyncAnnotationMessageService.buildMessage(operationData, method);

            // then
            assertThat(message.getDescription()).isNull();
        }

        static class ClassWithAsyncOperationDescription {
            @TestListener(operation = @AsyncOperation(channelName = "test-channel", description = "test-description"))
            public void methodWithAnnotation(String payload) {}
        }

        static class ClassWithAsyncMessageDescription {
            @TestListener(
                    operation =
                            @AsyncOperation(
                                    channelName = "test-channel",
                                    message = @AsyncMessage(description = "test-description")))
            public void methodWithAnnotation(String payload) {}
        }

        static class ClassWithSchemaDescription {
            @TestListener(operation = @AsyncOperation(channelName = "test-channel"))
            public void methodWithAnnotation(Foo payload) {}
        }

        @Schema(description = "test-description")
        static class Foo {
            private String value;
        }
    }

    static class ClassWithAnnotation {
        @TestListener
        public void methodWithAnnotation(String payload) {}
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestListener {
        AsyncOperation operation() default @AsyncOperation(channelName = "test-channel");
    }
}
