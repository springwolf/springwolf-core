// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OperationAction {
    @JsonProperty("send")
    SEND,
    @JsonProperty("receive")
    RECEIVE
}
