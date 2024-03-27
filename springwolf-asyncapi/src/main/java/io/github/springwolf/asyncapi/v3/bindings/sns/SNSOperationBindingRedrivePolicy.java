// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @see <a href="https://github.com/asyncapi/bindings/tree/master/sns#redrive-policy">Redrive Policy</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SNSOperationBindingRedrivePolicy {

    /**
     * Required. The SQS queue to use as a dead letter queue (DLQ). Note that you may have a Redrive Policy to put
     * messages that cannot be delivered to an SQS queue, even if you use another protocol to consume messages from the
     * queue, so it is defined at the level of the SNS Operation Binding Object in a Consumer Object (and is applied
     * as part of an SNS Subscription). The SQS Binding describes how to define an SQS Binding that supports defining
     * the target SQS of the Redrive Policy.
     */
    @NotNull
    @JsonProperty("deadLetterQueue")
    private SNSOperationBindingIdentifier deadLetterQueue;

    /**
     * Optional. The number of times a message is delivered to the source queue before being moved to the dead-letter
     * queue. Defaults to 10.
     */
    @Builder.Default
    @JsonProperty("maxReceiveCount")
    private Integer maxReceiveCount = 10;
}
