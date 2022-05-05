package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.stomp.STOMPOperationBinding;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
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
@ContextConfiguration(classes = {MessageMappingChannelScanner.class, DefaultSchemasService.class})
@TestPropertySource(properties = "stomp.topics.test=test-topic")
public class MessageMappingChannelScannerTest {

    private static final String TOPIC = "test-topic";
    @Autowired
    private MessageMappingChannelScanner messageMappingChannelScanner;
    @MockBean
    private ComponentsScanner componentsScanner;
    @MockBean
    private AsyncApiDocket asyncApiDocket;
    @Value("${stomp.topics.test}")
    private String topicFromProperties;

    @Before
    public void setUp() throws Exception {
        when(asyncApiDocket.getBasePackage())
                .thenReturn("Does not matter - will be set by component scanner mock");
    }

    private void setClassToScan(Class<?> classToScan) {
        Set<Class<?>> classesToScan = singleton(classToScan);
        when(componentsScanner.scanForComponents(anyString())).thenReturn(classesToScan);
    }

    @Test
    public void scan_componentHasNoMessageMappingMethods() {
        setClassToScan(ClassWithoutMessageMappingAnnotation.class);

        Map<String, ChannelItem> channels = messageMappingChannelScanner.scan();

        assertThat(channels)
                .isEmpty();
    }

    @Test
    public void scan_componentHasMessageMappingMethods_hardCodedTopic() {
        // Given a class with methods annotated with KafkaListener, whose topics attribute is hard coded
        setClassToScan(ClassWithMessageMappingAnnotationHardCodedTopic.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = messageMappingChannelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("stomp", new STOMPOperationBinding()))
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder().publish(operation).build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry(TOPIC, expectedChannel));
    }

    @Test
    public void scan_componentHasMessageMappingMethods_embeddedValueTopic() {
        // Given a class with methods annotated with KafkaListener, whose topics attribute is an embedded value
        setClassToScan(ClassWithMessageMappingAnnotationsEmbeddedValueTopic.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = messageMappingChannelScanner.scan();

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("stomp", new STOMPOperationBinding()))
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder().publish(operation).build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry(TOPIC, expectedChannel));
    }

    @Test
    public void scan_componentHasMessageMappingMethods_withSendTo() {
        // Given a class with methods annotated with KafkaListener, with a group id
        setClassToScan(ClassWithMessageMappingAnnotationAndSendTo.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = messageMappingChannelScanner.scan();

        // Then the returned collection contains a correct binding
        Map<String, ? extends OperationBinding> actualBindings = actualChannels.get(TOPIC)
                .getPublish()
                .getBindings();

        assertThat(actualBindings).isNotNull();
        STOMPOperationBinding stomp = (STOMPOperationBinding) actualBindings.get("stomp");
        assertThat(stomp).isNotNull();
//        assertThat(stomp.getGroupId()) Asserting properties AFTER creating STOMPOperationBinding
//                .isEqualTo(ClassWithKafkaListenerAnnotationWithGroupId.GROUP_ID);
    }

    @Test
    public void scan_componentHasMessageMappingMethods_multipleParamsWithoutPayloadAnnotation() {
        // Given a class with a method annotated with KafkaListener:
        // - The method has more than one parameter
        // - No parameter is annotated with @Payload
        setClassToScan(ClassWithMessageMappingAnnotationMultipleParamsWithoutPayloadAnnotation.class);

        // Then an exception is thrown when scan is called
        assertThatThrownBy(() -> messageMappingChannelScanner.scan())
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    public void scan_componentHasMessageMappingMethods_multipleParamsWithPayloadAnnotation() {
        // Given a class with a method annotated with KafkaListener:
        // - The method has more than one parameter
        // - There is a parameter is annotated with @Payload
        setClassToScan(ClassWithMessageMappingAnnotationMultipleParamsWithPayloadAnnotation.class);

        // When scan is called
        Map<String, ChannelItem> actualChannels = messageMappingChannelScanner.scan();

        // Then the returned collection contains the channel, and the payload is of the parameter annotated with @Payload
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();

        Operation operation = Operation.builder()
                .bindings(ImmutableMap.of("stomp", new STOMPOperationBinding()))
                .message(message)
                .build();

        ChannelItem expectedChannel = ChannelItem.builder().publish(operation).build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry(TOPIC, expectedChannel));
    }

    private static class ClassWithoutMessageMappingAnnotation {

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithMessageMappingAnnotationHardCodedTopic {

        @MessageMapping(value = {TOPIC})
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithMessageMappingAnnotationsEmbeddedValueTopic {

        @MessageMapping(value = "${stomp.topics.test}")
        private void methodWithAnnotation1(SimpleFoo payload) {
        }

    }

    private static class ClassWithMessageMappingAnnotationAndSendTo {

        private static final String GROUP_ID = "test-group-id";

        @MessageMapping(value = {TOPIC})
        @SendTo("/group")
        private void methodWithAnnotation(SimpleFoo payload) {
        }

        private void methodWithoutAnnotation() {
        }

    }

    private static class ClassWithMessageMappingAnnotationMultipleParamsWithoutPayloadAnnotation {

        @MessageMapping(value = {TOPIC})
        private void methodWithAnnotation(SimpleFoo payload, String anotherParam) {
        }

    }

    private static class ClassWithMessageMappingAnnotationMultipleParamsWithPayloadAnnotation {

        @MessageMapping(value = {TOPIC})
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