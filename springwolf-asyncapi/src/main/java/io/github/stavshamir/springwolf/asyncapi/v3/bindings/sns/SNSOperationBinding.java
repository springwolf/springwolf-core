package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information operation binding in SNS.
 * </p>
 * We represent SNS producers via a subscribe Operation Object. In simple cases this may not require configuration, and
 * can be shown as an empty SNS Binding Object i.e. {} if you need to explicitly indicate how a producer publishes to
 * the channel.
 * </p>
 * We represent SNS consumers via a publish Operation Object. These consumers need an SNS Subscription that defines how
 * they consume from SNS i.e. the protocol that they use, and any filters applied.
 * </p>
 * The SNS binding does not describe the receiver.If you wish to define the receiver, add a publish Operation Binding
 * Object for that receiver. For example, if you send message to an SQS queue from an SNS Topic, you would add a
 * protocol of 'sqs' and an Identifier object for the queue. That identifier could be an ARN of a queue defined outside
 * of the scope of AsyncAPI, but if you wanted to define the receiver you would use the name of a queue defined in an
 * SQS Binding on the publish Operation Binding Object.
 * </p>
 * We support an array of consumers via the consumers field. This allows you to represent multiple protocols consuming
 * an SNS Topic in one file. You may also use it for multiple consumers with the same protocol, instead of representing
 * each consumer in a separate file.
 *
 * @see <a href="https://github.com/asyncapi/bindings/tree/master/sns#operation-binding-object">SNS Operation binding</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SNSOperationBinding extends OperationBinding {

    /**
     * Optional. Often we can assume that the SNS Topic is the channel name-we provide this field in case the you need
     * to supply the ARN, or the Topic name is not the channel name in the AsyncAPI document.
     * </p>
     * Applies to: Publish, Subscribe
     */
    @JsonProperty("topic")
    private SNSOperationBindingIdentifier topic;

    /**
     * Required. The protocols that listen to this topic and their endpoints.
     * </p>
     * Applies to: Publish
     */
    @NotNull
    @JsonProperty("consumers")
    private List<SNSOperationBindingConsumer> consumers;

    /**
     * Optional. Policy for retries to HTTP. The field is the default for HTTP receivers of the SNS Topic which may be
     * overridden by a specific consumer.
     * </p>
     * Applies to: Subscribe
     */
    @JsonProperty("deliveryPolicy")
    private Object deliveryPolicy;

    /**
     * Optional, defaults to latest. The version of this binding.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.1.0";
}
