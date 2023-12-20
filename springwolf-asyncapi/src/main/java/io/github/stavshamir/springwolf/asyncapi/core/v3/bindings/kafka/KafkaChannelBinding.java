// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.ChannelBinding;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Protocol-specific information for a Kafka channel.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/kafka/README.md#channel-binding-object">Kafka Channel Binding</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KafkaChannelBinding extends ChannelBinding {

    /**
     * Kafka topic name if different from channel name.
     */
    @JsonProperty("topic")
    private String topic;

    /**
     * Number of partitions configured on this topic (useful to know how many parallel consumers you may run).
     */
    @Positive
    @JsonProperty("partitions")
    private Integer partitions;

    /**
     * Number of replicas configured on this topic.
     */
    @Positive
    @JsonProperty("replicas")
    private Integer replicas;

    /**
     * Topic configuration properties that are relevant for the API.
     */
    @JsonProperty("topicConfiguration")
    private KafkaChannelTopicConfiguration topicConfiguration;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.4.0";
}
