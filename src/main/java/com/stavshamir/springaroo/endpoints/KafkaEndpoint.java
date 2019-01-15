package com.stavshamir.springaroo.endpoints;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaEndpoint {

    private final String methodName;
    private final String topic;
    private final String payloadModelName;
    private final String payloadExample;

}
