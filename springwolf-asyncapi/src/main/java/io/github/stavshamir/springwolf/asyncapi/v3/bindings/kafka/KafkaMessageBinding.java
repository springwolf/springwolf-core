// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This object contains information about the message representation in Kafka.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KafkaMessageBinding extends MessageBinding {
    /**
     * The message key. NOTE: You can also use the reference object way.
     */
    @JsonProperty(value = "key")
    private Schema key;

    /**
     * If a Schema Registry is used when performing this operation, tells where the id of schema is stored (e.g. header or payload).
     */
    @JsonProperty(value = "schemaIdLocation")
    private String schemaIdLocation;

    /**
     * Number of bytes or vendor specific values when schema id is encoded in payload (e.g confluent/ apicurio-legacy / apicurio-new).
     */
    @JsonProperty(value = "schemaIdPayloadEncoding")
    private String schemaIdPayloadEncoding;

    /**
     * Freeform string for any naming strategy class to use. Clients should default to the vendor default if not supplied.
     */
    @JsonProperty(value = "schemaLookupStrategy")
    private String schemaLookupStrategy;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.4.0";
}
