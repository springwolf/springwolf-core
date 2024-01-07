// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeadersNotDocumented;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            ClassLevelAnnotationChannelsScannerIntegrationTest.TestBindingFactory.class,
            DefaultSchemasService.class,
            PayloadClassExtractor.class,
            ExampleJsonGenerator.class,
            SpringwolfConfigProperties.class,
        })
class ClassLevelAnnotationChannelsScannerIntegrationTest {

    @Autowired
    BindingFactory<TestClassListener> bindingFactory;

    @Autowired
    PayloadClassExtractor payloadClassExtractor;

    @Autowired
    SchemasService schemasService;

    private ClassLevelAnnotationChannelsScanner<TestClassListener, TestMethodListener> scanner;

    @BeforeEach
    void setUp() {
        scanner = new ClassLevelAnnotationChannelsScanner<>(
                TestClassListener.class,
                TestMethodListener.class,
                this.bindingFactory,
                new AsyncHeadersNotDocumented(),
                payloadClassExtractor,
                schemasService);
    }

    @Nested
    class NoClassListener {
        @Test
        void scan_componentHasNoClassLevelRabbitListenerAnnotation() {
            // when
            List<Map.Entry<String, ChannelObject>> channels =
                    scanner.process(ClassWithoutClassListener.class).toList();

            // then
            assertThat(channels).isEmpty();
        }

        private static class ClassWithoutClassListener {

            @TestMethodListener
            private void methodWithAnnotation(SimpleFoo payload) {}
        }
    }

    @Nested
    class NoMethodListener {
        @Test
        void scan_componentHasNoClassLevelRabbitListenerAnnotation() {
            // when
            List<Map.Entry<String, ChannelObject>> channels =
                    scanner.process(ClassWithoutMethodListener.class).toList();

            // then
            assertThat(channels).isEmpty();
        }

        @TestClassListener
        private static class ClassWithoutMethodListener {

            private void methodWithAnnotation(SimpleFoo payload) {}
        }
    }

    @Nested
    class OneMethodLevelAnnotation {
        @Test
        void scan_componentWithOneMethodLevelAnnotation() {
            // when
            List<Map.Entry<String, ChannelObject>> actualChannels =
                    scanner.process(ClassWithOneMethodLevelHandler.class).toList();

            // then
            MessageObject message = MessageObject.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    //                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    // FIXME
                    //
                    .headers(
                            MessageHeaders.of(MessageReference.fromSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                    // FIXME
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            Operation operation = Operation.builder()
                    .description("Auto-generated description")
                    .title("test-channel_publish_ClassWithOneMethodLevelHandler")
                    .bindings(TestBindingFactory.defaultOperationBinding)
                    //                    .message(message) FIXME
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .bindings(TestBindingFactory.defaultChannelBinding)
                    //                    .publish(operation) FIXME
                    .build();

            assertThat(actualChannels).containsExactly(Map.entry(TestBindingFactory.CHANNEL, expectedChannel));
        }

        @TestClassListener
        private static class ClassWithOneMethodLevelHandler {

            @TestMethodListener
            private void methodWithAnnotation(SimpleFoo payload) {}

            private void methodWithoutAnnotation() {}
        }
    }

    @Nested
    class MultipleMethodLevelAnnotations {

        @Test
        void scan_componentWithMultipleRabbitHandlerMethods() {
            // when
            List<Map.Entry<String, ChannelObject>> actualChannels =
                    scanner.process(ClassWithMultipleMethodLevelHandlers.class).toList();

            // Then the returned collection contains the channel with message set to oneOf
            MessageObject fooMessage = MessageObject.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    //                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    // FIXME
                    //
                    .headers(
                            MessageHeaders.of(MessageReference.fromSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                    // FIXME
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            MessageObject barMessage = MessageObject.builder()
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    //                    .payload(PayloadReference.fromModelName(String.class.getSimpleName())) FIXME
                    //
                    .headers(
                            MessageHeaders.of(MessageReference.fromSchema(AsyncHeaders.NOT_DOCUMENTED.getSchemaName())))
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            Operation operation = Operation.builder()
                    .description("Auto-generated description")
                    //                    .operationId("test-channel_publish_ClassWithMultipleMethodLevelHandlers")
                    // FIXME
                    .bindings(TestBindingFactory.defaultOperationBinding)
                    //                    .message(toMessageObjectOrComposition(Set.of(fooMessage, barMessage))) FIXME
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .bindings(TestBindingFactory.defaultChannelBinding)
                    //                    .publish(operation) FIXME
                    .build();

            assertThat(actualChannels).containsExactly(Map.entry(TestBindingFactory.CHANNEL, expectedChannel));
        }

        @TestClassListener
        private static class ClassWithMultipleMethodLevelHandlers {

            @TestMethodListener
            private void methodWithAnnotation(SimpleFoo payload) {}

            @TestMethodListener
            private void anotherMethodWithAnnotation(String payload) {}
        }
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
    public @interface TestClassListener {}

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface TestMethodListener {}

    static class TestBindingFactory implements BindingFactory<TestClassListener> {

        public static final String CHANNEL = "test-channel";
        public static final Map<String, MessageBinding> defaultMessageBinding =
                Map.of(CHANNEL, new TestBindingFactory.TestMessageBinding());
        public static final Map<String, ChannelBinding> defaultChannelBinding =
                Map.of(CHANNEL, new TestBindingFactory.TestChannelBinding());
        public static final Map<String, OperationBinding> defaultOperationBinding =
                Map.of(CHANNEL, new TestBindingFactory.TestOperationBinding());

        @Override
        public String getChannelName(TestClassListener annotation) {
            return CHANNEL;
        }

        @Override
        public Map<String, ChannelBinding> buildChannelBinding(TestClassListener annotation) {
            return (Map) defaultChannelBinding;
        }

        @Override
        public Map<String, OperationBinding> buildOperationBinding(TestClassListener annotation) {
            return (Map) defaultOperationBinding;
        }

        @Override
        public Map<String, MessageBinding> buildMessageBinding(TestClassListener annotation) {
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
