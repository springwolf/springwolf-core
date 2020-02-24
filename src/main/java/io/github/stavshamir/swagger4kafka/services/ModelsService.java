package io.github.stavshamir.swagger4kafka.services;

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

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import org.springframework.stereotype.Service;
import springfox.documentation.schema.Enums;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.AllowableValues;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class ModelsService {

    private final ModelConverters converter = ModelConverters.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Getter
    private final Map<String, Model> definitions = new HashMap<>();

    public ModelsService() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleModule simpleModule = new SimpleModule().addSerializer(new JsonNodeExampleSerializer());
        Json.mapper().registerModule(simpleModule);
    }

    public String register(Class<?> type) {
        log.debug("Registering model for {}", type.getSimpleName());

        Map<String, Model> models = converter.readAll(type);
        setDeserializableEnumValues(type, models);
        this.definitions.putAll(models);

        return getModelName(type);
    }

    private void setDeserializableEnumValues(Class<?> type, Map<String, Model> models) {
        Model model = models.get(getModelName(type));
        if (model == null) {
            return;
        }

        setDeserializableEnumValues(type, model);

        for (Field field : (type).getDeclaredFields()) {
            Type fieldType = field.getGenericType();

            if (fieldType instanceof ParameterizedType) {
                fieldType = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
            }

            if(fieldType instanceof Class<?>) {
                setDeserializableEnumValues((Class<?>) fieldType, models);
            }
        }
    }

    private void setDeserializableEnumValues(Class<?> type, Model model) {
        Map<String, List<String>> enumFieldsValues = mapEnumFieldsNameToTheirEnumValues(type);
        if (enumFieldsValues.isEmpty()) {
            return;
        }

        Map<String, StringProperty> enumProperties = getEnumProperties(model);
        enumProperties.forEach((fieldName, property) -> property.setEnum(enumFieldsValues.get(fieldName)));
    }

    private Map<String, StringProperty> getEnumProperties(Model model) {
        return model.getProperties().entrySet().stream()
                .filter(e -> e.getValue() instanceof StringProperty)
                .filter(e -> ((StringProperty) e.getValue()).getEnum() != null)
                .collect(toMap(Map.Entry::getKey, e -> (StringProperty)e.getValue()));
    }

    private Map<String, List<String>> mapEnumFieldsNameToTheirEnumValues(Class<?> type) {
        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.getType().isEnum())
                .collect(toMap(Field::getName, field -> getEnumValues(field.getType())));
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
