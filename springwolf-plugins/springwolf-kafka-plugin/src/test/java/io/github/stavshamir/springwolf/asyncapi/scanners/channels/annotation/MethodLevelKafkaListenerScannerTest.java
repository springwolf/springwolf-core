package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {MethodLevelKafkaListenerScanner.class, DefaultSchemasService.class})
@TestPropertySource(properties = "kafka.topics.test=test-topic")
public class MethodLevelKafkaListenerScannerTest {

    @Autowired
    private MethodLevelKafkaListenerScanner methodLevelKafkaListenerScanner;

    @MockBean
    private ComponentClassScanner componentsScanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    private static final String TOPIC = "test-topic";

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentsScanner.scan()).thenReturn(classesToScan);
    }

    @Test
    public void scan_componentHasNoKafkaListenerMethods() {
        setClassToScan(ClassWithoutKafkaListenerAnnotations.class);

        Map<String, ChannelItem> channels = methodLevelKafkaListenerScanner.scan();

        assertThat(channels)
                .isEmpty();
    }

    @Test
    public void scan_componentHasKafkaListenerMethods_hardCodedTopic() {
        // Given a class with methods annotated with KafkaListener, whose topics attribute is hard coded
        setClassToScan(ClassWithKafkaListenerAnnotationHardCodedTopic.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-topic_publish_methodWithAnnotation")
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
    public void scan_componentHasKafkaListenerMethods_embeddedValueTopic() {
        // Given a class with methods annotated with KafkaListener, whose topics attribute is an embedded value
        setClassToScan(ClassWithKafkaListenerAnnotationsEmbeddedValueTopic.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-topic_publish_methodWithAnnotation1")
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
    public void scan_componentHasKafkaListenerMethods_withGroupId() {
        // Given a class with methods annotated with KafkaListener, with a group id
        setClassToScan(ClassWithKafkaListenerAnnotationWithGroupId.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains a correct binding
        Map<String, ? extends OperationBinding> actualBindings = actualChannels.get(TOPIC)
                .getPublish()
                .getBindings();

        assertThat(actualBindings).isNotNull();
        KafkaOperationBinding kafka = (KafkaOperationBinding) actualBindings.get("kafka");
        assertThat(kafka).isNotNull();
        assertThat(kafka.getGroupId())
                .isEqualTo(ClassWithKafkaListenerAnnotationWithGroupId.GROUP_ID);
    }

    @Test
    public void scan_componentHasKafkaListenerMethods_multipleParamsWithoutPayloadAnnotation() {
        // Given a class with a method annotated with KafkaListener:
        // - The method has more than one parameter
        // - No parameter is annotated with @Payload
        setClassToScan(ClassWithKafkaListenerAnnotationMultipleParamsWithoutPayloadAnnotation.class);

        // Then an exception is thrown when scan is called
        assertThatThrownBy(() -> methodLevelKafkaListenerScanner.scan())
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    public void scan_componentHasKafkaListenerMethods_multipleParamsWithPayloadAnnotation() {
        // Given a class with a method annotated with KafkaListener:
        // - The method has more than one parameter
        // - There is a parameter is annotated with @Payload
        setClassToScan(ClassWithKafkaListenerAnnotationMultipleParamsWithPayloadAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel, and the payload is of the parameter annotated with @Payload
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-topic_publish_methodWithAnnotation")
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
    public void scan_componentHasKafkaListenerMethods_batchPayload() {
        // Given a class with a method annotated with KafkaListener with a payload of type List<?>
        setClassToScan(ClassWithKafkaListenerWithBatchPayload.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel, and the payload is the generic type of the list
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-topic_publish_methodWithAnnotation")
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

    private static class ClassWithoutKafkaListenerAnnotations {

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithKafkaListenerAnnotationHardCodedTopic {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithKafkaListenerAnnotationsEmbeddedValueTopic {

        @KafkaListener(topics = "${kafka.topics.test}")
        private void methodWithAnnotation1(SimpleFoo payload) {
        }

    }

    private static class ClassWithKafkaListenerAnnotationWithGroupId {

        private static final String GROUP_ID = "test-group-id";

        @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithKafkaListenerAnnotationMultipleParamsWithoutPayloadAnnotation {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation(SimpleFoo payload, String anotherParam) {
        }

    }

    private static class ClassWithKafkaListenerAnnotationMultipleParamsWithPayloadAnnotation {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation(String anotherParam, @Payload SimpleFoo payload) {
        }

    }

    private static class ClassWithKafkaListenerWithBatchPayload {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation(List<SimpleFoo> batchPayload) {
        }

    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

}
