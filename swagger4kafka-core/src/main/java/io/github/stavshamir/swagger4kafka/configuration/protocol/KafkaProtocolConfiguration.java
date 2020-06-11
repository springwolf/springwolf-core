package io.github.stavshamir.swagger4kafka.configuration.protocol;

import lombok.Builder;

@Builder
public class KafkaProtocolConfiguration implements AsyncApiProtocolConfiguration {

    private String basePackage;

    @Override
    public String getBasePackage() {
        return basePackage;
    }

}
