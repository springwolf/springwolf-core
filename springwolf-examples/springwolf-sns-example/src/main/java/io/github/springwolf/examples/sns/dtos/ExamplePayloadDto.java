// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sns.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Example payload model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamplePayloadDto {
    @Schema(description = "Some string field", example = "some string value", requiredMode = REQUIRED)
    private String someString;

    @Schema(description = "Some long field", example = "5", minimum = "0")
    private long someLong;

    @Schema(description = "Some enum field", example = "FOO2", requiredMode = REQUIRED)
    private ExampleEnum someEnum;

    @RequiredArgsConstructor
    public enum ExampleEnum {
        FOO1(1),
        FOO2(2),
        FOO3(3);

        private final int code;
    }
}
