// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp.stomp.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Another payload model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnotherPayloadDto {

    @Schema(description = "Foo field", example = "bar", requiredMode = NOT_REQUIRED)
    private String foo;

    @Schema(description = "Example field", requiredMode = REQUIRED)
    private ExamplePayloadDto example;
}
