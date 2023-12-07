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
public class SNSChannelBindingStatements {
    /**
     * Required. Either "Allow" or "Deny"
     */
    @NotNull
    @JsonProperty("effect")
    private SNSChannelBindingEffect effect;

    /**
     * Required. The AWS account or resource ARN that this statement applies to
     */
    @NotNull
    @JsonProperty("principal")
    private String principal;

    /**
     * Required. The SNS permission being allowed or denied e.g. sns:Publish
     */
    @NotNull
    @JsonProperty("action")
    private String action;
}
