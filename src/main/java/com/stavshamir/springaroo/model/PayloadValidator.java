package com.stavshamir.springaroo.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class PayloadValidator {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void validate(Map<String, Object> payload, String payloadClassName) throws ClassNotFoundException {
        Class<?> payloadClass = Class.forName(payloadClassName);

        objectMapper.convertValue(payload, payloadClass);
    }

}
