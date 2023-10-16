// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.cloudstream.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Output payload model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputPayload {

    @Schema(
            description = "Some string field",
            example = "some string value",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String someString;

    @Schema(description = "Some long field", example = "5")
    private long someLong;
}
