// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadService;
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

class SpringAnnotationMethodLevelChannelsScannerTest {

    private final PayloadService payloadService = mock();
    private final HeaderClassExtractor headerClassExtractor = mock(HeaderClassExtractor.class);
    private final BindingFactory<TestListener> bindingFactory = mock(BindingFactory.class);
    private final ComponentsService componentsService = mock(ComponentsService.class);
    SpringAnnotationMethodLevelChannelsScanner<TestListener> scanner = new SpringAnnotationMethodLevelChannelsScanner<>(
            TestListener.class,
            bindingFactory,
            new AsyncHeadersNotDocumented(),
            payloadService,
            headerClassExtractor,
            componentsService);

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
        doReturn(defaultMessageBinding).when(bindingFactory).buildMessageBinding(any(), any());

        when(payloadService.extractSchema(any()))
                .thenReturn(new NamedSchemaObject(String.class.getName(), new SchemaObject()));
        doAnswer(invocation -> AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())
                .when(componentsService)
                .registerSchema(any(SchemaObject.class));

        var stringMethod =
                ClassWithMultipleTestListenerAnnotation.class.getDeclaredMethod("methodWithAnnotation", String.class);
        when(payloadService.extractSchema(stringMethod))
                .thenReturn(new NamedSchemaObject(String.class.getName(), new SchemaObject()));
        var simpleFooMethod = ClassWithMultipleTestListenerAnnotation.class.getDeclaredMethod(
                "anotherMethodWithAnnotation", SimpleFoo.class);
        when(payloadService.extractSchema(simpleFooMethod))
                .thenReturn(new NamedSchemaObject(SimpleFoo.class.getName(), new SchemaObject()));
    }

    @Test
    void scan_componentHasTestListenerMethods() {
        // when
        List<Map.Entry<String, ChannelObject>> channels =
                scanner.scan(ClassWithTestListenerAnnotation.class).collect(Collectors.toList());

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
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                .bindings(defaultMessageBinding)
                .build();

        ChannelObject expectedChannelItem = ChannelObject.builder()
                .bindings(defaultChannelBinding)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
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
        List<Map.Entry<String, ChannelObject>> channels =
                scanner.scan(ClassWithMultipleTestListenerAnnotation.class).toList();

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
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                .bindings(defaultMessageBinding)
                .build();

        MessageObject simpleFooMessage = MessageObject.builder()
                .messageId(SimpleFoo.class.getName())
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(simpleFooPayload)
                .headers(MessageHeaders.of(
                        MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                .bindings(defaultMessageBinding)
                .build();

        ChannelObject methodChannel = ChannelObject.builder()
                .bindings(defaultChannelBinding)
                .messages(Map.of(stringMessage.getMessageId(), MessageReference.toComponentMessage(stringMessage)))
                .build();
        ChannelObject anotherMethodChannel = ChannelObject.builder()
                .bindings(defaultChannelBinding)
                .messages(
                        Map.of(simpleFooMessage.getMessageId(), MessageReference.toComponentMessage(simpleFooMessage)))
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
