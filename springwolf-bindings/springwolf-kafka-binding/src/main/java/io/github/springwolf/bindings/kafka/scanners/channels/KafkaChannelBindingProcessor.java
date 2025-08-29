// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.kafka.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelTopicCleanupPolicy;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelTopicConfiguration;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncChannelBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.AbstractChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ProcessedChannelBinding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StringValueResolver;

import java.util.Arrays;

public class KafkaChannelBindingProcessor extends AbstractChannelBindingProcessor<KafkaAsyncChannelBinding> {

    public KafkaChannelBindingProcessor(StringValueResolver stringValueResolver) {
        super(stringValueResolver);
    }

    protected ProcessedChannelBinding mapToChannelBinding(KafkaAsyncChannelBinding bindingAnnotation) {
        KafkaChannelBinding.KafkaChannelBindingBuilder bindingBuilder = KafkaChannelBinding.builder();
        if (StringUtils.isNotBlank(bindingAnnotation.topic())) {
            bindingBuilder.topic(resolveOrNull(bindingAnnotation.topic()));
        }
        if (bindingAnnotation.partitions() != KafkaAsyncChannelBinding.VALUE_NOT_SET) {
            bindingBuilder.partitions(bindingAnnotation.partitions());
        }
        if (bindingAnnotation.replicas() != KafkaAsyncChannelBinding.VALUE_NOT_SET) {
            bindingBuilder.replicas(bindingAnnotation.replicas());
        }
        bindingBuilder.topicConfiguration(mapToTopicConfiguration(bindingAnnotation));

        return new ProcessedChannelBinding("kafka", bindingBuilder.build());
    }

    private KafkaChannelTopicConfiguration mapToTopicConfiguration(KafkaAsyncChannelBinding bindingAnnotation) {
        KafkaChannelTopicConfiguration.KafkaChannelTopicConfigurationBuilder topicConfiguration =
                KafkaChannelTopicConfiguration.builder();

        if (bindingAnnotation.topicConfiguration().cleanup().length > 0) {
            topicConfiguration.cleanupPolicy(
                    Arrays.stream(bindingAnnotation.topicConfiguration().cleanup())
                            .map(this::toKafkaChannelTopicCleanupPolicy)
                            .toList());
        }

        if (bindingAnnotation.topicConfiguration().retentionMs() != KafkaAsyncChannelBinding.VALUE_NOT_SET) {
            topicConfiguration.retentionMs(
                    bindingAnnotation.topicConfiguration().retentionMs());
        }
        if (bindingAnnotation.topicConfiguration().retentionBytes() != KafkaAsyncChannelBinding.VALUE_NOT_SET) {
            topicConfiguration.retentionBytes(
                    bindingAnnotation.topicConfiguration().retentionBytes());
        }
        if (bindingAnnotation.topicConfiguration().deleteRetentionMs() != KafkaAsyncChannelBinding.VALUE_NOT_SET) {
            topicConfiguration.deleteRetentionMs(
                    bindingAnnotation.topicConfiguration().deleteRetentionMs());
        }
        if (bindingAnnotation.topicConfiguration().maxMessageBytes() != KafkaAsyncChannelBinding.VALUE_NOT_SET) {
            topicConfiguration.maxMessageBytes(
                    bindingAnnotation.topicConfiguration().maxMessageBytes());
        }

        KafkaChannelTopicConfiguration buildTopicConfiguration = topicConfiguration.build();
        if (EMPTY_TOPIC_CONFIGURATION.equals(buildTopicConfiguration)) {
            return null;
        }
        return buildTopicConfiguration;
    }

    private KafkaChannelTopicCleanupPolicy toKafkaChannelTopicCleanupPolicy(
            KafkaAsyncChannelBinding.KafkaChannelTopicConfiguration.CleanupPolicy cleanupType) {
        return switch (cleanupType) {
            case COMPACT -> KafkaChannelTopicCleanupPolicy.COMPACT;
            case DELETE -> KafkaChannelTopicCleanupPolicy.DELETE;
        };
    }
}
