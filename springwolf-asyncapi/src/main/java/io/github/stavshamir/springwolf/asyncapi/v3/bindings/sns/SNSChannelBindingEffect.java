// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SNSChannelBindingEffect {
    @JsonProperty("Allow")
    ALLOW,
    @JsonProperty("Deny")
    DENY
}
