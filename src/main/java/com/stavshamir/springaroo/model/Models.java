package com.stavshamir.springaroo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.annotations.ApiModel;
import io.swagger.converter.ModelConverters;
import io.swagger.inflector.examples.ExampleBuilder;
import io.swagger.inflector.examples.models.Example;
import io.swagger.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.models.Model;
import io.swagger.models.properties.StringProperty;
import io.swagger.util.Json;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import springfox.documentation.schema.Enums;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.AllowableValues;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public class Models {

    private final ModelConverters converter = ModelConverters.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Getter
    private final Map<String, Model> definitions = new HashMap<>();

    public Models() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleModule simpleModule = new SimpleModule().addSerializer(new JsonNodeExampleSerializer());
        Json.mapper().registerModule(simpleModule);
    }

    public String register(Class<?> type) {
        log.debug("Registering model for {}", type.getSimpleName());

        Map<String, Model> models = converter.readAll(type);
        this.definitions.putAll(models);

        String modelName = getModelName(type);
        setDeserializableEnumValues(type, models.get(modelName));

        return modelName;
    }

    private void setDeserializableEnumValues(Class<?> type, Model model) {
        Map<String, List<String>> enumFieldsValues = Arrays
                .stream(type.getDeclaredFields())
                .filter(field -> field.getType().isEnum())
                .collect(toMap(Field::getName,field -> getEnumValues(field.getType())));

        Map<String, StringProperty> enumProperties = model.getProperties().entrySet().stream()
                .filter(e -> e.getValue() instanceof StringProperty)
                .filter(e -> ((StringProperty) e.getValue()).getEnum() != null)
                .collect(toMap(Map.Entry::getKey, e -> (StringProperty)e.getValue()));

        enumProperties.forEach((fieldName, property) -> property.setEnum(enumFieldsValues.get(fieldName)));
    }

    private List<String> getEnumValues(Class<?> type) {
        AllowableValues allowableValues = Enums.allowableValues(type);
        return ((AllowableListValues) allowableValues).getValues();
    }

    public Map<String, Object> getExample(String modelName) {
        Model model = definitions.get(modelName);

        if (null == model) {
            log.error("Model for {} was not found", modelName);
            return null;
        }

        Example example = ExampleBuilder.fromModel(modelName, model, definitions, new HashSet<>());
        String exampleAsJson = Json.pretty(example);

        try {
            return objectMapper.readValue(exampleAsJson, Map.class);
        } catch (IOException e) {
            log.error("Failed to convert example object of {} to map", modelName);
            return null;
        }
    }

    private String getModelName(Class<?> type) {
        return Optional
                .ofNullable(type.getAnnotation(ApiModel.class))
                .map(ApiModel::value)
                .orElse(type.getSimpleName());
    }

}
