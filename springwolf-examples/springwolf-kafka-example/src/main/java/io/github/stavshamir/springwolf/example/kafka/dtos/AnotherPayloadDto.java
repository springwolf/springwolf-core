package io.github.stavshamir.springwolf.example.kafka.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Another payload model")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AnotherPayloadDto {

    @Schema(description = "Foo field", example = "bar", requiredMode = NOT_REQUIRED)
    private String foo;

    @Schema(description = "Example field", requiredMode = REQUIRED)
    private ExamplePayloadDto example;
}
