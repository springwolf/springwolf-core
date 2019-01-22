package com.stavshamir.swagger4kafka.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map jsonResourceAsMap(Class<?> testClass, String path) throws IOException {
        InputStream s = testClass.getResourceAsStream(path);
        String json = IOUtils.toString(s, "UTF-8");
        return objectMapper.readValue(json, Map.class);
    }

}
