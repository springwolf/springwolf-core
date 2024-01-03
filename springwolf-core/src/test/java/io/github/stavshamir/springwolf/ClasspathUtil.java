// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class ClasspathUtil {
    private ClasspathUtil() {}

    public static String readAsString(String resourceName) throws IOException {
        InputStream inputStream = ClasspathUtil.class.getResourceAsStream(resourceName);
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    public static JsonNode parseYamlFile(String resourceName) throws IOException {
        InputStream inputStream = ClasspathUtil.class.getResourceAsStream(resourceName);
        ObjectMapper objectMapper = new DefaultAsyncApiSerializer().getYamlObjectMapper();
        return objectMapper.readTree(inputStream);
    }
}
