package io.github.stavshamir.springwolf.asyncapi.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class KafkaMessageDto {

    private final Map<String, String> headers;

    private final Map<String, ?> payload;

}
