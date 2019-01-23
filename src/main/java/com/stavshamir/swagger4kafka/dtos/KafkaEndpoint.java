package com.stavshamir.swagger4kafka.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class KafkaEndpoint {

    private final String topic;
    private final String payloadClassName;
    private final String payloadModelName;
    private final Map<String, Object> payloadExample;

}
