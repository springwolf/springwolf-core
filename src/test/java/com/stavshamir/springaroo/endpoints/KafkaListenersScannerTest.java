package com.stavshamir.springaroo.endpoints;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Config.class })
@TestPropertySource(properties = "kafka.topics.test=test-topic")
public class KafkaListenersScannerTest {

    @Autowired
    private KafkaListenersScanner kafkaListenersScanner;

    @Value("${kafka.topics.test}")
    private String topicFromProperties;

    private final static String TOPIC = "test-topic";

    private static final String EXAMPLES_PATH = "/models/examples";
    private String simpleFooExample = jsonResourceAsWhitespaceStrippedString(EXAMPLES_PATH + "/simple-foo.json");

    @Test
    public void getKafkaEndpoints_noAnnotatedMethods() {
        // Given a class without methods annotated with KafkaListener
        // When getKafkaEndpointsFromClass is called
        Set<KafkaEndpoint> consumersDetails = kafkaListenersScanner.getKafkaEndpointsFromClass(ClassWithoutKafkaListenerAnnotations.class);

        // Then the returned collection is empty
        assertThat(consumersDetails).isEmpty();
    }

    @Test
    public void getKafkaEndpoints_hasAnnotatedMethods_hardCodedTopic() {
        // Given a class with methods annotated with KafkaListener, whose topics attribute is hard coded
        // When getKafkaEndpointsFromClass is called
        Set<KafkaEndpoint> consumersDetails = kafkaListenersScanner.getKafkaEndpointsFromClass(ClassWithKafkaListenerAnnotationsHardCodedTopics.class);

        // Then the returned collection contains the methods' details
        assertThat(consumersDetails).containsExactlyInAnyOrder(
                new KafkaEndpoint("methodWithAnnotation1", TOPIC, "SimpleFoo", simpleFooExample),
                new KafkaEndpoint("methodWithAnnotation2", TOPIC, "SimpleFoo", simpleFooExample)
        );
    }

    @Test
    public void getKafkaEndpoints_hasAnnotatedMethods_embeddedValueTopic() {
        assertThat(topicFromProperties).isEqualTo(TOPIC);

        // Given a class with methods annotated with KafkaListener, whose topics attribute is an embedded value
        // When getKafkaEndpointsFromClass is called
        Set<KafkaEndpoint> consumersDetails = kafkaListenersScanner.getKafkaEndpointsFromClass(ClassWithKafkaListenerAnnotationsEmbeddedValueTopic.class);

        // Then the returned collection contains the methods' details
        assertThat(consumersDetails).containsExactlyInAnyOrder(
                new KafkaEndpoint("methodWithAnnotation1", TOPIC, "SimpleFoo", simpleFooExample),
                new KafkaEndpoint("methodWithAnnotation2", TOPIC, "SimpleFoo", simpleFooExample)
        );
    }

    @Test
    public void getKafkaEndpoints_hasAnnotatedMethods_multipleTopic() {
        // Given a class with methods annotated with KafkaListener, whose topics contain multiple topics
        // When getKafkaEndpointsFromClass is called
        Set<KafkaEndpoint> consumersDetails = kafkaListenersScanner.getKafkaEndpointsFromClass(ClassWithKafkaListenerAnnotationsMultipleTopics.class);

        // Then the returned collection contains the methods' details
        assertThat(consumersDetails).containsExactlyInAnyOrder(
                new KafkaEndpoint("methodWithAnnotation1", TOPIC + "1", "SimpleFoo", simpleFooExample),
                new KafkaEndpoint("methodWithAnnotation1", TOPIC + "2", "SimpleFoo", simpleFooExample)
        );
    }
    private static class ClassWithoutKafkaListenerAnnotations {

        @Deprecated
        private void methodWithoutAnnotation1() {}

        private void methodWithoutAnnotation2() {}

    }

    private static class ClassWithKafkaListenerAnnotationsHardCodedTopics {

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation1(SimpleFoo payload) {}

        @KafkaListener(topics = TOPIC)
        private void methodWithAnnotation2(SimpleFoo payload) {}

        private void methodWithoutAnnotation1() {}

        private void methodWithoutAnnotation2() {}

    }

    private static class ClassWithKafkaListenerAnnotationsEmbeddedValueTopic {

        @KafkaListener(topics = "${kafka.topics.test}")
        private void methodWithAnnotation1(SimpleFoo payload) {}

        @KafkaListener(topics = "${missing-property:" + TOPIC + "}")
        private void methodWithAnnotation2(SimpleFoo payload) {}

    }

    private static class ClassWithKafkaListenerAnnotationsMultipleTopics {

        @KafkaListener(topics = { TOPIC + "1", TOPIC + "2" })
        private void methodWithAnnotation1(SimpleFoo payload) {}

    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

    private String jsonResourceAsWhitespaceStrippedString(String path) {
        InputStream s = this.getClass().getResourceAsStream(path);
        try {
            return IOUtils.toString(s, "UTF-8").replaceAll("\\s+", "");
        } catch (IOException e) {
            fail("Failed to read resource stream");
            return null;
        }
    }

}