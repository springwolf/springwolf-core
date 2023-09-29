// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
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
        classes = {ClassLevelRabbitListenerScanner.class, DefaultSchemasService.class, ExampleJsonGenerator.class})
class ClassLevelRabbitListenerScannerIntegrationTest {

    @Autowired
    private ClassLevelRabbitListenerScanner classLevelRabbitListenerScanner;

    @MockBean
    private ComponentClassScanner componentsScanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    private static final String QUEUE = "test-queue";
    private static final Map<String, Object> defaultOperationBinding =
            Map.of("amqp", AMQPOperationBinding.builder().cc(List.of(QUEUE)).build());
    private static final Map<String, ? extends MessageBinding> defaultMessageBinding =
            Map.of("amqp", new AMQPMessageBinding());
    private static final Map<String, Object> defaultChannelBinding = Map.of(
            "amqp",
            AMQPChannelBinding.builder()
                    .is("queue")
                    .exchange(AMQPChannelBinding.ExchangeProperties.builder()
                            .name("")
                            .durable(true)
                            .autoDelete(false)
                            .type(ExchangeTypes.DIRECT)
                            .vhost("/")
                            .build())
                    .queue(AMQPChannelBinding.QueueProperties.builder()
                            .name(QUEUE)
                            .durable(true)
                            .autoDelete(false)
                            .exclusive(false)
                            .vhost("/")
                            .build())
                    .build());

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentsScanner.scan()).thenReturn(classesToScan);
    }

    private void setClassesToScan(Set<Class<?>> classesToScan) {
        when(componentsScanner.scan()).thenReturn(classesToScan);
    }

    @Test
    void scan_componentWithMultipleRabbitListenersAndHandlers() {
        // Given multiple @RabbitListener annotated classes with method(s) annotated with @RabbitHandler
        Set<Class<?>> classesToScan = Set.of(
                RabbitListenerClassWithOneRabbitHandler.class, RabbitListenerClassWithMultipleRabbitHandler.class);
        setClassesToScan(classesToScan);

        // When scan is called
        Map<String, ChannelItem> actualChannels = classLevelRabbitListenerScanner.scan();

        // Then the returned collection contains the channel with message set to oneOf
        Message fooMessage = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringRabbitListenerDefaultHeaders"))
                .bindings(defaultMessageBinding)
                .build();

        Message barMessage = Message.builder()
                .name(SimpleBar.class.getName())
                .title(SimpleBar.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleBar.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringRabbitListenerDefaultHeaders"))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("RabbitListenerClassWithMultipleRabbitHandler_publish")
                .bindings(defaultOperationBinding)
                .message(toMessageObjectOrComposition(Set.of(fooMessage, barMessage)))
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(QUEUE, expectedChannel));
    }

    @Test
    void scan_componentHasNoClassLevelRabbitListenerAnnotation() {
        // Given a class with one @RabbitHandler method, but no class level @RabbitListener annotation
        setClassToScan(ClassWithoutClassLevelRabbitListenerAndWithOneRabbitHandler.class);

        // When scan is called
        Map<String, ChannelItem> channels = classLevelRabbitListenerScanner.scan();

        // Then no channel is not created
        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentHasNoRabbitHandlerMethods() {
        setClassToScan(RabbitListenerClassWithoutRabbitHandlers.class);

        Map<String, ChannelItem> channels = classLevelRabbitListenerScanner.scan();

        assertThat(channels).isEmpty();
    }

    @Test
    void scan_componentWithSingleRabbitHandlerMethod() {
        // Given a @RabbitListener annotated class with one method annotated with @RabbitHandler
        setClassToScan(RabbitListenerClassWithOneRabbitHandler.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = classLevelRabbitListenerScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringRabbitListenerDefaultHeaders"))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("RabbitListenerClassWithOneRabbitHandler_publish")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(QUEUE, expectedChannel));
    }

    @Test
    void scan_componentWithMultipleRabbitHandlerMethods() {
        // Given a @RabbitListener annotated class with multiple methods annotated with @RabbitHandler
        setClassToScan(RabbitListenerClassWithMultipleRabbitHandler.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = classLevelRabbitListenerScanner.scan();

        // Then the returned collection contains the channel with message set to oneOf
        Message fooMessage = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringRabbitListenerDefaultHeaders"))
                .bindings(defaultMessageBinding)
                .build();

        Message barMessage = Message.builder()
                .name(SimpleBar.class.getName())
                .title(SimpleBar.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleBar.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringRabbitListenerDefaultHeaders"))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("RabbitListenerClassWithMultipleRabbitHandler_publish")
                .bindings(defaultOperationBinding)
                .message(toMessageObjectOrComposition(Set.of(fooMessage, barMessage)))
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(QUEUE, expectedChannel));
    }

    @Test
    void scan_componentWithSingleRabbitHandlerMethod_batchPayload() {
        // Given a @RabbitListener annotated class with one method annotated with @RabbitHandler
        // - There is a payload of type List<?>
        setClassToScan(RabbitListenerClassWithRabbitHandlerWithBatchPayload.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = classLevelRabbitListenerScanner.scan();

        // Then the returned collection contains the channel, and the payload is the generic type of the list
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName("SpringRabbitListenerDefaultHeaders"))
                .bindings(defaultMessageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId("RabbitListenerClassWithRabbitHandlerWithBatchPayload_publish")
                .bindings(defaultOperationBinding)
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder()
                .bindings(defaultChannelBinding)
                .publish(operation)
                .build();

        assertThat(actualChannels).containsExactly(Map.entry(QUEUE, expectedChannel));
    }

    private static class ClassWithoutClassLevelRabbitListenerAndWithOneRabbitHandler {

        @RabbitHandler
        private void methodWithAnnotation(SimpleFoo payload) {}
    }

    @RabbitListener(queues = QUEUE)
    private static class RabbitListenerClassWithoutRabbitHandlers {

        private void methodWithoutAnnotation() {}
    }

    @RabbitListener(queues = QUEUE)
    private static class RabbitListenerClassWithOneRabbitHandler {

        @RabbitHandler
        private void methodWithAnnotation(SimpleFoo payload) {}

        private void methodWithoutAnnotation() {}
    }

    @RabbitListener(queues = QUEUE)
    private static class RabbitListenerClassWithMultipleRabbitHandler {

        @RabbitHandler
        private void methodWithAnnotation(SimpleFoo payload) {}

        @RabbitHandler
        private void anotherMethodWithoutAnnotation(SimpleBar payload) {}
    }

    @RabbitListener(queues = QUEUE)
    private static class RabbitListenerClassWithRabbitHandlerWithBatchPayload {

        @RabbitHandler
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
