package com.stavshamir.springaroo;

import lombok.Builder;
import lombok.Data;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.reflect.Method;
import java.util.Optional;

@Data
@Builder
public class KafkaEndpoint {

    private String methodName;
    private String[] topics;
    private Class<?> payloadType;

}
