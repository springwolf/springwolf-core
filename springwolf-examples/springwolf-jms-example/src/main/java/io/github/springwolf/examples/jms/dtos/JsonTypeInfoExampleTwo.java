// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms.dtos;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

@JsonTypeName("exampleTwo")
@Schema(description = "Json Type Info Example Two model")
public record JsonTypeInfoExampleTwo(
        @Schema(description = "Boo field", example = "booValue", requiredMode = NOT_REQUIRED) String boo)
        implements JsonTypeInfoInterface {}
