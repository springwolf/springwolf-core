package io.github.stavshamir.swagger4kafka.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaEndpoint {

    private String topic;
    private String payloadClassName;
    private String payloadModelName;
    private Map<String, Object> payloadExample;

}
