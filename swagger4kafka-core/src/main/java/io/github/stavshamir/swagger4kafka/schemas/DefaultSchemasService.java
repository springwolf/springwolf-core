package io.github.stavshamir.swagger4kafka.schemas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.oas.inflector.examples.ExampleBuilder;
import io.swagger.oas.inflector.examples.models.Example;
import io.swagger.oas.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.util.Json;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DefaultSchemasService implements SchemasService {

    private final ModelConverters converter = ModelConverters.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Getter
    private final Map<String, Schema> definitions = new HashMap<>();

    public DefaultSchemasService() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleModule simpleModule = new SimpleModule().addSerializer(new JsonNodeExampleSerializer());
        Json.mapper().registerModule(simpleModule);
    }

    @Override
    public String register(Class<?> type) {
        log.debug("Registering schema for {}", type.getSimpleName());

        Map<String, Schema> schemas = converter.readAll(type);
        this.definitions.putAll(schemas);

        return type.getSimpleName();
    }

    @Override
    public Map<String, Object> getExample(String modelName) {
        Schema<?> schema = definitions.get(modelName);

        if (null == schema) {
            log.error("Model for {} was not found", modelName);
            return null;
        }

        Example example = ExampleBuilder.fromSchema(schema, definitions);
        String exampleAsJson = Json.pretty(example);

        try {
            return objectMapper.readValue(exampleAsJson, Map.class);
        } catch (IOException e) {
            log.error("Failed to convert example object of {} to map", modelName);
            return null;
        }
    }

}
