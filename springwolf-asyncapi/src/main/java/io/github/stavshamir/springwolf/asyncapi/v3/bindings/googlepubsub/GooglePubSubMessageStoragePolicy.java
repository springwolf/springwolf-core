// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.googlepubsub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The Message Storage Policy Object is used to describe the Google Cloud Pub/Sub MessageStoragePolicy Object
 * with AsyncAPI.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GooglePubSubMessageStoragePolicy {
    /**
     * A list of IDs of GCP regions where messages that are published to the topic may be persisted in storage
     */
    @JsonProperty("allowedPersistenceRegions")
    private List<String> allowedPersistenceRegions;
}
