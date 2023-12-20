// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.sns;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SNSChannelBindingOrderingType {
    @JsonProperty("standard")
    STANDARD,
    @JsonProperty("FIFO")
    FIFO
}
