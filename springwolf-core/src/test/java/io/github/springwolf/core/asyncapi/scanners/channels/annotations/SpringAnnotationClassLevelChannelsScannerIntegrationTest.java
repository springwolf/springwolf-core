// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.DefaultComponentsService;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.DefaultSchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.TypeToClassConverter;
import io.github.springwolf.core.asyncapi.schemas.SchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
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
            SpringAnnotationClassLevelChannelsScannerIntegrationTest.TestBindingFactory.class,
            DefaultComponentsService.class,
            SchemaService.class,
            SwaggerSchemaUtil.class,
            PayloadService.class,
            PayloadClassExtractor.class,
            HeaderClassExtractor.class,
            TypeToClassConverter.class,
            DefaultSchemaWalker.class,
            SchemaWalkerProvider.class,
            ExampleJsonValueGenerator.class,
            SpringwolfConfigProperties.class,
        })
class SpringAnnotationClassLevelChannelsScannerIntegrationTest {

    @Autowired
    BindingFactory<TestClassListener> bindingFactory;

    @Autowired
    PayloadService payloadService;

    @Autowired
    HeaderClassExtractor headerClassExtractor;

    @Autowired
    ComponentsService componentsService;

    private SpringAnnotationClassLevelChannelsScanner<TestClassListener, TestMethodListener> scanner;

    @BeforeEach
    void setUp() {
        scanner = new SpringAnnotationClassLevelChannelsScanner<>(
                TestClassListener.class,
                TestMethodListener.class,
                this.bindingFactory,
                new AsyncHeadersNotDocumented(),
                payloadService,
                headerClassExtractor,
                componentsService);
    }

    @Nested
    class NoClassListener {
        @Test
        void scan_componentHasNoClassLevelRabbitListenerAnnotation() {
            // when
            List<Map.Entry<String, ChannelObject>> channels =
                    scanner.scan(ClassWithoutClassListener.class).toList();

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
                    scanner.scan(ClassWithoutMethodListener.class).toList();

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
                    scanner.scan(ClassWithOneMethodLevelHandler.class).toList();

            // then
            MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                    .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                    .build());

            MessageObject message = MessageObject.builder()
                    .messageId(SimpleFoo.class.getName())
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    .payload(payload)
                    .headers(MessageHeaders.of(
                            MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .bindings(TestBindingFactory.defaultChannelBinding)
                    .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
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
                    scanner.scan(ClassWithMultipleMethodLevelHandlers.class).toList();

            // Then the returned collection contains the channel with message set to oneOf
            MessagePayload simpleFooPayload = MessagePayload.of(MultiFormatSchema.builder()
                    .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                    .build());

            MessageObject fooMessage = MessageObject.builder()
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    .payload(simpleFooPayload)
                    .headers(MessageHeaders.of(
                            MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            MessagePayload stringPayload = MessagePayload.of(MultiFormatSchema.builder()
                    .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                    .build());

            MessageObject barMessage = MessageObject.builder()
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    .payload(stringPayload)
                    .headers(MessageHeaders.of(
                            MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .bindings(TestBindingFactory.defaultChannelBinding)
                    .messages(Map.of(
                            fooMessage.getMessageId(),
                            MessageReference.toComponentMessage(fooMessage),
                            barMessage.getMessageId(),
                            MessageReference.toComponentMessage(barMessage)))
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
            return defaultChannelBinding;
        }

        @Override
        public Map<String, OperationBinding> buildOperationBinding(TestClassListener annotation) {
            return defaultOperationBinding;
        }

        @Override
        public Map<String, MessageBinding> buildMessageBinding(
                TestClassListener annotation, SchemaObject headerSchema) {
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
