package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ComponentsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RabbitChannelsScanner.class, DefaultSchemasService.class})
@TestPropertySource(properties = "amqp.queues.test=test-queue")
public class RabbitChannelsScannerTest {


    @Autowired
    private RabbitChannelsScanner rabbitListenerScanner;

    @MockBean
    private ComponentsScanner componentsScanner;

    @MockBean
    private AsyncApiDocket asyncApiDocket;

    @Value("${amqp.queues.test}")
    private String queueFromProperties;

    private static final String QUEUE = "test-queue";

    @Before
    public void setUp() {
        when(asyncApiDocket.getBasePackage())
                .thenReturn("Does not matter - will be set by component scanner mock");
    }

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentsScanner.scanForComponents(anyString())).thenReturn(classesToScan);
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
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("amqp", new AMQPOperationBinding()))
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder().publish(operation).build();

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
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("amqp", new AMQPOperationBinding()))
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder().publish(operation).build();

        assertThat(actualChannelItems)
                .containsExactly(Maps.immutableEntry(QUEUE, expectedChannelItem));
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
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("amqp", new AMQPOperationBinding()))
                .message(message)
                .build();

        ChannelItem expectedChannelItem = ChannelItem.builder().publish(operation).build();

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