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
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpringAnnotationClassLevelChannelsScannerTest {

    private final PayloadService payloadService = mock();
    private final BindingFactory<TestClassListener> bindingFactory = mock(BindingFactory.class);
    private final ComponentsService componentsService = mock(ComponentsService.class);
    SpringAnnotationClassLevelChannelsScanner<TestClassListener, TestMethodListener> scanner =
            new SpringAnnotationClassLevelChannelsScanner<>(
                    TestClassListener.class,
                    TestMethodListener.class,
                    bindingFactory,
                    new AsyncHeadersNotDocumented(),
                    payloadService,
                    componentsService);

    private static final String CHANNEL = "test-channel";
    private static final Map<String, OperationBinding> defaultOperationBinding =
            Map.of("protocol", new AMQPOperationBinding());
    private static final Map<String, MessageBinding> defaultMessageBinding =
            Map.of("protocol", new AMQPMessageBinding());
    private static final Map<String, ChannelBinding> defaultChannelBinding =
            Map.of("protocol", new AMQPChannelBinding());

    @BeforeEach
    void setUp() {
        // when
        when(bindingFactory.getChannelName(any())).thenReturn(CHANNEL);

        doReturn(defaultOperationBinding).when(bindingFactory).buildOperationBinding(any());
        doReturn(defaultChannelBinding).when(bindingFactory).buildChannelBinding(any());
        doReturn(defaultMessageBinding).when(bindingFactory).buildMessageBinding(any());

        when(payloadService.extractSchema(any()))
                .thenReturn(new NamedSchemaObject(String.class.getName(), new SchemaObject()));
        doAnswer(invocation -> AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())
                .when(componentsService)
                .registerSchema(any(SchemaObject.class));
    }

    @Test
    void scan_componentHasTestListenerMethods() {
        // when
        List<Map.Entry<String, ChannelObject>> channels =
                scanner.scan(ClassWithTestListenerAnnotation.class).toList();

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

    @TestClassListener
    private static class ClassWithTestListenerAnnotation {
        @TestMethodListener
        private void methodWithAnnotation(String payload) {}

        private void methodWithoutAnnotation() {}
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestClassListener {}

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestMethodListener {}
}
