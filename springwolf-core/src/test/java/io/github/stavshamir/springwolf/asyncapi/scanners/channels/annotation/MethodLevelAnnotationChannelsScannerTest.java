// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
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
    void setUp() {
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
    }

    @Test
    void scan_componentHasTestListenerMethods() {
        // when
        List<Map.Entry<String, ChannelObject>> channels =
                scanner.process(ClassWithTestListenerAnnotation.class).collect(Collectors.toList());

        // then
        MessageObject message = MessageObject.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                //                .payload(PayloadReference.fromModelName(String.class.getSimpleName())) FIXME
                .headers(MessageHeaders.of(MessageReference.fromSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .title(CHANNEL + "_publish_methodWithAnnotation")
                .bindings(defaultOperationBinding)
                //                .message(message) FIXME
                .build();

        ChannelObject expectedChannelItem = ChannelObject.builder()
                .bindings(defaultChannelBinding)
                //                .publish(operation) FIXME
                .build();

        assertThat(channels).containsExactly(Map.entry(CHANNEL, expectedChannelItem));
    }

    private static class ClassWithTestListenerAnnotation {

        @TestListener
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
    @interface TestListener {}
}
