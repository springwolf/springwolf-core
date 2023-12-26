// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SQSChannelBindingStatementEffect {
    @JsonProperty("Allow")
    ALLOW,
    @JsonProperty("Deny")
    DENY
}
