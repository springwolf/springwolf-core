package com.stavshamir.swagger4kafka.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Info {

    private final String serviceName;
    private final String bootstrapServers;

}
