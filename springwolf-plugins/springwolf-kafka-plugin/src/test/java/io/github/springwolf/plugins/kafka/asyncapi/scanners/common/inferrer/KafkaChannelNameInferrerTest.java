// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.common.inferrer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class KafkaChannelNameInferrerTest {

    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    private final KafkaChannelNameInferrer inferrer = new KafkaChannelNameInferrer(stringValueResolver);

    @BeforeEach
    void setUp() {
        doAnswer(invocation -> {
                    String arg = (String) invocation.getArguments()[0];
                    return switch (arg) {
                        case "${topic-1}" -> "topic-1";
                        case "${topic-2}" -> "topic-2";
                        case "${topic-pattern}" -> "topic-pattern";
                        default -> arg;
                    };
                })
                .when(stringValueResolver)
                .resolveStringValue(any());
    }

    @Test
    void returnsEmptyWhenNoKafkaListenerAnnotation() throws Exception {
        // given
        Method method = NoAnnotationClass.class.getDeclaredMethod("method", String.class);

        // when
        Optional<String> result = inferrer.inferChannelName(method);

        // then
        assertThat(result).isEmpty();
    }

    @Nested
    class TopicsAttribute {
        @Test
        void infersChannelNameFromTopics() throws Exception {
            // given
            Method method = WithTopicsClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("topic-1");
        }

        @Test
        void infersChannelNameFromTopicsWithPlaceholder() throws Exception {
            // given
            Method method = WithTopicsPlaceholderClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("topic-1");
        }

        @Test
        void infersChannelNameFromMultipleTopics() throws Exception {
            // given
            Method method = WithMultipleTopicsClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("topic-1");
        }
    }

    @Nested
    class TopicPatternAttribute {
        @Test
        void infersChannelNameFromTopicPattern() throws Exception {
            // given
            Method method = WithTopicPatternClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("topic-pattern");
        }

        @Test
        void infersChannelNameFromTopicPatternWithPlaceholder() throws Exception {
            // given
            Method method = WithTopicPatternPlaceholderClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("topic-pattern");
        }
    }

    @Nested
    class KafkaHandlerOnMethod {
        @Test
        void infersChannelNameFromClassLevelKafkaListenerWhenKafkaHandlerOnMethod() throws Exception {
            // given
            Method method = WithKafkaHandlerClass.class.getDeclaredMethod("handle", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("topic-1");
        }

        @Test
        void returnsEmptyWhenKafkaHandlerButNoClassLevelKafkaListener() throws Exception {
            // given
            Method method = WithKafkaHandlerNoClassAnnotationClass.class.getDeclaredMethod("handle", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).isEmpty();
        }
    }

    @Nested
    class EmptyAnnotation {
        @Test
        void returnsEmptyStringWhenTopicsArrayIsEmpty() throws Exception {
            // given
            Method method = WithEmptyTopicsClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).isEmpty();
        }
    }

    // ---- helper classes ----

    private static class NoAnnotationClass {
        private void method(String payload) {}
    }

    private static class WithTopicsClass {
        @KafkaListener(topics = "topic-1")
        private void listen(String payload) {}
    }

    private static class WithTopicsPlaceholderClass {
        @KafkaListener(topics = "${topic-1}")
        private void listen(String payload) {}
    }

    private static class WithMultipleTopicsClass {
        @KafkaListener(topics = {"topic-1", "topic-2"})
        private void listen(String payload) {}
    }

    private static class WithTopicPatternClass {
        @KafkaListener(topicPattern = "topic-pattern")
        private void listen(String payload) {}
    }

    private static class WithTopicPatternPlaceholderClass {
        @KafkaListener(topicPattern = "${topic-pattern}")
        private void listen(String payload) {}
    }

    @KafkaListener(topics = "${topic-1}")
    private static class WithKafkaHandlerClass {
        @KafkaHandler
        private void handle(String payload) {}
    }

    private static class WithKafkaHandlerNoClassAnnotationClass {
        @KafkaHandler
        private void handle(String payload) {}
    }

    private static class WithEmptyTopicsClass {
        @KafkaListener(topics = {})
        private void listen(String payload) {}
    }
}
