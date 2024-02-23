// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.googlepubsub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Schema Settings Object is used to describe the Google Cloud Pub/Sub SchemaSettings Object with AsyncAPI.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GooglePubSubSchemaSettings {
    /**
     * The encoding of the message (Must be one of the possible Encoding values.)
     */
    @JsonProperty("encoding")
    private String encoding;

    /**
     * The minimum (inclusive) revision allowed for validating messages
     */
    @JsonProperty("firstRevisionId")
    private String firstRevisionId;

    /**
     * The maximum (inclusive) revision allowed for validating messages
     */
    @JsonProperty("lastRevisionId")
    private String lastRevisionId;

    /**
     * The name of the schema that messages published should be validated against
     * (The format is projects/{project}/schemas/{schema}.)
     */
    @JsonProperty("name")
    private String name;
}
