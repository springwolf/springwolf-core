package io.github.stavshamir.springwolf.example.dtos;

public class ExamplePayloadDto {

    private String someString;
    private long someLong;
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

    private enum ExampleEnum {
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
