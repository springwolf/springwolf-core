package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ComponentsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import junit.framework.TestCase;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

import static io.github.stavshamir.springwolf.asyncapi.Constants.ONE_OF;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ClassLevelKafkaListenerScanner.class, DefaultSchemasService.class})
@TestPropertySource(properties = "kafka.topics.test=test-topic")
public class ClassLevelKafkaListenerScannerTest extends TestCase {

    @Autowired
    private ClassLevelKafkaListenerScanner methodLevelKafkaListenerScanner;

    @MockBean
    private ComponentsScanner componentsScanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    private static final String TOPIC = "test-topic";

    @Before
    public void setUp() {
        when(asyncApiDocket.getComponentsScanner())
                .thenReturn(componentsScanner);
    }

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentsScanner.scanForComponents()).thenReturn(classesToScan);
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
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
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
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                .build();

        Message barMessage = Message.builder()
                .name(SimpleBar.class.getName())
                .title(SimpleBar.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleBar.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("kafka", new KafkaOperationBinding()))
                .message(ImmutableMap.of(ONE_OF, ImmutableSet.of(fooMessage, barMessage)))
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
