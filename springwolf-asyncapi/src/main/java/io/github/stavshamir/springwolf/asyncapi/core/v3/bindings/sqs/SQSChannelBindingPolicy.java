// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SQSChannelBindingPolicy {

    /**
     * Required. An array of Statement objects, each of which controls a permission for this queue.
     */
    @NotNull
    @JsonProperty("statements")
    private List<SQSChannelBindingStatement> statements;
}
