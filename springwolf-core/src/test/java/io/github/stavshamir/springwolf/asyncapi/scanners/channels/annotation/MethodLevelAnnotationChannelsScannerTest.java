// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeadersNotDocumented;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MethodLevelAnnotationChannelsScannerTest {

    private final PayloadClassExtractor payloadClassExtractor = mock(PayloadClassExtractor.class);
    private final BindingFactory<TestListener> bindingFactory = mock(BindingFactory.class);
    private final SchemasService schemasService = mock(SchemasService.class);
    MethodLevelAnnotationChannelsScanner<TestListener> scanner = new MethodLevelAnnotationChannelsScanner<>(
            TestListener.class, bindingFactory, payloadClassExtractor, schemasService);

    private static final String CHANNEL = "test-channel";
    private static final Map<String, OperationBinding> defaultOperationBinding =
            Map.of("protocol", new AMQPOperationBinding());
    private static final Map<String, MessageBinding> defaultMessageBinding =
            Map.of("protocol", new AMQPMessageBinding());
    private static final Map<String, ChannelBinding> defaultChannelBinding =
            Map.of("protocol", new AMQPChannelBinding());

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        // when
        when(bindingFactory.getChannelName(any())).thenReturn(CHANNEL);

        doReturn(defaultOperationBinding).when(bindingFactory).buildOperationBinding(any());
        doReturn(defaultChannelBinding).when(bindingFactory).buildChannelBinding(any());
        doReturn(defaultMessageBinding).when(bindingFactory).buildMessageBinding(any());

        doReturn(String.class).when(payloadClassExtractor).extractFrom(any());
        doAnswer(invocation -> invocation.<Class<?>>getArgument(0).getSimpleName())
                .when(schemasService)
                .register(any(Class.class));
        doAnswer(invocation -> AsyncHeaders.NOT_DOCUMENTED.getSchemaName())
                .when(schemasService)
                .register(any(AsyncHeaders.class));

        var stringMethod =
                ClassWithMultipleTestListenerAnnotation.class.getDeclaredMethod("methodWithAnnotation", String.class);
        doReturn(String.class).when(payloadClassExtractor).extractFrom(stringMethod);
        var simpleFooMethod = ClassWithMultipleTestListenerAnnotation.class.getDeclaredMethod(
                "anotherMethodWithAnnotation", SimpleFoo.class);
        doReturn(SimpleFoo.class).when(payloadClassExtractor).extractFrom(simpleFooMethod);
    }

    @Test
    void scan_componentHasTestListenerMethods() {
        // when
        List<Map.Entry<String, ChannelObject>> channels =
                scanner.processChannels(ClassWithTestListenerAnnotation.class).collect(Collectors.toList());

        // then
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId(String.class.getName())
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(payload)
                .headers(MessageHeaders.of(
                        MessageReference.fromSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(defaultMessageBinding)
                .build();

        ChannelObject expectedChannelItem = ChannelObject.builder()
                .bindings(defaultChannelBinding)
                .messages(Map.of(message.getMessageId(), message))
                .build();

        assertThat(channels).containsExactly(Map.entry(CHANNEL, expectedChannelItem));
    }

    private static class ClassWithTestListenerAnnotation {

        @TestListener
        private void methodWithAnnotation(String payload) {}

        private void methodWithoutAnnotation() {}
    }

    @Test
    void scan_componentHasMultipleTestListenerMethods() {
        // when
        List<Map.Entry<String, ChannelObject>> channels = scanner.processChannels(
                        ClassWithMultipleTestListenerAnnotation.class)
                .toList();

        // then
        MessagePayload stringPayload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                .build());
        MessagePayload simpleFooPayload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                .build());

        MessageObject stringMessage = MessageObject.builder()
                .messageId(String.class.getName())
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(stringPayload)
                .headers(MessageHeaders.of(
                        MessageReference.fromSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(defaultMessageBinding)
                .build();

        MessageObject simpleFooMessage = MessageObject.builder()
                .messageId(SimpleFoo.class.getName())
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(simpleFooPayload)
                .headers(MessageHeaders.of(
                        MessageReference.fromSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName())))
                .bindings(defaultMessageBinding)
                .build();

        ChannelObject methodChannel = ChannelObject.builder()
                .bindings(defaultChannelBinding)
                .messages(Map.of(stringMessage.getMessageId(), stringMessage))
                .build();
        ChannelObject anotherMethodChannel = ChannelObject.builder()
                .bindings(defaultChannelBinding)
                .messages(Map.of(simpleFooMessage.getMessageId(), simpleFooMessage))
                .build();

        assertThat(channels)
                .containsExactlyInAnyOrderElementsOf(
                        List.of(Map.entry(CHANNEL, methodChannel), Map.entry(CHANNEL, anotherMethodChannel)));
    }

    private static class ClassWithMultipleTestListenerAnnotation {

        @TestListener
        private void methodWithAnnotation(String payload) {}

        @TestListener
        private void anotherMethodWithAnnotation(SimpleFoo payload) {}
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestListener {}
}
