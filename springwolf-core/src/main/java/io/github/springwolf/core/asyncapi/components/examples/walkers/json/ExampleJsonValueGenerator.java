// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.json;

import io.github.springwolf.core.asyncapi.components.examples.walkers.ExampleValueGenerator;
import io.github.springwolf.core.asyncapi.components.examples.walkers.PropertyExample;
import io.swagger.v3.oas.models.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.BooleanNode;
import tools.jackson.databind.node.DoubleNode;
import tools.jackson.databind.node.IntNode;
import tools.jackson.databind.node.JsonNodeFactory;
import tools.jackson.databind.node.ObjectNode;
import tools.jackson.dataformat.yaml.YAMLMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class ExampleJsonValueGenerator implements ExampleValueGenerator<JsonNode, JsonNode> {

    private static final Set<String> SUPPORTED_CONTENT_TYPES = Set.of("application/json");

    private static final YAMLMapper yamlMapper = YAMLMapper.builder().build();

    @Override
    public boolean canHandle(String contentType) {
        return SUPPORTED_CONTENT_TYPES.contains(contentType);
    }

    @Override
    public Optional<String> lookupSchemaName(Schema schema) {
        return Optional.ofNullable(schema.getName());
    }

    @NotNull
    @Override
    public Optional<JsonNode> createBooleanExample(Boolean value, Schema schema) {
        return Optional.of(BooleanNode.valueOf(value));
    }

    @Override
    public Optional<JsonNode> createIntegerExample(Integer value, Schema schema) {
        return Optional.of(new IntNode(value));
    }

    @Override
    public Optional<JsonNode> createDoubleExample(Double value, Schema schema) {
        return Optional.of(new DoubleNode(value));
    }

    @Override
    public Optional<JsonNode> createStringExample(String value, Schema schema) {
        return Optional.of(JsonNodeFactory.instance.textNode(value));
    }

    @Override
    public Optional<JsonNode> createEnumExample(String anEnumValue, Schema schema) {
        return Optional.of(JsonNodeFactory.instance.textNode(anEnumValue));
    }

    @Override
    public Optional<JsonNode> createUnknownSchemaStringTypeExample(String type) {
        return Optional.of(JsonNodeFactory.instance.textNode("unknown schema type: " + type));
    }

    @Override
    public Optional<JsonNode> createUnknownSchemaStringFormatExample(String schemaFormat) {
        return Optional.of(JsonNodeFactory.instance.textNode("unknown string schema format: " + schemaFormat));
    }

    @Override
    public JsonNode createArrayExample(Optional<String> name, JsonNode arrayItem) {
        ArrayNode array = yamlMapper.createArrayNode();
        array.add(arrayItem);
        return array;
    }

    @Override
    public JsonNode prepareForSerialization(Schema schema, JsonNode exampleObject) {
        return exampleObject;
    }

    @Override
    public JsonNode createRaw(Object exampleValue) {
        try {
            return yamlMapper.readTree(exampleValue.toString());
        } catch (JacksonException e) {
            log.info("Unable to parse example to JsonNode: {}", exampleValue, e);
            return null;
        }
    }

    @Override
    public JsonNode getExampleOrNull(Optional<String> fieldName, Schema schema, Object example) {
        if (example instanceof JsonNode node) {
            return node;
        }

        return null;
    }

    @Override
    public JsonNode startObject(Optional<String> name) {
        return yamlMapper.createObjectNode();
    }

    @Override
    public void addPropertyExamples(JsonNode object, List<PropertyExample<JsonNode>> properties) {
        if (object == null) {
            throw new IllegalArgumentException("JsonNode to add properties must not be empty");
        }

        if (object instanceof ObjectNode objectNode) {
            properties.forEach(property -> objectNode.set(property.name(), property.example()));
        } else {
            throw new IllegalArgumentException("JsonNode to add properties must be of type ObjectNode");
        }
    }

    @Override
    public Optional<JsonNode> createEmptyObjectExample() {
        return Optional.of(yamlMapper.createObjectNode());
    }
}
