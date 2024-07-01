// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Generic payload model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericPayloadDto<T> {

    @Schema(description = "Generic Payload field", requiredMode = REQUIRED)
    private T genericValue;
}
