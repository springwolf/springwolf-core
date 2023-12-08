// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeadersNotDocumented;
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
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
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
            List<Map.Entry<String, ChannelItem>> channels =
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
            List<Map.Entry<String, ChannelItem>> channels =
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
            List<Map.Entry<String, ChannelItem>> actualChannels =
                    scanner.process(ClassWithOneMethodLevelHandler.class).toList();

            // then
            Message message = Message.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    .headers(HeaderReference.fromModelName(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName()))
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            Operation operation = Operation.builder()
                    .description("Auto-generated description")
                    .operationId("test-channel_publish_ClassWithOneMethodLevelHandler")
                    .bindings(TestBindingFactory.defaultOperationBinding)
                    .message(message)
                    .build();

            ChannelItem expectedChannel = ChannelItem.builder()
                    .bindings(TestBindingFactory.defaultChannelBinding)
                    .publish(operation)
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
            List<Map.Entry<String, ChannelItem>> actualChannels =
                    scanner.process(ClassWithMultipleMethodLevelHandlers.class).toList();

            // Then the returned collection contains the channel with message set to oneOf
            Message fooMessage = Message.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                    .headers(HeaderReference.fromModelName(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName()))
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            Message barMessage = Message.builder()
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    .payload(PayloadReference.fromModelName(String.class.getSimpleName()))
                    .headers(HeaderReference.fromModelName(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getSchemaName()))
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            Operation operation = Operation.builder()
                    .description("Auto-generated description")
                    .operationId("test-channel_publish_ClassWithMultipleMethodLevelHandlers")
                    .bindings(TestBindingFactory.defaultOperationBinding)
                    .message(toMessageObjectOrComposition(Set.of(fooMessage, barMessage)))
                    .build();

            ChannelItem expectedChannel = ChannelItem.builder()
                    .bindings(TestBindingFactory.defaultChannelBinding)
                    .publish(operation)
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
        public static final Map<String, ? extends MessageBinding> defaultMessageBinding =
                Map.of(CHANNEL, new TestBindingFactory.TestMessageBinding());
        public static final Map<String, Object> defaultChannelBinding =
                Map.of(CHANNEL, new TestBindingFactory.TestChannelBinding());
        public static final Map<String, Object> defaultOperationBinding =
                Map.of(CHANNEL, new TestBindingFactory.TestOperationBinding());

        @Override
        public String getChannelName(TestClassListener annotation) {
            return CHANNEL;
        }

        @Override
        public Map<String, ? extends ChannelBinding> buildChannelBinding(TestClassListener annotation) {
            return (Map) defaultChannelBinding;
        }

        @Override
        public Map<String, ? extends OperationBinding> buildOperationBinding(TestClassListener annotation) {
            return (Map) defaultOperationBinding;
        }

        @Override
        public Map<String, ? extends MessageBinding> buildMessageBinding(TestClassListener annotation) {
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
