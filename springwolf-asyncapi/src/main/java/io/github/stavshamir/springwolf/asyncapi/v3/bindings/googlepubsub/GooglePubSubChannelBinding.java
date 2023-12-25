// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.googlepubsub;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Channel Bindings Object is used to describe the Google Cloud Pub/Sub specific Topic details with AsyncAPI.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/googlepubsub/README.md#channel-binding-object">GooglePubSub Channel</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GooglePubSubChannelBinding extends ChannelBinding {
    /**
     * An object of key-value pairs (These are used to categorize Cloud Resources like Cloud Pub/Sub Topics.)
     */
    @JsonProperty("labels")
    private Object labels;

    /**
     * Indicates the minimum duration to retain a message after it is published to the topic (Must be a valid Duration.)
     */
    @JsonProperty("messageRetentionDuration")
    private String messageRetentionDuration;

    /**
     * Policy constraining the set of Google Cloud Platform regions where messages published to the topic may be stored
     */
    @JsonProperty("messageStoragePolicy")
    private GooglePubSubMessageStoragePolicy messageStoragePolicy;

    /**
     * Settings for validating messages published against a schema
     */
    @JsonProperty("schemaSettings")
    private GooglePubSubSchemaSettings schemaSettings;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.2.0";
}
