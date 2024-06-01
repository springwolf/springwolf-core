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

import static io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScannerIntegrationTest.TestBindingFactory.defaultChannelBinding;
import static io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScannerIntegrationTest.TestBindingFactory.defaultMessageBinding;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            SpringAnnotationMethodLevelChannelsScannerIntegrationTest.TestBindingFactory.class,
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
class SpringAnnotationMethodLevelChannelsScannerIntegrationTest {
    @Autowired
    BindingFactory<TestChannelListener> bindingFactory;

    @Autowired
    PayloadService payloadService;

    @Autowired
    HeaderClassExtractor headerClassExtractor;

    @Autowired
    ComponentsService componentsService;

    private SpringAnnotationMethodLevelChannelsScanner<TestChannelListener> scanner;

    @BeforeEach
    void setUp() {
        scanner = new SpringAnnotationMethodLevelChannelsScanner<>(
                TestChannelListener.class,
                this.bindingFactory,
                new AsyncHeadersNotDocumented(),
                payloadService,
                headerClassExtractor,
                componentsService);
    }

    @Nested
    class NoListener {
        @Test
        void scan_componentHasNoListenerMethods() {
            // when
            List<Map.Entry<String, ChannelObject>> channels =
                    scanner.scan(ClassWithoutListenerAnnotation.class).toList();

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
                    scanner.scan(ClassWithListenerAnnotation.class).toList();

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
                    .bindings(defaultChannelBinding)
                    .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
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
            List<Map.Entry<String, ChannelObject>> channels = scanner.scan(
                            ClassWithTestListenerAnnotationMultiplePayloads.class)
                    .toList();

            // then
            MessagePayload simpleFooPayload = MessagePayload.of(MultiFormatSchema.builder()
                    .schema(SchemaReference.fromSchema(SimpleFoo.class.getSimpleName()))
                    .build());
            MessagePayload stringPayload = MessagePayload.of(MultiFormatSchema.builder()
                    .schema(SchemaReference.fromSchema(String.class.getSimpleName()))
                    .build());

            MessageObject messageSimpleFoo = MessageObject.builder()
                    .messageId(SimpleFoo.class.getName())
                    .name(SimpleFoo.class.getName())
                    .title(SimpleFoo.class.getSimpleName())
                    .payload(simpleFooPayload)
                    .headers(MessageHeaders.of(
                            MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();
            MessageObject messageString = MessageObject.builder()
                    .messageId(String.class.getName())
                    .name(String.class.getName())
                    .title(String.class.getSimpleName())
                    .payload(stringPayload)
                    .headers(MessageHeaders.of(
                            MessageReference.toSchema(AsyncHeadersNotDocumented.NOT_DOCUMENTED.getTitle())))
                    .bindings(TestBindingFactory.defaultMessageBinding)
                    .build();

            ChannelObject expectedChannelItem = ChannelObject.builder()
                    .messages(Map.of(
                            messageSimpleFoo.getMessageId(), MessageReference.toComponentMessage(messageSimpleFoo)))
                    .bindings(defaultChannelBinding)
                    .build();

            ChannelObject expectedChannelItem2 = ChannelObject.builder()
                    .bindings(defaultChannelBinding)
                    .messages(Map.of(messageString.getMessageId(), MessageReference.toComponentMessage(messageString)))
                    .build();

            assertThat(channels)
                    .containsExactlyInAnyOrder(
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
                    scanner.scan(ClassWithListenerMetaAnnotation.class).toList();

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
                    .bindings(defaultMessageBinding)
                    .build();

            ChannelObject expectedChannel = ChannelObject.builder()
                    .bindings(defaultChannelBinding)
                    .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
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
            return defaultChannelBinding;
        }

        @Override
        public Map<String, OperationBinding> buildOperationBinding(TestChannelListener annotation) {
            return defaultOperationBinding;
        }

        @Override
        public Map<String, MessageBinding> buildMessageBinding(
                TestChannelListener annotation, SchemaObject headerSchema) {
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
