// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingBuilder;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScannerIntegrationTest.TestBindingBuilder.defaultChannelBinding;
import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScannerIntegrationTest.TestBindingBuilder.defaultMessageBinding;
import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScannerIntegrationTest.TestBindingBuilder.defaultOperationBinding;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            MethodLevelAnnotationChannelsScannerIntegrationTest.TestBindingBuilder.class,
            DefaultSchemasService.class,
            PayloadClassExtractor.class,
            ExampleJsonGenerator.class,
            SpringwolfConfigProperties.class,
        })
class MethodLevelAnnotationChannelsScannerIntegrationTest {
    @Autowired
    BindingBuilder<TestChannelListener> bindingBuilder;

    @Autowired
    PayloadClassExtractor payloadClassExtractor;

    @Autowired
    SchemasService schemasService;

    private MethodLevelAnnotationChannelsScanner<TestChannelListener> scanner;

    @BeforeEach
    void setUp() {
        scanner = new MethodLevelAnnotationChannelsScanner<>(
                TestChannelListener.class, this.bindingBuilder, payloadClassExtractor, schemasService);
    }

    @Nested
    class NoListener {
        @Test
        void scan_componentHasNoListenerMethods() {
            // when
            List<Map.Entry<String, ChannelItem>> channels =
                    scanner.process(ClassWithoutListenerAnnotation.class).toList();

            // then
            assertThat(channels).isEmpty();
        }

        private static class ClassWithoutListenerAnnotation {
            private void methodWithoutAnnotation() {}
        }
    }

    @Nested
    class WithListener {
        @Test
        void scan_componentHasListenerMethod() {
            // when
            List<Map.Entry<String, ChannelItem>> actualChannels =
                    scanner.process(ClassWithListenerAnnotation.class).toList();

            // then
            Message message = Message.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                    .bindings(TestBindingBuilder.defaultMessageBinding)
                    .build();

            Operation operation = Operation.builder()
                    .description("Auto-generated description")
                    .operationId("test-channel_publish_methodWithAnnotation")
                    .bindings(TestBindingBuilder.defaultOperationBinding)
                    .message(message)
                    .build();

            ChannelItem expectedChannel = ChannelItem.builder()
                    .bindings(defaultChannelBinding)
                    .publish(operation)
                    .build();

            assertThat(actualChannels).containsExactly(Map.entry(TestBindingBuilder.CHANNEL, expectedChannel));
        }

        private static class ClassWithListenerAnnotation {

            @TestChannelListener
            private void methodWithAnnotation(SimpleFoo payload) {}

            private void methodWithoutAnnotation() {}
        }
    }

    @Nested
    class OneChannelTwoPayloads {
        @Test
        void scan_componentHasTestListenerMethods_multiplePayloads() {
            // when
            List<Map.Entry<String, ChannelItem>> channels = scanner.process(
                            ClassWithTestListenerAnnotationMultiplePayloads.class)
                    .toList();

            // then
            Message messageSimpleFoo = Message.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                    .bindings(TestBindingBuilder.defaultMessageBinding)
                    .build();
            Message messageString = Message.builder()
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                    .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                    .bindings(TestBindingBuilder.defaultMessageBinding)
                    .build();

            ChannelItem expectedChannelItem = ChannelItem.builder()
                    .bindings(defaultChannelBinding)
                    .publish(Operation.builder()
                            .description("Auto-generated description")
                            .operationId(TestBindingBuilder.CHANNEL + "_publish_methodWithAnnotation")
                            .bindings(TestBindingBuilder.defaultOperationBinding)
                            .message(messageSimpleFoo)
                            .build())
                    .build();

            ChannelItem expectedChannelItem2 = ChannelItem.builder()
                    .bindings(defaultChannelBinding)
                    .publish(Operation.builder()
                            .description("Auto-generated description")
                            .operationId(TestBindingBuilder.CHANNEL + "_publish_methodWithAnnotation")
                            .bindings(TestBindingBuilder.defaultOperationBinding)
                            .message(messageString)
                            .build())
                    .build();

            assertThat(channels)
                    .containsExactly(
                            Map.entry(TestBindingBuilder.CHANNEL, expectedChannelItem),
                            Map.entry(TestBindingBuilder.CHANNEL, expectedChannelItem2));
        }

        private static class ClassWithTestListenerAnnotationMultiplePayloads {

            @TestChannelListener
            private void methodWithAnnotation(SimpleFoo payload) {}

            @TestChannelListener
            private void methodWithAnnotation(String payload) {}
        }
    }

    @Nested
    class MetaAnnotation {
        @Test
        void scan_componentHasListenerMetaMethod() {
            // when
            List<Map.Entry<String, ChannelItem>> actualChannels =
                    scanner.process(ClassWithListenerMetaAnnotation.class).toList();

            // then
            Message message = Message.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                    .bindings(defaultMessageBinding)
                    .build();

            Operation operation = Operation.builder()
                    .description("Auto-generated description")
                    .operationId("test-channel_publish_methodWithAnnotation")
                    .bindings(defaultOperationBinding)
                    .message(message)
                    .build();

            ChannelItem expectedChannel = ChannelItem.builder()
                    .bindings(defaultChannelBinding)
                    .publish(operation)
                    .build();

            assertThat(actualChannels).containsExactly(Map.entry(TestBindingBuilder.CHANNEL, expectedChannel));
        }

        private static class ClassWithListenerMetaAnnotation {

            @MetaAnnotation.TestChannelListenerMetaAnnotation
            private void methodWithAnnotation(SimpleFoo payload) {}

            private void methodWithoutAnnotation() {}
        }

        @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
        @Retention(RetentionPolicy.RUNTIME)
        @Inherited
        @TestChannelListener
        public @interface TestChannelListenerMetaAnnotation {}
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface TestChannelListener {}

    static class TestBindingBuilder implements BindingBuilder<TestChannelListener> {

        public static final String CHANNEL = "test-channel";
        public static final Map<String, ? extends MessageBinding> defaultMessageBinding =
                Map.of(CHANNEL, new TestBindingBuilder.TestMessageBinding());
        public static final Map<String, Object> defaultChannelBinding =
                Map.of(CHANNEL, new TestBindingBuilder.TestChannelBinding());
        public static final Map<String, Object> defaultOperationBinding =
                Map.of(CHANNEL, new TestBindingBuilder.TestOperationBinding());

        @Override
        public String getChannelName(TestChannelListener annotation) {
            return CHANNEL;
        }

        @Override
        public Map<String, ? extends ChannelBinding> buildChannelBinding(TestChannelListener annotation) {
            return (Map) defaultChannelBinding;
        }

        @Override
        public Map<String, ? extends OperationBinding> buildOperationBinding(TestChannelListener annotation) {
            return (Map) defaultOperationBinding;
        }

        @Override
        public Map<String, ? extends MessageBinding> buildMessageBinding(TestChannelListener annotation) {
            return defaultMessageBinding;
        }

        @EqualsAndHashCode(callSuper = true)
        public static class TestChannelBinding extends ChannelBinding {}

        @EqualsAndHashCode(callSuper = true)
        public static class TestOperationBinding extends OperationBinding {}

        @EqualsAndHashCode(callSuper = true)
        public static class TestMessageBinding extends MessageBinding {}
    }
}
