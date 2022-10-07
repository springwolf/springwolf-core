package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ComponentsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        RabbitChannelsScanner.class,
        DefaultSchemasService.class,
        RabbitChannelsScannerTest.ClassWithRabbitListenerAnnotationsBindingBean.class
})
@TestPropertySource(properties = "amqp.queues.test=test-queue")
public class RabbitChannelsScannerTest {


    @Autowired
    private RabbitChannelsScanner rabbitListenerScanner;

    @MockBean
    private ComponentsScanner componentsScanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    private static final String QUEUE = "test-queue";

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
    public void scan_componentHasNoRabbitListenerMethods() {
        setClassToScan(ClassWithoutRabbitListenerAnnotations.class);

        Map<String, ChannelItem> channels = rabbitListenerScanner.scan();

        assertThat(channels)
                .isEmpty();
    }

    @Test
    public void scan_componentHasRabbitListenerMethods_hardCodedTopic() {
        // Given a class with methods annotated with RabbitListener, whose queues attribute is hard coded
        setClassToScan(ClassWithRabbitListenerAnnotationHardCodedTopic.class);

        // When scan is called
        Map<String, ChannelItem> actualChannelItems = rabbitListenerScanner.scan();

        // Then the returned collection contains the channel
        AMQPChannelBinding.ExchangeProperties properties = new AMQPChannelBinding.ExchangeProperties();
        properties.setName("");
        ChannelBinding channelBinding = AMQPChannelBinding.builder()
                .is("routingKey")
                .exchange(properties)
                .build();

        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("amqp", AMQPOperationBinding.builder()
                        .cc(Collections.singletonList(QUEUE))
                        .build()))
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(ImmutableMap.of("amqp", channelBinding))
                .publish(operation)
                .build();

