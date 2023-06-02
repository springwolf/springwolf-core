package io.github.stavshamir.springwolf.example.kafka.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Example payload model")
@Getter
@Setter
@EqualsAndHashCode
public class ExamplePayloadDto {
    @Schema(description = "Some string field", example = "some string value", requiredMode = REQUIRED)
    private String someString;

    @Schema(description = "Some long field", example = "5")
    private long someLong;

    @Schema(description = "Some enum field", example = "FOO2", requiredMode = REQUIRED)
    private ExampleEnum someEnum;

    public enum ExampleEnum {
        FOO1, FOO2, FOO3
    }

    @Override
    public String toString() {
        return "ExamplePayloadDto{" +
                "someString='" + someString + '\'' +
                ", someLong=" + someLong +
                ", someEnum=" + someEnum +
                '}';
    }

}
