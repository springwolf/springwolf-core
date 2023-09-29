// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.dtos;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Schema(description = "Payload model with nested complex types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NestedPayloadDto {
    @ArraySchema(schema = @Schema(description = "Some string field", example = "some string value"), uniqueItems = true)
    private Set<String> someStrings;

    @ArraySchema
    private List<ExamplePayloadDto> examplePayloads;
}
