// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Json Type Info Payload Dto model")
public record JsonTypeInfoPayloadDto(
        @Schema(description = "", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                JsonTypeInfoInterface jsonTypeInfoInterface) {}
