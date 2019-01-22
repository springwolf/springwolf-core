package com.stavshamir.swagger4kafka.configuration;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Docket {

    private final String basePackage;

    @Builder.Default
    private final String serviceName = "";

    @Builder.Default
    private final String bootstrapServers = "localhost:9092";

}
