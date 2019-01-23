package com.stavshamir.swagger4kafka.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Payload {
    private String className;
    private Map<String, Object> object;
}
