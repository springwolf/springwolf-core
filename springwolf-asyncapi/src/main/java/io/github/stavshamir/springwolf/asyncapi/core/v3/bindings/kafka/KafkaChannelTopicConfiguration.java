// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This objects contains information about the API relevant topic configuration in Kafka.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/kafka/README.md#topicConfiguration">Kafka Topic Configuration</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaChannelTopicConfiguration {
    /**
     * The <a href="https://kafka.apache.org/documentation/#topicconfigs_cleanup.policy">cleanup.policy</a>
     * configuration option.
     */
    @JsonProperty("cleanup.policy")
    private List<KafkaChannelTopicCleanupPolicy> cleanupPolicy;

    /**
     * The <a href="https://kafka.apache.org/documentation/#topicconfigs_retention.ms">retention.ms</a>
     * configuration option.
     */
    @Min(value = -1, message = "retention.ms must be greater or equals to -1")
    @JsonProperty("retention.ms")
    private Integer retentionMs;

    /**
     * The <a href="https://kafka.apache.org/documentation/#topicconfigs_retention.bytes">retention.bytes</a>
     * configuration option.
     */
    @Min(value = -1, message = "retention.bytes must be greater or equals to -1")
    @JsonProperty("retention.bytes")
    private Integer retentionBytes;

    /**
     * The <a href="https://kafka.apache.org/documentation/#topicconfigs_delete.retention.ms">delete.retention.ms</a>
     * configuration option.
     */
    @PositiveOrZero
    @JsonProperty("delete.retention.ms")
    private Integer deleteRetentionMs;

    /**
     * The <a href="https://kafka.apache.org/documentation/#topicconfigs_max.message.bytes">max.message.bytes</a>
     * configuration option.
     */
    @PositiveOrZero
    @JsonProperty("max.message.bytes")
    private Integer maxMessageBytes;
}
