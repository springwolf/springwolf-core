// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Example payload model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamplePayloadDto {
    @Schema(description = "Some string field", example = "some string value", requiredMode = REQUIRED)
    private String someString;

    @Schema(description = "Some long field", example = "5")
    private long someLong;

    @Schema(description = "Some enum field", example = "FOO2", requiredMode = REQUIRED)
    private ExampleEnum someEnum;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

    public enum ExampleEnum {
        FOO1,
        FOO2,
        FOO3
    }
}
