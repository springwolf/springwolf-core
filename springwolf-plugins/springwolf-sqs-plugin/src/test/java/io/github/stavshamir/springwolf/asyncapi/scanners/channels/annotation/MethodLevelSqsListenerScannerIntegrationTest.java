package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.sqs.SQSChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.sqs.SQSMessageBinding;
import com.asyncapi.v2.binding.operation.sqs.SQSOperationBinding;
import io.awspring.cloud.sqs.annotation.SqsListener;
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
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
            MethodLevelSqsListenerScanner.class,
            DefaultSchemasService.class,
            ExampleJsonGenerator.class,
        })
@TestPropertySource(properties = "sqs.value.test=test-queue")
class MethodLevelSqsListenerScannerIntegrationTest {

    @Autowired
    private MethodLevelSqsListenerScanner scanner;

    @MockBean
    private ComponentClassScanner componentsScanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    private static final String QUEUE = "test-queue";
    private static final Map<String, Object> defaultOperationBinding = Map.of("sqs", new SQSOperationBinding());
    private static final Map<String, ? extends MessageBinding> defaultMessageBinding =
            Map.of("sqs", new SQSMessageBinding());
    private static final Map<String, Object> defaultChannelBinding = Map.of("sqs", new SQSChannelBinding());

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentsScanner.scan()).thenReturn(classesToScan);
    }

    @Test
    void scan_componentHasNoSqsListenerMethods() {
        setClassToScan(ClassWithoutSqsListenerAnnotations.class);

        Map<String, ChannelItem> channels = scanner.scan();

        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentHasSqsListenerMethods_hardCodedTopic() {
        // Given a class with methods annotated with SqsListener, whose value attribute is hard coded
        setClassToScan(ClassWithSqsListenerAnnotationHardCodedTopic.class);

        // When scan is called
        Map<String, ChannelItem> actualChannelItems = scanner.scan();

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
                .operationId("test-queue_publish_methodWithAnnotation")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannelItems).containsExactly(Map.entry(QUEUE, expectedChannelItem));
    }

    @Test
    void scan_componentHasSqsListenerMethods_embeddedValueTopic() {
        // Given a class with methods annotated with SqsListener, whose value attribute is an embedded value
        setClassToScan(ClassWithSqsListenerAnnotationsEmbeddedValueTopic.class);

        // When scan is called
        Map<String, ChannelItem> actualChannelItems = scanner.scan();

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
                .operationId("test-queue_publish_methodWithAnnotation1")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannelItems).containsExactly(Map.entry(QUEUE, expectedChannelItem));
    }

    @Test
    void scan_componentHasSqsListenerMethods_multipleQueues() {
        setClassToScan(ClassWithSqsListenerAnnotationMultipleQueues.class);

        Map<String, ChannelItem> actualChannelItems = scanner.scan();

        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_DOCUMENTED.getSchemaName()))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("test-queue_publish_methodWithMultipleQueueNames")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannelItems).containsExactly(Map.entry(QUEUE, expectedChannelItem));
    }

    @Test
    void scan_componentHasSqsListenerMethods_multipleParamsWithoutPayloadAnnotation() {
        // Given a class with a method annotated with SqsListener:
        // - The method has more than one parameter
        // - No parameter is annotated with @Payload
        setClassToScan(ClassWithSqsListenerAnnotationMultipleParamsWithoutPayloadAnnotation.class);

        // Then an exception is thrown when scan is called
        assertThatThrownBy(() -> scanner.scan()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void scan_componentHasSqsListenerMethods_multipleParamsWithPayloadAnnotation() {
        // Given a class with a method annotated with SqsListener:
        // - The method has more than one parameter
        // - There is a parameter is annotated with @Payload
        setClassToScan(ClassWithSqsListenerAnnotationMultipleParamsWithPayloadAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannelItems = scanner.scan();

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
                .operationId("test-queue_publish_methodWithAnnotation")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannelItems).containsExactly(Map.entry(QUEUE, expectedChannelItem));
    }

    private static class ClassWithoutSqsListenerAnnotations {

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithSqsListenerAnnotationHardCodedTopic {

        @SqsListener(value = QUEUE)
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    private static class ClassWithSqsListenerAnnotationsEmbeddedValueTopic {

        @SqsListener(value = "${sqs.value.test}")
        private void methodWithAnnotation1(SimpleFoo payload) {}
    }

    private static class ClassWithSqsListenerAnnotationMultipleParamsWithoutPayloadAnnotation {

        @SqsListener(value = QUEUE)
        private void methodWithAnnotation(SimpleFoo payload, String anotherParam) {}
    }

    private static class ClassWithSqsListenerAnnotationMultipleParamsWithPayloadAnnotation {

        @SqsListener(value = QUEUE)
        private void methodWithAnnotation(String anotherParam, @Payload SimpleFoo payload) {}
    }

    private static class ClassWithSqsListenerAnnotationMultipleQueues {

        @SqsListener(queueNames = {QUEUE, "ignored-name"})
        private void methodWithMultipleQueueNames(String anotherParam, @Payload SimpleFoo payload) {}
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }
}
