package io.github.stavshamir.springwolf.example.dtos;

public class AnotherPayloadDto {

    private String foo;
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
