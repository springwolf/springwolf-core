package io.github.stavshamir.springwolf.example.cloudstream.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Input payload model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputPayload {

    private List<String> foo;
    private int bar;
}
