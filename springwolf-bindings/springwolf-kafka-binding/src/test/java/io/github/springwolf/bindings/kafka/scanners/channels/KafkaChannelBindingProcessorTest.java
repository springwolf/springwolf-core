// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.kafka.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelTopicCleanupPolicy;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelTopicConfiguration;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncChannelBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ProcessedChannelBinding;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KafkaChannelBindingProcessorTest {
    private final StringValueResolver stringValueResolver = mock();
    private final KafkaChannelBindingProcessor processor = new KafkaChannelBindingProcessor(stringValueResolver);

    @Test
    void processTest() throws Exception {
        Method method = KafkaChannelBindingProcessorTest.class.getMethod("methodWithAnnotation");

        ProcessedChannelBinding binding = processor.process(method).get();

        assertThat(binding.type()).isEqualTo("kafka");
        assertThat(binding.binding()).isEqualTo(new KafkaChannelBinding());
    }

    @Test
    void processWithoutAnnotationTest() throws Exception {
        Method method = KafkaChannelBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        Optional<ProcessedChannelBinding> binding = processor.process(method);

        assertThat(binding).isNotPresent();
    }

    @Test
    void processTestWithFullConfiguration() throws Exception {
        when(stringValueResolver.resolveStringValue("test-topic")).thenReturn("resolved-test-topic");

        Method method = KafkaChannelBindingProcessorTest.class.getMethod("methodWithFullConfiguration");

        ProcessedChannelBinding binding = processor.process(method).get();

        assertThat(binding.type()).isEqualTo("kafka");
        assertThat(binding.binding())
                .isEqualTo(KafkaChannelBinding.builder()
                        .topic("resolved-test-topic")
                        .partitions(3)
                        .replicas(2)
                        .topicConfiguration(KafkaChannelTopicConfiguration.builder()
                                .cleanupPolicy(List.of(
                                        KafkaChannelTopicCleanupPolicy.COMPACT, KafkaChannelTopicCleanupPolicy.DELETE))
                                .retentionMs(86400000L)
                                .retentionBytes(-1L)
                                .deleteRetentionMs(86400000L)
                                .maxMessageBytes(1048588)
                                .build())
                        .build());
    }

    @KafkaAsyncChannelBinding
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}

    @KafkaAsyncChannelBinding(
            topic = "test-topic",
            partitions = 3,
            replicas = 2,
            topicConfiguration =
                    @KafkaAsyncChannelBinding.KafkaChannelTopicConfiguration(
                            cleanup = {
                                KafkaAsyncChannelBinding.KafkaChannelTopicConfiguration.CleanupPolicy.COMPACT,
                                KafkaAsyncChannelBinding.KafkaChannelTopicConfiguration.CleanupPolicy.DELETE
                            },
                            retentionMs = 86400000L,
                            retentionBytes = -1L,
                            deleteRetentionMs = 86400000L,
                            maxMessageBytes = 1048588))
    public void methodWithFullConfiguration() {}
}
