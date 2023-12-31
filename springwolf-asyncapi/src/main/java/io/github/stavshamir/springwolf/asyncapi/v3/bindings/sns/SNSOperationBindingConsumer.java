package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Operation Consumer
 *
 * @see <a href="https://github.com/asyncapi/bindings/tree/master/sns#consumer">SNS Operation Consumer</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SNSOperationBindingConsumer {
    /**
     * Required. The protocol that this endpoint receives messages by.
     * Can be http, https, email, email-json, sms, sqs, application, lambda or firehose
     */
    @JsonProperty("protocol")
    private Protocol protocol;

    /**
     * Required. The endpoint messages are delivered to.
     */
    @NotNull
    @JsonProperty("endpoint")
    private SNSOperationBindingIdentifier endpoint;

    /**
     * Optional. Only receive a subset of messages from the channel, determined by this policy.
     */
    @JsonProperty("filterPolicy")
    private Object filterPolicy;

    /**
     * Optional. Determines whether the FilterPolicy applies to MessageAttributes (default) or MessageBody.
     */
    @Builder.Default
    @JsonProperty("filterPolicyScope")
    private FilterPolicyScope filterPolicyScope = FilterPolicyScope.MESSAGE_ATTRIBUTES;

    /**
     * Required. If true AWS SNS attributes are removed from the body, and for SQS, SNS message attributes are copied
     * to SQS message attributes. If false the SNS attributes are included in the body.
     */
    @NotNull
    @JsonProperty("rawMessageDelivery")
    private Boolean rawMessageDelivery;

    /**
     * Optional. Prevent poison pill messages by moving un-processable messages to an SQS dead letter queue.
     */
    @JsonProperty("redrivePolicy")
    private SNSOperationBindingRedrivePolicy redrivePolicy;

    /**
     * Optional. Policy for retries to HTTP. The parameter is for that SNS Subscription and overrides any policy on
     * the SNS Topic.
     */
    @JsonProperty("deliveryPolicy")
    private SNSOperationBindingDeliveryPolicy deliveryPolicy;

    /**
     * Optional. The display name to use with an SMS subscription
     */
    @JsonProperty("displayName")
    private String displayName;

    public enum Protocol {
        HTTP("http"),
        HTTPS("https"),
        EMAIL("email"),
        EMAIL_JSON("email-json"),
        SMS("sms"),
        SQS("sqs"),
        APPLICATION("application"),
        LAMBDA("lambda"),
        FIREHOSE("firehose");

        public final String type;

        Protocol(String type) {
            this.type = type;
        }

        public static Protocol fromString(String type) {
            return valueOf(type.toUpperCase());
        }

        @Override
        public String toString() {
            return this.type;
        }
    }

    public enum FilterPolicyScope {
        MESSAGE_ATTRIBUTES("MessageAttributes"),
        MESSAGE_BODY("MessageBody");

        public final String type;

        FilterPolicyScope(String type) {
            this.type = type;
        }

        public static FilterPolicyScope fromString(String type) {
            return valueOf(type.toUpperCase());
        }

        @Override
        public String toString() {
            return this.type;
        }
    }
}
