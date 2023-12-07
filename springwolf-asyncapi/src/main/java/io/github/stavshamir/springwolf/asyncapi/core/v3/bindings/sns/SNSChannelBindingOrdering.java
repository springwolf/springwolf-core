// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SNSChannelBindingOrdering {
    /**
     * Required. Defines the type of SNS Topic. Can be either standard or FIFO.
     */
    @NotNull
    @JsonProperty("type")
    private SNSChannelBindingOrderingType type;

    /**
     * Optional. Whether the de-duplication of messages should be turned on. Defaults to false
     */
    @NotNull
    @Builder.Default
    @JsonProperty("contentBasedDeduplication")
    private Boolean contentBasedDeduplication = false;
}
