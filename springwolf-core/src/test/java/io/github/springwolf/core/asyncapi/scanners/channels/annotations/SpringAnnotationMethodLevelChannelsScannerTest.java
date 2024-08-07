// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SpringAnnotationMethodLevelChannelsScannerTest {

    private final PayloadMethodService payloadMethodService = mock();
    private final HeaderClassExtractor headerClassExtractor = mock(HeaderClassExtractor.class);
    private final BindingFactory<TestListener> bindingFactory = mock(BindingFactory.class);
    private final ComponentsService componentsService = mock(ComponentsService.class);
    SpringAnnotationMethodLevelChannelsScanner<TestListener> scanner = new SpringAnnotationMethodLevelChannelsScanner<>(
            TestListener.class,
            bindingFactory,
            new AsyncHeadersNotDocumented(),
            payloadMethodService,
            headerClassExtractor,
            componentsService);

    private static final String CHANNEL = "test/channel";
    private static final String CHANNEL_ID = ReferenceUtil.toValidId(CHANNEL);
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

        when(payloadMethodService.extractSchema(any()))
                .thenReturn(new PayloadSchemaObject(
                        String.class.getName(), String.class.getSimpleName(), ComponentSchema.of(new SchemaObject())));
        doAnswer(invocation -> AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())
                .when(componentsService)
                .registerSchema(any(SchemaObject.class));

        var stringMethod =
                ClassWithMultipleTestListenerAnnotation.class.getDeclaredMethod("methodWithAnnotation", String.class);
        when(payloadMethodService.extractSchema(stringMethod))
                .thenReturn(new PayloadSchemaObject(
                        String.class.getName(), String.class.getSimpleName(), ComponentSchema.of(new SchemaObject())));
        var simpleFooMethod = ClassWithMultipleTestListenerAnnotation.class.getDeclaredMethod(
                "anotherMethodWithAnnotation", SimpleFoo.class);
        when(payloadMethodService.extractSchema(simpleFooMethod))
                .thenReturn(new PayloadSchemaObject(
                        SimpleFoo.class.getName(),
                        String.class.getSimpleName(),
                        ComponentSchema.of(new SchemaObject())));
    }

    @Test
    void scan_componentHasHiddenAnnotation() {
        // when
        List<Map.Entry<String, ChannelObject>> channels =
                scanner.scan(ClassWithHiddenAnnotation.class).collect(Collectors.toList());

        // then
        assertThat(channels).isEmpty();
    }

    private static class ClassWithHiddenAnnotation {

        @TestListener
        @Hidden
        private void methodWithAnnotation(String payload) {}

        private void methodWithoutAnnotation() {}
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
                .channelId(CHANNEL_ID)
                .address(CHANNEL)
                .bindings(defaultChannelBinding)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .build();

        assertThat(channels).containsExactly(Map.entry(CHANNEL_ID, expectedChannelItem));
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
                .channelId(CHANNEL_ID)
                .address(CHANNEL)
                .bindings(defaultChannelBinding)
                .messages(Map.of(stringMessage.getMessageId(), MessageReference.toComponentMessage(stringMessage)))
                .build();
        ChannelObject anotherMethodChannel = ChannelObject.builder()
                .channelId(CHANNEL_ID)
                .address(CHANNEL)
                .bindings(defaultChannelBinding)
                .messages(
                        Map.of(simpleFooMessage.getMessageId(), MessageReference.toComponentMessage(simpleFooMessage)))
                .build();

        assertThat(channels)
                .containsExactlyInAnyOrderElementsOf(
                        List.of(Map.entry(CHANNEL_ID, methodChannel), Map.entry(CHANNEL_ID, anotherMethodChannel)));
    }

    private static class ClassWithMultipleTestListenerAnnotation {

        @TestListener
        private void methodWithAnnotation(String payload) {}

        @TestListener
        private void anotherMethodWithAnnotation(SimpleFoo payload) {}
    }

    @Nested
    class HeaderAnnotation {

        @Test
        void scan_componentHasHeaderAnnotation() {
            // given
            when(headerClassExtractor.extractHeader(any(), any()))
                    .thenReturn(SchemaObject.builder()
                            .type(SchemaType.OBJECT)
                            .properties(Map.of(
                                    "header_name",
                                    SchemaObject.builder()
                                            .type(SchemaType.STRING)
                                            .examples(List.of("foobar"))
                                            .build()))
                            .build());

            // when
            scanner.scan(ClassWithMethodWithHeaderAnnotation.class).toList();

            // then
            verify(componentsService)
                    .registerSchema(eq(SchemaObject.builder()
                            .title("HeadersNotDocumented-934983093")
                            .type(SchemaType.OBJECT)
                            .description("There can be headers, but they are not explicitly documented.")
                            .properties(Map.of(
                                    "header_name",
                                    SchemaObject.builder()
                                            .type(SchemaType.STRING)
                                            .examples(List.of("foobar"))
                                            .build()))
                            .build()));
        }

        private static class ClassWithMethodWithHeaderAnnotation {
            @TestListener
            private void methodWithAnnotationAndHeader(
                    @Payload String payload, @Header("header_name") String headerValue) {}
        }
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
