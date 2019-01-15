package com.stavshamir.springaroo.model;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.annotations.ApiModel;
import io.swagger.converter.ModelConverters;
import io.swagger.inflector.examples.ExampleBuilder;
import io.swagger.inflector.examples.models.Example;
import io.swagger.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.models.Model;
import io.swagger.util.Json;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Models {

    private final ModelConverters converter = ModelConverters.getInstance();

    @Getter
    private final Map<String, Model> definitions = new HashMap<>();

    public Models() {
        SimpleModule simpleModule = new SimpleModule().addSerializer(new JsonNodeExampleSerializer());
        Json.mapper().registerModule(simpleModule);
    }

    public String register(Class<?> type) {
        log.debug("Registering model for {}", type.getSimpleName());

        Map<String, Model> models = converter.readAll(type);
        this.definitions.putAll(models);

        return getModelName(type);
    }

    public String getExample(String modelName) {
        Model model = definitions.get(modelName);

        if (null == model) {
            log.error("Model for {} was not found", modelName);
            return null;
        }

        Example example = ExampleBuilder.fromModel(modelName, model, definitions, new HashSet<>());
        return Json.pretty(example).replaceAll("\\s+", "");
    }

    private String getModelName(Class<?> type) {
        return Optional
                .ofNullable(type.getAnnotation(ApiModel.class))
                .map(ApiModel::value)
                .orElse(type.getSimpleName());
    }

}
