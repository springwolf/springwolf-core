// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            ClassLevelKafkaListenerScanner.class,
            DefaultSchemasService.class,
            ExampleJsonGenerator.class,
            SpringwolfConfigProperties.class,
        })
@TestPropertySource(properties = "kafka.topics.test=test-topic")
class ClassLevelKafkaListenerScannerIntegrationTest {

    @Autowired
    private ClassLevelKafkaListenerScanner classLevelKafkaListenerScanner;

    @MockBean
    private ComponentClassScanner componentsScanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    private static final String TOPIC = "test-topic";
    private static final Map<String, Object> defaultOperationBinding = Map.of("kafka", new KafkaOperationBinding());
    private static final Map<String, ? extends MessageBinding> defaultMessageBinding =
            Map.of("kafka", new KafkaMessageBinding());
    private static final Map<String, Object> defaultChannelBinding = Map.of("kafka", new KafkaChannelBinding());

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentsScanner.scan()).thenReturn(classesToScan);
    }

    private void setClassesToScan(Set<Class<?>> classesToScan) {
        when(componentsScanner.scan()).thenReturn(classesToScan);
    }

    @Test
    void scan_componentWithMultipleKafkaListenersAndHandlers() {
        // Given multiple @KafkaListener annotated classes with method(s) annotated with @KafkaHandler
        Set<Class<?>> classesToScan =
                Set.of(KafkaListenerClassWithOneKafkaHandler.class, KafkaListenerClassWithMultipleKafkaHandler.class);
        setClassesToScan(classesToScan);

        // When scan is called
        Map<String, ChannelItem> actualChannels = classLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel with message set to oneOf
        Message fooMessage = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleFoo.class.getSimpleName()))
                .bindings(defaultMessageBinding)
                .build();

        Message barMessage = Message.builder()
                .name(SimpleBar.class.getName())
                .title(SimpleBar.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleBar.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleBar.class.getSimpleName()))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("KafkaListenerClassWithMultipleKafkaHandler_publish")
                .bindings(defaultOperationBinding)
                .message(toMessageObjectOrComposition(Set.of(fooMessage, barMessage)))
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(TOPIC, expectedChannel));
    }

    @Test
    void scan_componentHasNoClassLevelKafkaListenerAnnotation() {
        // Given a class with one @KafkaHandler method, but no class level @KafkaListener annotation
        setClassToScan(ClassWithoutClassLevelKafkaListenerAndWithOneKafkaHandler.class);

        // When scan is called
        Map<String, ChannelItem> channels = classLevelKafkaListenerScanner.scan();

        // Then no channel is not created
        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentHasNoKafkaHandlerMethods() {
        setClassToScan(KafkaListenerClassWithoutKafkaHandlers.class);

        Map<String, ChannelItem> channels = classLevelKafkaListenerScanner.scan();

        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentWithSingleKafkaHandlerMethod() {
        // Given a @KafkaListener annotated class with one method annotated with @KafkaHandler
        setClassToScan(KafkaListenerClassWithOneKafkaHandler.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = classLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleFoo.class.getSimpleName()))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("KafkaListenerClassWithOneKafkaHandler_publish")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(TOPIC, expectedChannel));
    }

    @Test
    void scan_componentWithMultipleKafkaHandlerMethods() {
        // Given a @KafkaListener annotated class with multiple methods annotated with @KafkaHandler
        setClassToScan(KafkaListenerClassWithMultipleKafkaHandler.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = classLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel with message set to oneOf
        Message fooMessage = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleFoo.class.getSimpleName()))
                .bindings(defaultMessageBinding)
                .build();

        Message barMessage = Message.builder()
                .name(SimpleBar.class.getName())
                .title(SimpleBar.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleBar.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleBar.class.getSimpleName()))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("KafkaListenerClassWithMultipleKafkaHandler_publish")
                .bindings(defaultOperationBinding)
                .message(toMessageObjectOrComposition(Set.of(fooMessage, barMessage)))
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(TOPIC, expectedChannel));
    }

    @Test
    void scan_componentWithSingleKafkaHandlerMethod_batchPayload() {
        // Given a @KafkaListener annotated class with one method annotated with @KafkaHandler
        // - There is a payload of type List<?>
        setClassToScan(KafkaListenerClassWithKafkaHandlerWithBatchPayload.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = classLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel, and the payload is the generic type of the list
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleFoo.class.getSimpleName()))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("KafkaListenerClassWithKafkaHandlerWithBatchPayload_publish")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(TOPIC, expectedChannel));
    }

    private static class ClassWithoutClassLevelKafkaListenerAndWithOneKafkaHandler {

        @KafkaHandler
        private void methodWithAnnotation(SimpleFoo payload) {}
    }

    @KafkaListener(topics = TOPIC)
    private static class KafkaListenerClassWithoutKafkaHandlers {

        private void methodWithoutAnnotation() {}
    }

    @KafkaListener(topics = TOPIC)
    private static class KafkaListenerClassWithOneKafkaHandler {

        @KafkaHandler
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    @KafkaListener(topics = TOPIC)
    private static class KafkaListenerClassWithMultipleKafkaHandler {

        @KafkaHandler
        private void methodWithAnnotation(SimpleFoo payload) {}

        @KafkaHandler
        private void anotherMethodWithoutAnnotation(SimpleBar payload) {}
    }

    @KafkaListener(topics = TOPIC)
    private static class KafkaListenerClassWithKafkaHandlerWithBatchPayload {

        @KafkaHandler
        private void methodWithAnnotation(List<SimpleFoo> batchPayload) {}
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

    @Data
    @NoArgsConstructor
    private static class SimpleBar {
        private String s;
        private boolean b;
    }
}
