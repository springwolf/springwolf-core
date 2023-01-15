package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {ClassLevelKafkaListenerScanner.class, DefaultSchemasService.class})
@TestPropertySource(properties = "kafka.topics.test=test-topic")
public class ClassLevelKafkaListenerScannerTest {

    @Autowired
    private ClassLevelKafkaListenerScanner methodLevelKafkaListenerScanner;

    @MockBean
    private ComponentClassScanner componentsScanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    private static final String TOPIC = "test-topic";

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentsScanner.scan()).thenReturn(classesToScan);
    }

    private void setClassesToScan(Set<Class<?>> classesToScan) {
        when(componentsScanner.scan()).thenReturn(classesToScan);
    }

    @Test
    public void scan_componentWithMultipleKafkaListenersAndHandlers() {
        // Given multiple @KafkaListener annotated classes with method(s) annotated with @KafkaHandler
        ImmutableSet<Class<?>> classesToScan = ImmutableSet.of(
                KafkaListenerClassWithOneKafkaHandler.class,
                KafkaListenerClassWithMultipleKafkaHandler.class
        );
        setClassesToScan(classesToScan);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel with message set to oneOf
        Message fooMessage = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();

        Message barMessage = Message.builder()
                .name(SimpleBar.class.getName())
                .title(SimpleBar.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleBar.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("KafkaListenerClassWithMultipleKafkaHandler_publish")
                .bindings(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .message(toMessageObjectOrComposition(ImmutableSet.of(fooMessage, barMessage)))
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .publish(operation)
                .build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry(TOPIC, expectedChannel));
    }

    @Test
    public void scan_componentHasNoClassLevelKafkaListenerAnnotation() {
        // Given a class with one @KafkaHandler method, but no class level @KafkaListener annotation
        setClassToScan(ClassWithoutClassLevelKafkaListenerAndWithOneKafkaHandler.class);

        // When scan is called
        Map<String, ChannelItem> channels = methodLevelKafkaListenerScanner.scan();

        // Then no channel is not created
        assertThat(channels)
                .isEmpty();
    }

    @Test
    public void scan_componentHasNoKafkaHandlerMethods() {
        setClassToScan(KafkaListenerClassWithoutKafkaHandlers.class);

        Map<String, ChannelItem> channels = methodLevelKafkaListenerScanner.scan();

        assertThat(channels)
                .isEmpty();
    }

    @Test
    public void scan_componentWithSingleKafkaHandlerMethod() {
        // Given a @KafkaListener annotated class with one method annotated with @KafkaHandler
        setClassToScan(KafkaListenerClassWithOneKafkaHandler.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleFoo.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("KafkaListenerClassWithOneKafkaHandler_publish")
                .bindings(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .publish(operation)
                .build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry(TOPIC, expectedChannel));
    }

    @Test
    public void scan_componentWithMultipleKafkaHandlerMethods() {
        // Given a @KafkaListener annotated class with multiple methods annotated with @KafkaHandler
        setClassToScan(KafkaListenerClassWithMultipleKafkaHandler.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel with message set to oneOf
        Message fooMessage = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleFoo.class.getSimpleName()))
                .build();

        Message barMessage = Message.builder()
                .name(SimpleBar.class.getName())
                .title(SimpleBar.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleBar.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleBar.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("KafkaListenerClassWithMultipleKafkaHandler_publish")
                .bindings(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .message(toMessageObjectOrComposition(ImmutableSet.of(fooMessage, barMessage)))
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .publish(operation)
                .build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry(TOPIC, expectedChannel));
    }

    @Test
    public void scan_componentWithSingleKafkaHandlerMethod_batchPayload() {
        // Given a @KafkaListener annotated class with one method annotated with @KafkaHandler
        // - There is a payload of type List<?>
        setClassToScan(KafkaListenerClassWithKafkaHandlerWithBatchPayload.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel, and the payload is the generic type of the list
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringKafkaDefaultHeaders-" + SimpleFoo.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("KafkaListenerClassWithKafkaHandlerWithBatchPayload_publish")
                .bindings(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .publish(operation)
                .build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry(TOPIC, expectedChannel));
    }


    private static class ClassWithoutClassLevelKafkaListenerAndWithOneKafkaHandler {

        @KafkaHandler
        private void methodWithAnnotation(SimpleFoo payload) {
        }

    }

    @KafkaListener(topics = TOPIC)
    private static class KafkaListenerClassWithoutKafkaHandlers {

        private void methodWithoutAnnotation() {
        }

    }


    @KafkaListener(topics = TOPIC)
    private static class KafkaListenerClassWithOneKafkaHandler {

        @KafkaHandler
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        private void methodWithoutAnnotation() {
        }

    }

    @KafkaListener(topics = TOPIC)
    private static class KafkaListenerClassWithMultipleKafkaHandler {

        @KafkaHandler
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        @KafkaHandler
        private void anotherMethodWithoutAnnotation(SimpleBar payload) {
        }

    }

    @KafkaListener(topics = TOPIC)
    private static class KafkaListenerClassWithKafkaHandlerWithBatchPayload {

        @KafkaHandler
        private void methodWithAnnotation(List<SimpleFoo> batchPayload) {
        }

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
