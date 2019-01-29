package com.stavshamir.swagger4kafka.configuration;

import lombok.Builder;
import lombok.Getter;

/**
 * The primary interface to the swagger4kafka framework.
 */
@Builder
@Getter
public class Docket {

    /**
     * Define the base package in which @KafkaListener annotations will be scanned (required).
     */
    private final String basePackage;

    /**
     * Define the name of the service to be displayed in the UI (optional).
     */
    @Builder.Default
    private final String serviceName = "";

    /**
     * Define the bootstrap servers to be used for publishing messages (optional - defaults to localhost:9092).
     */
    @Builder.Default
    private final String bootstrapServers = "localhost:9092";

}
