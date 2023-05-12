package io.github.stavshamir.springwolf.example.cloudstream.payload;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Input payload model")
public class InputPayload {

    private List<String> foo;
    private int bar;

    public List<String> getFoo() {
        return foo;
    }

    public void setFoo(List<String> foo) {
        this.foo = foo;
    }

    public int getBar() {
        return bar;
    }

    public void setBar(int bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "InputPayload{" +
                "foo=" + foo +
                ", bar=" + bar +
                '}';
    }

}
