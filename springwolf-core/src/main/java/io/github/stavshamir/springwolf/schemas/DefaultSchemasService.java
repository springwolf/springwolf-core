package io.github.stavshamir.springwolf.schemas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.swagger.oas.inflector.examples.ExampleBuilder;
import io.swagger.oas.inflector.examples.models.Example;
import io.swagger.oas.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class DefaultSchemasService implements SchemasService {

    private final ModelConverters converter = ModelConverters.getInstance();
    private final ObjectMapper objectMapper = Json.mapper();

    private final Map<String, Schema> definitions = new HashMap<>();

    public DefaultSchemasService(Optional<List<ModelConverter>> externalModelConverters) {
        externalModelConverters.ifPresent(converters -> converters.forEach(converter::addConverter));

        SimpleModule simpleModule = new SimpleModule().addSerializer(new JsonNodeExampleSerializer());
        objectMapper.registerModule(simpleModule);
    }

    @Override
    public Map<String, Schema> getDefinitions() {
        // The examples must first be set as JSON strings (the inflector does not work otherwise)
        definitions.forEach(this::buildExampleAsString);

        // Then they must be deserialized to map, or they will be serialized as reguler string and not json by the
        // object mapper
        definitions.forEach(this::deserializeExampleToMap);
        return definitions;
    }

    @Override
    public String register(AsyncHeaders headers) {
        log.debug("Registering schema for {}", headers.getSchemaName());

        MapSchema headerSchema = new MapSchema();
        headerSchema.properties(headers);

        this.definitions.put(headers.getSchemaName(), headerSchema);

        return headers.getSchemaName();
    }

    @Override
    public String register(Class<?> type) {
        log.debug("Registering schema for {}", type.getSimpleName());

        Map<String, Schema> schemas = converter.readAll(type);
        this.definitions.putAll(schemas);

        if (schemas.size() == 0 && type.equals(String.class)) {
            this.definitions.put("String", new StringSchema());
            return "String";
        }

        if (schemas.size() == 1) {
            return new ArrayList<>(schemas.keySet()).get(0);
        }

        Set<String> resolvedPayloadModelName = converter.read(type).keySet();
        if (!resolvedPayloadModelName.isEmpty()) {
            return new ArrayList<>(resolvedPayloadModelName).get(0);
        }

        return type.getSimpleName();
    }

    private void buildExampleAsString(String k, Schema schema) {
        log.debug("Setting example for {}", schema.getName());

        Example example = ExampleBuilder.fromSchema(schema, definitions);
        try {
            String exampleAsJson = objectMapper.writeValueAsString(example);
            schema.setExample(exampleAsJson);
        } catch (JsonProcessingException e) {
            log.error("Failed to write example value as a string");
        }
    }

    private void deserializeExampleToMap(String k, Schema schema) {
        try {
            schema.setExample(objectMapper.readValue((String) schema.getExample(), Object.class));
        } catch (IOException e) {
            log.error("Failed to convert example object of {} to map", schema.getName());
        }
    }

}
