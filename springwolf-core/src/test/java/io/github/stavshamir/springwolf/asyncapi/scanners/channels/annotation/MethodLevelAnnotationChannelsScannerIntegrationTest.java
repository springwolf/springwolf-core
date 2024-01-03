// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
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

import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScannerIntegrationTest.TestBindingFactory.defaultChannelBinding;
import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScannerIntegrationTest.TestBindingFactory.defaultMessageBinding;
import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScannerIntegrationTest.TestBindingFactory.defaultOperationBinding;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            MethodLevelAnnotationChannelsScannerIntegrationTest.TestBindingFactory.class,
            DefaultSchemasService.class,
            PayloadClassExtractor.class,
            ExampleJsonGenerator.class,
            SpringwolfConfigProperties.class,
        })
class MethodLevelAnnotationChannelsScannerIntegrationTest {
    @Autowired
    BindingFactory<TestChannelListener> bindingFactory;

    @Autowired
    PayloadClassExtractor payloadClassExtractor;

    @Autowired
    SchemasService schemasService;

    private MethodLevelAnnotationChannelsScanner<TestChannelListener> scanner;

    @BeforeEach
    void setUp() {
        scanner = new MethodLevelAnnotationChannelsScanner<>(
                TestChannelListener.class, this.bindingFactory, payloadClassExtractor, schemasService);
    }

    @Nested
    class NoListener {
        @Test
        void scan_componentHasNoListenerMethods() {
            // when
            List<Map.Entry<String, ChannelObject>> channels =
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
            List<Map.Entry<String, ChannelObject>> actualChannels =
                    scanner.process(ClassWithListenerAnnotation.class).toList();

            // then
            MessageObject message = MessageObject.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    //                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    // FIXME
                    //
                    // .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())) FIXME
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            Operation operation = Operation.builder()
                    .description("Auto-generated description")
                    .title("test-channel_publish_methodWithAnnotation")
                    .bindings(TestBindingFactory.defaultOperationBinding)
                    //                    .message(message) FIXME
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .bindings(defaultChannelBinding)
                    //                    .publish(operation) FIXME
                    .build();

            assertThat(actualChannels).containsExactly(Map.entry(TestBindingFactory.CHANNEL, expectedChannel));
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
            List<Map.Entry<String, ChannelObject>> channels = scanner.process(
                            ClassWithTestListenerAnnotationMultiplePayloads.class)
                    .toList();

            // then
            MessageObject messageSimpleFoo = MessageObject.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    //                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    // FIXME
                    //
                    // .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())) FIXME
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();
            MessageObject messageString = MessageObject.builder()
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    //                    .payload(PayloadReference.fromModelName(String.class.getSimpleName())) FIXME
                    //
                    // .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())) FIXME
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            ChannelObject expectedChannelItem = ChannelObject.builder()
                    .bindings(defaultChannelBinding)
                    //                    .publish(Operation.builder() FIXME
                    //                            .description("Auto-generated description")
                    //                            .operationId(TestBindingFactory.CHANNEL +
                    // "_publish_methodWithAnnotation")
                    //                            .bindings(TestBindingFactory.defaultOperationBinding)
                    //                            .message(messageSimpleFoo)
                    //                            .build())
                    .build();

            ChannelObject expectedChannelItem2 = ChannelObject.builder()
                    .bindings(defaultChannelBinding)
                    //                    .publish(Operation.builder() FIXME
                    //                            .description("Auto-generated description")
                    //                            .operationId(TestBindingFactory.CHANNEL +
                    // "_publish_methodWithAnnotation")
                    //                            .bindings(TestBindingFactory.defaultOperationBinding)
                    //                            .message(messageString)
                    //                            .build())
                    .build();

            assertThat(channels)
                    .containsExactly(
                            Map.entry(TestBindingFactory.CHANNEL, expectedChannelItem),
                            Map.entry(TestBindingFactory.CHANNEL, expectedChannelItem2));
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
            List<Map.Entry<String, ChannelObject>> actualChannels =
                    scanner.process(ClassWithListenerMetaAnnotation.class).toList();

            // then
            MessageObject message = MessageObject.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    //                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    // FIXME
                    //
                    // .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())) FIXME
                    .bindings(defaultMessageBinding)
                    .build();

            Operation operation = Operation.builder()
                    .description("Auto-generated description")
                    .title("test-channel_publish_methodWithAnnotation")
                    .bindings(defaultOperationBinding)
                    //                    .message(message) FIXME
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .bindings(defaultChannelBinding)
                    //                    .publish(operation) FIXME
                    .build();

            assertThat(actualChannels).containsExactly(Map.entry(TestBindingFactory.CHANNEL, expectedChannel));
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

    static class TestBindingFactory implements BindingFactory<TestChannelListener> {

        public static final String CHANNEL = "test-channel";
        public static final Map<String, MessageBinding> defaultMessageBinding =
                Map.of(CHANNEL, new TestBindingFactory.TestMessageBinding());
        public static final Map<String, ChannelBinding> defaultChannelBinding =
                Map.of(CHANNEL, new TestBindingFactory.TestChannelBinding());
        public static final Map<String, OperationBinding> defaultOperationBinding =
                Map.of(CHANNEL, new TestBindingFactory.TestOperationBinding());

        @Override
        public String getChannelName(TestChannelListener annotation) {
            return CHANNEL;
        }

        @Override
        public Map<String, ChannelBinding> buildChannelBinding(TestChannelListener annotation) {
            return (Map) defaultChannelBinding;
        }

        @Override
        public Map<String, OperationBinding> buildOperationBinding(TestChannelListener annotation) {
            return (Map) defaultOperationBinding;
        }

        @Override
        public Map<String, MessageBinding> buildMessageBinding(TestChannelListener annotation) {
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
