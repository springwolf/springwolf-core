package io.github.stavshamir.springwolf.example.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Another payload model")
public class AnotherPayloadDto {

    @Schema(description = "Foo field", example = "bar")
    private String foo;

    @Schema(description = "Example field", required = true)
    private ExamplePayloadDto example;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public ExamplePayloadDto getExample() {
        return example;
    }

    public void setExample(ExamplePayloadDto example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "AnotherPayloadDto{" +
                "foo='" + foo + '\'' +
                ", example=" + example +
                '}';
    }

}
