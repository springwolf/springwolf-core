// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class ClasspathUtil {
    private ClasspathUtil() {}

    public static String readAsString(String resourceName) throws IOException {
        try (InputStream inputStream = ClasspathUtil.class.getResourceAsStream(resourceName)) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public static JsonNode parseYamlFile(String resourceName) throws IOException {
        try (InputStream inputStream = ClasspathUtil.class.getResourceAsStream(resourceName)) {
            ObjectMapper yamlMapper = new DefaultAsyncApiSerializerService().getYamlMapper();
            return yamlMapper.readTree(inputStream);
        }
    }
}
