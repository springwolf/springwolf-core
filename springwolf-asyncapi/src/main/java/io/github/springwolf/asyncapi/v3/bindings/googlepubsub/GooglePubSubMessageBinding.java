// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.googlepubsub;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Message Binding Object is used to describe the Google Cloud Pub/Sub specific PubsubMessage details, alongside
 * with pertintent parts of the Google Cloud Pub/Sub Schema Object, with AsyncAPI.
 *
 * @see <a href="https://github.com/asyncapi/bindings/blob/master/googlepubsub/README.md#message-binding-object">GooglePubSub Message</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GooglePubSubMessageBinding extends MessageBinding {
    /**
     * Attributes for this message (If this field is empty, the message must contain non-empty data. This can be used
     * to filter messages on the subscription.)
     */
    @JsonProperty("attributes")
    private Object attributes;

    /**
     * If non-empty, identifies related messages for which publish order should be respected (For more information,
     * see ordering messages.)
     */
    @JsonProperty("orderingKey")
    private String orderingKey;

    /**
     * Describes the schema used to validate the payload of this message
     */
    @JsonProperty("schema")
    private GooglePubSubSchema schema;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     */
    @Builder.Default
    @JsonProperty("bindingVersion")
    private String bindingVersion = "0.2.0";
}
