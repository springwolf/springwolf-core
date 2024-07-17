// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms.dtos;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

@JsonTypeName("exampleOne")
@Schema(description = "Json Type Info Example One model")
public record JsonTypeInfoExampleOne(
        @Schema(description = "Foo field", example = "fooValue", requiredMode = NOT_REQUIRED) String foo)
        implements JsonTypeInfoInterface {}
