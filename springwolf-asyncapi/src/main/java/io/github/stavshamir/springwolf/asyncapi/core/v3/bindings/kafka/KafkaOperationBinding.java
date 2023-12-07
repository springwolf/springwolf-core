// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.schema.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Protocol-specific information for a Kafka operation.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/kafka/README.md#operation">Kafka Operation</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KafkaOperationBinding extends OperationBinding {

    /**
     * Id of the consumer group.
     */
    @JsonProperty("groupId")
    private Schema groupId;

    /**
     * Id of the consumer inside a consumer group.
     */
    @JsonProperty("clientId")
    private Schema clientId;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.4.0";
}
