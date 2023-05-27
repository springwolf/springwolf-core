package io.github.stavshamir.springwolf.example.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Example payload model")
public class ExamplePayloadDto {
    @Schema(description = "Some string field", example = "some string value", requiredMode =REQUIRED)
    private String someString;

    @Schema(description = "Some long field", example = "5")
    private long someLong;

    @Schema(description = "Some enum field", example = "FOO2", requiredMode = REQUIRED)
    private ExampleEnum someEnum;

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public long getSomeLong() {
        return someLong;
    }

    public void setSomeLong(long someLong) {
        this.someLong = someLong;
    }

    public ExampleEnum getSomeEnum() {
        return someEnum;
    }

    public void setSomeEnum(ExampleEnum someEnum) {
        this.someEnum = someEnum;
    }

    enum ExampleEnum {
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
