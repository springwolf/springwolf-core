package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.oas.inflector.examples.ExampleBuilder;
import io.swagger.oas.inflector.examples.models.Example;
import io.swagger.oas.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

import static io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigConstants.SPRINGWOLF_SCHEMA_EXAMPLE_GENERATOR;

/**
 * This only exists as a fallback when {@see ExampleJsonGenerator} does not work. Please open issue when you feel the need to use this class.
 */
@Component
@Slf4j
@ConditionalOnProperty(name = SPRINGWOLF_SCHEMA_EXAMPLE_GENERATOR, havingValue = "swagger-inflector-json")
@Deprecated(forRemoval = true)
public class SwaggerInflectorJsonGenerator implements ExampleGenerator {
    private static final ObjectMapper objectMapper = Json.mapper();

    public SwaggerInflectorJsonGenerator() {
        SimpleModule simpleModule = new SimpleModule().addSerializer(new JsonNodeExampleSerializer());
        objectMapper.registerModule(simpleModule);
    }

    @Nullable
    @Override
    public Object fromSchema(Schema schema, Map<String, Schema> definitions) {
        try {
            String exampleAsJson = buildSchema(schema, definitions);
            return objectMapper.readValue(exampleAsJson, Object.class);
        } catch (JsonProcessingException e) {
            log.error("Failed to write example value as a string");
        }
        return null;
    }

    public String buildSchema(Schema schema, Map<String, Schema> definitions) throws JsonProcessingException {
        Example example = ExampleBuilder.fromSchema(schema, definitions);
        return objectMapper.writeValueAsString(example);
    }
}
