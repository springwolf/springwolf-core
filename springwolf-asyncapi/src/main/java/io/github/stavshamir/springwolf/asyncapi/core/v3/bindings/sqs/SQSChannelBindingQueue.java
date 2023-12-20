// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/sqs/README.md#queue">SQS Queue</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SQSChannelBindingQueue {
    /**
     * Required. The name of the queue. When an SNS Operation Binding Object references an SQS queue by name, the
     * identifier should be the one in this field.
     */
    @NotNull
    @JsonProperty("queue")
    private String name;

    /**
     * Required. Is this a FIFO queue?
     */
    @NotNull
    @JsonProperty("fifoQueue")
    private Boolean fifoQueue;

    /**
     * Optional. Specifies whether message deduplication occurs at the message group or queue level. Valid values
     * are messageGroup and queue. This property applies only to high throughput for FIFO queues.
     */
    @JsonProperty("deduplicationScope")
    private SQSChannelBindingDeduplicationScope deduplicationScope;

    /**
     * Optional. Specifies whether the FIFO queue throughput quota applies to the entire queue or per message group.
     * Valid values are perQueue and perMessageGroupId. The perMessageGroupId value is allowed only when the value for
     * DeduplicationScope is messageGroup. Setting both these values as such will enable high throughput on a FIFO queue.
     * As above, this property applies only to high throughput for FIFO queues.
     */
    @JsonProperty("fifoThroughputLimit")
    private SQSChannelBindingFifoThroughput fifoThroughputLimit;

    /**
     * Optional. The number of seconds to delay before a message sent to the queue can be received. Used to create a
     * delay queue. Range is 0 to 15 minutes. Defaults to 0.
     */
    @Builder.Default
    @Min(value = 0)
    @Max(value = 15)
    @JsonProperty("deliveryDelay")
    private Integer deliveryDelay = 0;

    /**
     * Optional. The length of time, in seconds, that a consumer locks a message - hiding it from reads - before it is
     * unlocked and can be read again. Range from 0 to 12 hours (43200 seconds). Defaults to 30 seconds.
     */
    @Builder.Default
    @Min(value = 0)
    @Max(value = 43200, message = "Maximum is 12 hours (43200 seconds)")
    @JsonProperty("visibilityTimeout")
    private Integer visibilityTimeout = 30;

    /**
     * Optional. Determines if the queue uses short polling or long polling. Set to zero (the default) the queue reads
     * available messages and returns immediately. Set to a non-zero integer, long polling waits the specified number
     * of seconds for messages to arrive before returning.
     */
    @Builder.Default
    @JsonProperty("receiveMessageWaitTime")
    private Integer receiveMessageWaitTime = 0;

    /**
     * Optional. How long to retain a message on the queue in seconds, unless deleted.
     * The range is 60 (1 minute) to 1,209,600 (14 days). The default is 345,600 (4 days).
     */
    @Builder.Default
    @Min(value = 60, message = "Minimum is 1 minute (60 seconds)")
    @Max(value = 1_209_600, message = "Maximum is 1,209,600 (14 days)")
    @JsonProperty("messageRetentionPeriod")
    private Integer messageRetentionPeriod = 345_600;

    /**
     * Optional. Prevent poison pill messages by moving un-processable messages to an SQS dead letter queue.
     */
    @JsonProperty("redrivePolicy")
    private Object redrivePolicy;

    /**
     * Optional. The security policy for the SQS Queue
     */
    @JsonProperty("policy")
    private SQSChannelBindingPolicy policy;

    /**
     * Optional. Key-value pairs that represent AWS tags on the queue.
     */
    @JsonProperty("tags")
    private Map<String, String> tags;
}
