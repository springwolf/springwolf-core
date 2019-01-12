package com.stavshamir.springaroo.model;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.annotations.ApiModel;
import io.swagger.converter.ModelConverters;
import io.swagger.inflector.examples.ExampleBuilder;
import io.swagger.inflector.examples.models.Example;
import io.swagger.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.models.Model;
import io.swagger.util.Json;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Slf4j
public class Models {

    private final ModelConverters converter = ModelConverters.getInstance();
    private final Map<String, Model> models = new HashMap<>();

    public Models() {
        SimpleModule simpleModule = new SimpleModule().addSerializer(new JsonNodeExampleSerializer());
        Json.mapper().registerModule(simpleModule);
    }

    public void registerModel(Class<?> type) {
        log.debug("Registering model for {}", type.getSimpleName());

        Map<String, Model> models = converter.readAll(type);
        this.models.putAll(models);
    }

    public String getExample(Class<?> type) {
        String modelName = getModelName(type);

        if (!models.containsKey(modelName)) {
            registerModel(type);
        }

        Model model = models.get(modelName);
        if (null == model) {
            log.error("Model for {} was not found", type.getSimpleName());
            return "";
        }

        Example example = ExampleBuilder.fromModel(modelName, model, models, new HashSet<>());
        return Json.pretty(example);
    }

    public Set<String> getModelsAsJson() {
        return models.entrySet()
                .stream()
                .map(Json::pretty)
                .map(s -> s.replaceAll("\\s+", ""))
                .collect(toSet());
    }

    private String getModelName(Class<?> type) {
        return Optional
                .ofNullable(type.getAnnotation(ApiModel.class))
                .map(ApiModel::value)
                .orElse(type.getSimpleName());
    }

}
