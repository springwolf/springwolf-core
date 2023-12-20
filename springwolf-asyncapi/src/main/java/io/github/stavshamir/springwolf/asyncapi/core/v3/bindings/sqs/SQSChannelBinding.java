// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.ChannelBinding;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Use the Channel Binding Operation for Point-to-Point SQS channels.
 * </p>
 * There are three likely scenarios for use of the Channel Binding Object:
 * </p>
 * <ul>
 * <li>One file defines both publish and subscribe operations, for example if we were implementing the work queue pattern
 * to offload work from an HTTP API endpoint to a worker process. In this case the channel would be defined on the
 * Channel Object in that single file.</li>
 * <li>The producer and consumer both have an AsyncAPI specification file, and the producer is raising an event, for
 * example interop between microservices, and the producer 'owns' the channel definition and thus has the SQS Binding
 * on its Channel Object.</li>
 * <li>The producer and consumer both have an AsyncAPI specification file, and the consumer receives commands, for example
 * interop between microservices, and the consumer 'owns' the channel definition and thus has the SQS Binding on its
 * Channel Object.</li>
 * </ul>
 * An SQS queue can set up a Dead Letter Queue as part of a Redelivery Policy. To support this requirement, the
 * Channel Binding Object allows you to define both a Queue Object to use as the Channel or target in a publish
 * Operation and a Dead Letter Queue. You can then refer to the Dead letter Queue in the Redrive Policy using the
 * Identifier Object and setting the name field to match the name field of your Dead Letter Queue Object.
 * (If you define the DLQ externally, the Identifier also supports an ARN).
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/sqs/README.md#channel">SQS Channel</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SQSChannelBinding extends ChannelBinding {
    /**
     * Required. A definition of the queue that will be used as the channel.
     */
    @NotNull
    @JsonProperty("queue")
    private SQSChannelBindingQueue queue;

    /**
     * Optional. A definition of the queue that will be used for un-processable messages.
     */
    @JsonProperty("deadLetterQueue")
    private SQSChannelBindingQueue deadLetterQueue;

    /**
     * Optional, defaults to latest. The version of this binding.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.2.0";
}
