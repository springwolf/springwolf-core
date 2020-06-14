package io.github.stavshamir.swagger4kafka.scanners.channels;

import com.google.common.collect.Maps;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.Channel;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.Operation;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.swagger4kafka.schemas.DefaultSchemasService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {KafkaChannelsScanner.class, DefaultSchemasService.class})
@TestPropertySource(properties = "kafka.topics.test=test-topic")
public class KafkaChannelsScannerTest {

    @Autowired
    private KafkaChannelsScanner kafkaChannelsScanner;

    @Value("${kafka.topics.test}")
    private String topicFromProperties;

    private static final String TOPIC = "test-topic";

    @Test
    public void scanForChannels_componentHasNoKafkaListenerMethods() {
        Set<Class<?>> classesToScan = singleton(ClassWithoutKafkaListenerAnnotations.class);
        Map<String, Channel> channels = kafkaChannelsScanner.scanForChannels(classesToScan);

        assertThat(channels)
                .isEmpty();
    }

    @Test
    public void scanForChannels_componentHasKafkaListenerMethods_hardCodedTopic() {
        // Given a class with methods annotated with KafkaListener, whose topics attribute is hard coded
        // When scanForChannels is called
        Set<Class<?>> classesToScan = singleton(ClassWithKafkaListenerAnnotationHardCodedTopic.class);
        Map<String, Channel> actualChannels = kafkaChannelsScanner.scanForChannels(classesToScan);

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();
        Operation operation = Operation.builder().message(message).build();
        Channel expectedChannel = Channel.builder().subscribe(operation).build();

        assertThat(actualChannels)
                .containsExactly(Maps.immutableEntry(TOPIC, expectedChannel));
    }

    @Test
    public void scanForChannels_componentHasKafkaListenerMethods_multipleHardCodedTopics() {
        // Given a class with methods annotated with KafkaListener with multiple hard coded topics
        // When scanForChannels is called
        Set<Class<?>> classesToScan = singleton(ClassWithKafkaListenerAnnotationsMultipleTopics.class);
        Map<String, Channel> actualChannels = kafkaChannelsScanner.scanForChannels(classesToScan);

        // Then the returned collection contains the channels
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();
        Operation operation = Operation.builder().message(message).build();
        Channel expectedChannel = Channel.builder().subscribe(operation).build();

        assertThat(actualChannels)
                .contains(Maps.immutableEntry(TOPIC + "1", expectedChannel))
                .contains(Maps.immutableEntry(TOPIC + "2", expectedChannel));
    }

    @Test
    public void scanForChannels_componentHasKafkaListenerMethods_embeddedValueTopic() {
        // Given a class with methods annotated with KafkaListener, whose topics attribute is an embedded value
        // When scanForChannels is called
        Set<Class<?>> classesToScan = singleton(ClassWithKafkaListenerAnnotationsEmbeddedValueTopic.class);
        Map<String, Channel> actualChannels = kafkaChannelsScanner.scanForChannels(classesToScan);

        // Then the returned collection contains the channel
        Message message = Message.builder()
                .name(SimpleFoo.class.getName())
                .title(SimpleFoo.class.getSimpleName())
                .payload(PayloadReference.fromModelName(SimpleFoo.class.getSimpleName()))
                .build();
        Operation operation = Operation.builder().message(message).build();
        Channel expectedChannel = Channel.builder().subscribe(operation).build();

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

    private static class ClassWithKafkaListenerAnnotationsMultipleTopics {

        @KafkaListener(topics = {TOPIC + "1", TOPIC + "2"})
        private void methodWithAnnotation1(SimpleFoo payload) {
        }

    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

}