// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.googlepubsub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Schema Definition Object is used to describe the Google Cloud Pub/Sub Schema Object with AsyncAPI. While some
 * of this information could be, or is, described using native AsyncAPI, for consistency it makes sense to provide this
 * information here at all times, especially for cases where AsyncAPI does not natively support describing payloads
 * using a supported Google Cloud Pub/Sub schema format like Protobuf.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GooglePubSubSchema {
    /**
     * The name of the schema
     */
    @JsonProperty("name")
    private String name;
}
