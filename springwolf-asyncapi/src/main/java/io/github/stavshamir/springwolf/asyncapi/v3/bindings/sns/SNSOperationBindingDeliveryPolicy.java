// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @see <a href="https://github.com/asyncapi/bindings/tree/master/sns#delivery-policy">Delivery Policy</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SNSOperationBindingDeliveryPolicy {
    /**
     * Optional. The minimum delay for a retry in seconds
     */
    @JsonProperty("minDelayTarget")
    private Integer minDelayTarget;

    /**
     * Optional. The maximum delay for a retry in seconds
     */
    @JsonProperty("maxDelayTarget")
    private Integer maxDelayTarget;

    /**
     * Optional. The total number of retries, including immediate, pre-backoff, backoff, and post-backoff retries
     */
    @JsonProperty("numRetries")
    private Integer numRetries;

    /**
     * Optional. The number of immediate retries (with no delay)
     */
    @JsonProperty("numNoDelayRetries")
    private Integer numNoDelayRetries;

    /**
     * Optional. The number of immediate retries (with delay)
     */
    @JsonProperty("numMinDelayRetries")
    private Integer numMinDelayRetries;

    /**
     * Optional. The number of post-backoff phase retries, with the maximum delay between retries
     */
    @JsonProperty("numMaxDelayRetries")
    private Integer numMaxDelayRetries;

    /**
     * Optional. The algorithm for backoff between retries
     */
    @JsonProperty("backoffFunction")
    private BackoffFunction backoffFunction;

    /**
     * Optional. The maximum number of deliveries per second, per subscription
     */
    @JsonProperty("maxReceivesPerSecond")
    private Integer maxReceivesPerSecond;

    public enum BackoffFunction {
        ARITHMETIC("arithmetic"),
        EXPONENTIAL("exponential"),
        GEOMETRIC("geometric"),
        LINEAR("linear");

        public final String type;

        BackoffFunction(String type) {
            this.type = type;
        }

        public static BackoffFunction fromString(String type) {
            return valueOf(type.toUpperCase());
        }

        @Override
        public String toString() {
            return this.type;
        }
    }
}
