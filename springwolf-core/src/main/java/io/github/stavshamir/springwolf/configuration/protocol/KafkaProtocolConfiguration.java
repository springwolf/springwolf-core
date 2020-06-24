package io.github.stavshamir.springwolf.configuration.protocol;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

@Builder
public class KafkaProtocolConfiguration implements AsyncApiProtocolConfiguration {

    @NonNull
    private final String basePackage;

    @Getter
    private final Map<String, Object> producerConfiguration;

    @Override
    public String getBasePackage() {
        return basePackage;
    }

}
