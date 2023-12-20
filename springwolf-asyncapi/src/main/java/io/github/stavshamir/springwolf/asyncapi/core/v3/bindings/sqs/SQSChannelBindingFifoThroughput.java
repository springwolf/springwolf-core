// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.bindings.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SQSChannelBindingFifoThroughput {
    @JsonProperty("perQueue")
    PER_QUEUE,
    @JsonProperty("perMessageGroupId")
    PER_MESSAGE_GROUP_ID
}
