// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.sns;

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
public class SNSChannelBindingPolicy {
    /**
     * Required. An array of Statement objects, each of which controls a permission for this topic
     */
    @NotNull
    @JsonProperty("statements")
    private List<SNSChannelBindingStatements> statements;
}
