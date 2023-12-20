// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.ServerBinding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object contains information about the server representation in Kafka.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KafkaServerBinding extends ServerBinding {
    /**
     * API URL for the Schema Registry used when producing Kafka messages (if a Schema Registry was used)
     */
    @JsonProperty("schemaRegistryUrl")
    private String schemaRegistryUrl;

    /**
     * MUST NOT be specified if schemaRegistryUrl is not specified
     * </p>
     * The vendor of Schema Registry and Kafka serdes library that should be used (e.g. apicurio, confluent, ibm, or karapace)
     */
    @JsonProperty("schemaRegistryVendor")
    private String schemaRegistryVendor;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.4.0";
}
