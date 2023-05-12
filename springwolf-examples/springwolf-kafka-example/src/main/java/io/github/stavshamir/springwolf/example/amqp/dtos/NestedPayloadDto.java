package io.github.stavshamir.springwolf.example.amqp.dtos;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Set;

@Schema(description = "Payload model with nested complex types")
public class NestedPayloadDto {
    @ArraySchema(schema = @Schema(description = "Some string field", example = "some string value"), uniqueItems = true)
    private Set<String> someStrings;

    @ArraySchema
    private List<ExamplePayloadDto> examplePayloads;

    public Set<String> getSomeStrings() {
        return someStrings;
    }

    public void setSomeStrings(Set<String> someStrings) {
        this.someStrings = someStrings;
    }

    public List<ExamplePayloadDto> getExamplePayloads() {
        return examplePayloads;
    }

    public void setExamplePayloads(List<ExamplePayloadDto> examplePayloads) {
        this.examplePayloads = examplePayloads;
    }

    @Override
    public String toString() {
        return "NestedPayloadDto{" +
                "someStrings=" + someStrings +
                ", examplePayloads=" + examplePayloads +
                '}';
    }
}
