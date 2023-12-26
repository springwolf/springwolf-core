// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs;

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
public class SQSChannelBindingStatement {

    /**
     * Required. Either "Allow" or "Deny"
     */
    @NotNull
    @JsonProperty("effect")
    private SQSChannelBindingStatementEffect effect;

    /**
     * Required. The AWS account or resource ARN that this statement applies to
     */
    @NotNull
    @JsonProperty("principal")
    private String principal;

    /**
     * Required. The SQS permission being allowed or denied e.g. sqs:ReceiveMessage
     */
    @NotNull
    @JsonProperty("action")
    private String action;
}
