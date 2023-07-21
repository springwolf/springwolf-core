package io.github.stavshamir.springwolf.example.cloudstream.payload;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Output payload model")
public class OutputPayload {

    public OutputPayload() {}

    public OutputPayload(String someString, long someLong) {
        this.someString = someString;
        this.someLong = someLong;
    }

    @Schema(
            description = "Some string field",
            example = "some string value",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String someString;

    @Schema(description = "Some long field", example = "5")
    private long someLong;

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

    @Override
    public String toString() {
        return "OutputPayload{" + "someString='" + someString + '\'' + ", someLong=" + someLong + '}';
    }
}