        assertThat(actualChannelItems)
                .containsExactly(Maps.immutableEntry(QUEUE, expectedChannelItem));
    }

    @Test
    public void scan_componentHasRabbitListenerMethods_embeddedValueTopic() {
        // Given a class with methods annotated with RabbitListener, whose queues attribute is an embedded value
        setClassToScan(ClassWithRabbitListenerAnnotationsEmbeddedValueTopic.class);

        // When scan is called
        Map<String, ChannelItem> actualChannelItems = rabbitListenerScanner.scan();

        // Then the returned collection contains the channel
        AMQPChannelBinding.ExchangeProperties properties = new AMQPChannelBinding.ExchangeProperties();
        properties.setName("");
        ChannelBinding channelBinding = AMQPChannelBinding.builder()
                .is("routingKey")
                .exchange(properties)
                .build();

        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("amqp", AMQPOperationBinding.builder()
                        .cc(Collections.singletonList(QUEUE))
                        .build()))
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(ImmutableMap.of("amqp", channelBinding))
                .publish(operation)
                .build();

        assertThat(actualChannelItems)
                .containsExactly(Maps.immutableEntry(QUEUE, expectedChannelItem));
    }

    @Test
    public void scan_componentHasRabbitListenerMethods_bindingsAnnotation() {
        setClassToScan(ClassWithRabbitListenerAnnotationUsingBindings.class);

        Map<String, ChannelItem> actualChannelItems = rabbitListenerScanner.scan();

        AMQPChannelBinding.ExchangeProperties properties = new AMQPChannelBinding.ExchangeProperties();
        properties.setName("name");
        ChannelBinding channelBinding = AMQPChannelBinding.builder()
                .is("routingKey")
                .exchange(properties)
                .build();

        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("amqp", AMQPOperationBinding.builder()
                        .cc(Collections.singletonList("key"))
                        .build()))
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(ImmutableMap.of("amqp", channelBinding))
                .publish(operation)
                .build();

        assertThat(actualChannelItems)
                .containsExactly(Maps.immutableEntry(QUEUE, expectedChannelItem));
    }

    @Test
    public void scan_componentHasRabbitListenerMethods_bindingBean() {
        setClassToScan(ClassWithRabbitListenerAnnotationsBindingBean.class);

        Map<String, ChannelItem> actualChannelItems = rabbitListenerScanner.scan();

        AMQPChannelBinding.ExchangeProperties properties = new AMQPChannelBinding.ExchangeProperties();
        properties.setName("binding-bean-exchange");
        ChannelBinding channelBinding = AMQPChannelBinding.builder()
                .is("routingKey")
                .exchange(properties)
                .build();

        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("amqp", AMQPOperationBinding.builder()
                        .cc(Collections.singletonList("binding-bean-key"))
                        .build()))
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(ImmutableMap.of("amqp", channelBinding))
                .publish(operation)
                .build();

        assertThat(actualChannelItems)
                .containsExactly(Maps.immutableEntry("binding-bean-queue", expectedChannelItem));
    }

    @Test
    public void scan_componentHasRabbitListenerMethods_multipleParamsWithoutPayloadAnnotation() {
        // Given a class with a method annotated with RabbitListener:
        // - The method has more than one parameter
        // - No parameter is annotated with @Payload
        setClassToScan(ClassWithRabbitListenerAnnotationMultipleParamsWithoutPayloadAnnotation.class);

        // Then an exception is thrown when scan is called
        assertThatThrownBy(() -> rabbitListenerScanner.scan())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void scan_componentHasRabbitListenerMethods_multipleParamsWithPayloadAnnotation() {
        // Given a class with a method annotated with RabbitListener:
        // - The method has more than one parameter
        // - There is a parameter is annotated with @Payload
        setClassToScan(ClassWithRabbitListenerAnnotationMultipleParamsWithPayloadAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannelItems = rabbitListenerScanner.scan();

        // Then the returned collection contains the channel, and the payload is of the parameter annotated with @Payload
        AMQPChannelBinding.ExchangeProperties properties = new AMQPChannelBinding.ExchangeProperties();
        properties.setName("");
        ChannelBinding channelBinding = AMQPChannelBinding.builder()
                .is("routingKey")
                .exchange(properties)
                .build();

        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .headers(HeaderReference.fromModelName(AsyncHeaders.NOT_USED.getSchemaName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("amqp", AMQPOperationBinding.builder()
                        .cc(Collections.singletonList(QUEUE))
                        .build()))
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder()
                .bindings(ImmutableMap.of("amqp", channelBinding))
                .publish(operation)
                .build();

        assertThat(actualChannelItems)
                .containsExactly(Maps.immutableEntry(QUEUE, expectedChannelItem));
    }

    private static class ClassWithoutRabbitListenerAnnotations {

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithRabbitListenerAnnotationHardCodedTopic {

        @RabbitListener(queues = QUEUE)
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithRabbitListenerAnnotationUsingBindings {

        @RabbitListener(bindings = {
                @QueueBinding(
                        exchange = @Exchange(name = "name", type = "topic"),
                        key = "key",
                        value = @Queue(name = "test-queue"))
        })
        private void methodWithAnnotation1(SimpleFoo payload) {
        }
    }

    public static class ClassWithRabbitListenerAnnotationsBindingBean {
        @Bean
        public Binding binding() {
            return new Binding("binding-bean-queue",
                    Binding.DestinationType.QUEUE,
                    "binding-bean-exchange",
                    "binding-bean-key",
                    new HashMap<>());
        }

        @RabbitListener(queues = "binding-bean-queue")
        private void methodWithAnnotation1(SimpleFoo payload) {
        }
    }

    private static class ClassWithRabbitListenerAnnotationsEmbeddedValueTopic {

        @RabbitListener(queues = "${amqp.queues.test}")
        private void methodWithAnnotation1(SimpleFoo payload) {
        }

    }

    private static class ClassWithRabbitListenerAnnotationMultipleParamsWithoutPayloadAnnotation {

        @RabbitListener(queues = QUEUE)
        private void methodWithAnnotation(SimpleFoo payload, String anotherParam) {
        }

    }

    private static class ClassWithRabbitListenerAnnotationMultipleParamsWithPayloadAnnotation {

        @RabbitListener(queues = QUEUE)
        private void methodWithAnnotation(String anotherParam, @Payload SimpleFoo payload) {
        }

    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

}
