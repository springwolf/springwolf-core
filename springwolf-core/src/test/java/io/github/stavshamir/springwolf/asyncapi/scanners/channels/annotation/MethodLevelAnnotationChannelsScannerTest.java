// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingBuilder;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
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
    private final BindingBuilder<TestListener> bindingBuilder = mock(BindingBuilder.class);
    private final SchemasService schemasService = mock(SchemasService.class);
    MethodLevelAnnotationChannelsScanner<TestListener> scanner = new MethodLevelAnnotationChannelsScanner<>(
            TestListener.class, bindingBuilder, payloadClassExtractor, schemasService);

    private static final String CHANNEL = "test-channel";
    private static final Map<String, Object> defaultOperationBinding = Map.of("protocol", new AMQPOperationBinding());
    private static final Map<String, ? extends MessageBinding> defaultMessageBinding =
            Map.of("protocol", new AMQPMessageBinding());
    private static final Map<String, Object> defaultChannelBinding = Map.of("protocol", new AMQPChannelBinding());

    @BeforeEach
    void setUp() {
        // when
        when(bindingBuilder.getChannelName(any())).thenReturn(CHANNEL);

        doReturn(defaultOperationBinding).when(bindingBuilder).buildOperationBinding(any());
        doReturn(defaultChannelBinding).when(bindingBuilder).buildChannelBinding(any());
        doReturn(defaultMessageBinding).when(bindingBuilder).buildMessageBinding(any());

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
        List<Map.Entry<String, ChannelItem>> channels =
                scanner.process(ClassWithTestListenerAnnotation.class).collect(Collectors.toList());

        // then
        Message message = Message.builder()
                .name(String.class.getName())
                .title(String.class.getSimpleName())
                .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId(CHANNEL + "_publish_methodWithAnnotation")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
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
