// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExtendableObject;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CorrelationID extends ExtendableObject {
    /**
     * An optional description of the identifier. <a href="https://spec.commonmark.org/">CommonMark syntax</a> can be
     * used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * REQUIRED. A runtime expression that specifies the location of the correlation ID.
     */
    @NotNull
    @JsonProperty(value = "location")
    private String location;
}
