package com.stavshamir.springaroo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { KafkaListenersScanner.class })
@TestPropertySource(properties = "kafka.topics.test=test-topic")
public class KafkaListenersScannerTest {

    @Autowired
    private KafkaListenersScanner kafkaListenersScanner;

    @Value("${kafka.topics.test}")
    private String topicFromProperties;

    private final static String TOPIC = "test-topic";

    @Test
    public void getKafkaEndpoints_noAnnotatedMethods() {
        // Given a class without methods annotated with KafkaListener
        // When getKafkaEndpoints is called
        Set<KafkaEndpoint> consumersDetails = kafkaListenersScanner.getKafkaEndpoints(ClassWithoutKafkaListenerAnnotations.class);

        // Then the returned collection is empty
        assertThat(consumersDetails).isEmpty();
    }

    @Test
    public void getKafkaEndpoints_hasAnnotatedMethods_hardCodedTopic() {
        // Given a class with methods annotated with KafkaListener, whose topics attribute is hard coded
        // When getKafkaEndpoints is called
        Set<KafkaEndpoint> consumersDetails = kafkaListenersScanner.getKafkaEndpoints(ClassWithKafkaListenerAnnotationsHardCodedTopics.class);

        // Then the returned collection contains the methods' details
        assertThat(consumersDetails).containsExactlyInAnyOrder(
                new KafkaEndpoint("methodWithAnnotation1", new String[]{TOPIC}, String.class),
                new KafkaEndpoint("methodWithAnnotation2", new String[]{TOPIC}, String.class)
        );
    }

    @Test
    public void getKafkaEndpoints_hasAnnotatedMethods_embeddedValueTopic() {
        assertThat(topicFromProperties).isEqualTo(TOPIC);

        // Given a class with methods annotated with KafkaListener, whose topics attribute is an embedded value
        // When getKafkaEndpoints is called
        Set<KafkaEndpoint> consumersDetails = kafkaListenersScanner.getKafkaEndpoints(ClassWithKafkaListenerAnnotationsEmbeddedValueTopic.class);

        // Then the returned collection contains the methods' details
        assertThat(consumersDetails).containsExactlyInAnyOrder(
                new KafkaEndpoint("methodWithAnnotation1", new String[]{TOPIC}, String.class),
                new KafkaEndpoint("methodWithAnnotation2", new String[]{TOPIC}, String.class)
        );
    }

    private static class ClassWithoutKafkaListenerAnnotations {

        @Deprecated
        private void methodWithoutAnnotation1() {}

        private void methodWithoutAnnotation2() {}

    }

    private static class ClassWithKafkaListenerAnnotationsHardCodedTopics {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation1(String payload) {}

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation2(String payload) {}

        private void methodWithoutAnnotation1() {}

        private void methodWithoutAnnotation2() {}

    }

    private static class ClassWithKafkaListenerAnnotationsEmbeddedValueTopic {

        @KafkaListener(topics = "${kafka.topics.test}")
        private void methodWithAnnotation1(String payload) {}

        @KafkaListener(topics = "${missing-property:" + TOPIC + "}")
        private void methodWithAnnotation2(String payload) {}

    }

}