package io.github.stavshamir.springwolf.asyncapi.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;


@Data
@Builder
@Jacksonized
public class KafkaMessageDto {

    private final Map<String, String> headers;

    private final Map<String, ?> payload;

}
