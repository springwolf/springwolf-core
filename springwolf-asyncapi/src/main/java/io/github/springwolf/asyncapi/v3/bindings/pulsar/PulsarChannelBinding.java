// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.pulsar;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object contains information about the channel representation in Pulsar.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/pulsar/README.md#channel-binding-object">Pulsar Channel</a>
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PulsarChannelBinding extends ChannelBinding {

    /**
     * Required. The namespace the channel is associated with.
     */
    @NotNull
    @JsonProperty("namespace")
    private String namespace;

    /**
     * Required. Persistence of the topic in Pulsar. It MUST be either persistent or non-persistent.
     */
    @NotNull
    @JsonProperty("persistence")
    private PulsarPesistenceType persistence;

    /**
     * Topic compaction threshold given in Megabytes.
     */
    @JsonProperty("compaction")
    private Integer compaction;

    /**
     * A list of clusters the topic is replicated to.
     */
    @JsonProperty("geo-replication")
    private List<String> georeplication;

    /**
     * Topic retention policy.
     */
    @JsonProperty("retention")
    private PulsarRetention retention;

    /**
     * Message time-to-live in seconds.
     */
    @JsonProperty("ttl")
    private Integer ttl;

    /**
     * Message deduplication. When true, it ensures that each message produced on Pulsar topics is persisted to disk
     * only once.
     */
    @JsonProperty("deduplication")
    private Boolean deduplication;

    /**
     * OPTIONAL, defaults to latest. The version of this binding.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.1.0";

    public enum PulsarPesistenceType {
        PERSISTENCE("persistent"),
        NON_PERSISTENCE("non-persistent");

        public final String type;

        PulsarPesistenceType(String type) {
            this.type = type;
        }

        public static PulsarPesistenceType fromString(String type) {
            return valueOf(type.toUpperCase());
        }

        @Override
        public String toString() {
            return this.type;
        }
    }
}
