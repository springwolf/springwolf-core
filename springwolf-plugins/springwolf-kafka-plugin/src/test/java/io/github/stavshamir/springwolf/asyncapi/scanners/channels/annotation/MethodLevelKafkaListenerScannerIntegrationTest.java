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
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {MethodLevelKafkaListenerScanner.class, DefaultSchemasService.class, ExampleJsonGenerator.class})
@TestPropertySource(properties = "kafka.topics.test=test-topic")
class MethodLevelKafkaListenerScannerIntegrationTest {

    @Autowired
    private MethodLevelKafkaListenerScanner methodLevelKafkaListenerScanner;

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

    @Test
    void scan_componentHasNoKafkaListenerMethods() {
        setClassToScan(ClassWithoutKafkaListenerAnnotations.class);

        Map<String, ChannelItem> channels = methodLevelKafkaListenerScanner.scan();

        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentHasKafkaListenerMethods_hardCodedTopic() {
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
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-topic_publish_methodWithAnnotation")
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
    void scan_componentHasKafkaListenerMethods_embeddedValueTopic() {
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
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-topic_publish_methodWithAnnotation1")
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
    void scan_componentHasKafkaListenerMethods_withGroupId() {
        // Given a class with methods annotated with KafkaListener, with a group id
        setClassToScan(ClassWithKafkaListenerAnnotationWithGroupId.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains a correct binding
        Map<String, Object> actualBindings =
                actualChannels.get(TOPIC).getPublish().getBindings();

        assertThat(actualBindings).isNotNull();
        KafkaOperationBinding kafka = (KafkaOperationBinding) actualBindings.get("kafka");
        assertThat(kafka).isNotNull();
        assertThat(kafka.getGroupId())
                .isEqualTo(KafkaListenerUtil.buildKafkaGroupIdSchema(
                        ClassWithKafkaListenerAnnotationWithGroupId.GROUP_ID));
    }

    @Test
    void scan_componentHasKafkaListenerMethods_withDifferentGroupId() {
        // Given a class with methods annotated with KafkaListener, with a group id
        setClassToScan(ClassWithKafkaListenerAnnotationWithDifferentGroupId.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains a correct binding
        Map<String, Object> actualBindings =
                actualChannels.get(TOPIC).getPublish().getBindings();

        assertThat(actualBindings).isNotNull();
        KafkaOperationBinding kafka = (KafkaOperationBinding) actualBindings.get("kafka");
        assertThat(kafka).isNotNull();
        assertThat(kafka.getGroupId())
                .isIn(
                        KafkaListenerUtil.buildKafkaGroupIdSchema(
                                ClassWithKafkaListenerAnnotationWithDifferentGroupId.GROUP_ID_FIRST),
                        KafkaListenerUtil.buildKafkaGroupIdSchema(
                                ClassWithKafkaListenerAnnotationWithDifferentGroupId.GROUP_ID_SECOND));
    }

    @Test
    void scan_componentHasKafkaListenerMethods_multipleParamsWithoutPayloadAnnotation() {
        // Given a class with a method annotated with KafkaListener:
        // - The method has more than one parameter
        // - No parameter is annotated with @Payload
        setClassToScan(ClassWithKafkaListenerAnnotationMultipleParamsWithoutPayloadAnnotation.class);

        // Then an exception is thrown when scan is called
        assertThatThrownBy(() -> methodLevelKafkaListenerScanner.scan()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void scan_componentHasKafkaListenerMethods_multipleParamsWithPayloadAnnotation() {
        // Given a class with a method annotated with KafkaListener:
        // - The method has more than one parameter
        // - There is a parameter is annotated with @Payload
        setClassToScan(ClassWithKafkaListenerAnnotationMultipleParamsWithPayloadAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = methodLevelKafkaListenerScanner.scan();

        // Then the returned collection contains the channel, and the payload is of the parameter annotated with
        // @Payload
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-topic_publish_methodWithAnnotation")
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
    void scan_componentHasKafkaListenerMethods_batchPayload() {
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
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-topic_publish_methodWithAnnotation")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(TOPIC, expectedChannel));
    }

    private static class ClassWithoutKafkaListenerAnnotations {

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithKafkaListenerAnnotationHardCodedTopic {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithKafkaListenerAnnotationsEmbeddedValueTopic {

        @KafkaListener(topics = "${kafka.topics.test}")
        private void methodWithAnnotation1(SimpleFoo payload) {}
    }

    private static class ClassWithKafkaListenerAnnotationWithGroupId {

        private static final String GROUP_ID = "test-group-id";

        @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithKafkaListenerAnnotationWithDifferentGroupId {
        private static final String GROUP_ID_FIRST = "test-group-id-first";
        private static final String GROUP_ID_SECOND = "test-group-id-second";

        @KafkaListener(topics = TOPIC, groupId = GROUP_ID_FIRST)
        private void methodWithAnnotation(SimpleFoo payload) {}

        @KafkaListener(topics = TOPIC, groupId = GROUP_ID_SECOND)
        private void sameMethodWithDifferentGroupId(SimpleFoo payload) {}
    }

    private static class ClassWithKafkaListenerAnnotationMultipleParamsWithoutPayloadAnnotation {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation(SimpleFoo payload, String anotherParam) {}
    }

    private static class ClassWithKafkaListenerAnnotationMultipleParamsWithPayloadAnnotation {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation(String anotherParam, @Payload SimpleFoo payload) {}
    }

    private static class ClassWithKafkaListenerWithBatchPayload {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation(List<SimpleFoo> batchPayload) {}
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }
}
