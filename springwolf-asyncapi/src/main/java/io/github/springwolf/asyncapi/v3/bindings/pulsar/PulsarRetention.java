// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.pulsar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Retention Definition Object is used to describe the Pulsar Retention policy.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PulsarRetention {
    /**
     * Time given in Minutes.
     */
    @Builder.Default
    @JsonProperty("time")
    private Integer time = 0;

    /**
     * Size given in MegaBytes.
     */
    @Builder.Default
    @JsonProperty("size")
    private Integer size = 0;
}
