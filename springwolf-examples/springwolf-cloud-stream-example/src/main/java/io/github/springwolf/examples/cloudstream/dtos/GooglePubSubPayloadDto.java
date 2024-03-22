// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.cloudstream.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Google pubsub payload model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GooglePubSubPayloadDto {
    @Schema(description = "Some string field", example = "some string value", requiredMode = REQUIRED)
    private String someString;

    @Schema(description = "Some long field", example = "5")
    private long someLong;
}
