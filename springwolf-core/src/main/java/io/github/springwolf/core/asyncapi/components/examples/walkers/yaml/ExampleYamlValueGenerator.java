// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.yaml;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.springwolf.core.asyncapi.components.examples.walkers.ExampleValueGenerator;
import io.github.springwolf.core.asyncapi.components.examples.walkers.PropertyExample;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class ExampleYamlValueGenerator implements ExampleValueGenerator<JsonNode, String> {

    private final Set<String> SUPPORTED_CONTENT_TYPES = Set.of(MediaType.APPLICATION_YAML_VALUE);
    private final Schema<String> OVERRIDE_SCHEMA = new StringSchema();

    private final ExampleJsonValueGenerator exampleJsonValueGenerator;
    private final ExampleYamlValueSerializer exampleYamlValueSerializer;
    private final SpringwolfConfigProperties springwolfConfigProperties;

    @Override
    public boolean canHandle(String contentType) {
        return SUPPORTED_CONTENT_TYPES.contains(contentType);
    }

    @Override
    public Optional<String> lookupSchemaName(Schema schema) {
        return exampleJsonValueGenerator.lookupSchemaName(schema);
    }

    @Override
    public String prepareForSerialization(Schema schema, JsonNode exampleObject) {
        final String name = schema.getName();
        try {
            if (springwolfConfigProperties.isStudioCompatibility()) {

                // spec workaround to embedded yaml examples as string https://github.com/asyncapi/spec/issues/1038
                schema.setType(OVERRIDE_SCHEMA.getType());
                schema.setTypes(OVERRIDE_SCHEMA.getTypes());
            }

            return exampleYamlValueSerializer.writeDocumentAsYamlString(exampleObject);
        } catch (JacksonException e) {
            log.warn("Serialize {}", name, e);
            return null;
        }
    }

    @Override
    public Optional<JsonNode> createIntegerExample(Integer value, Schema schema) {
        return this.exampleJsonValueGenerator.createIntegerExample(value, schema);
    }

    @Override
    public Optional<JsonNode> createDoubleExample(Double value, Schema schema) {
        return this.exampleJsonValueGenerator.createDoubleExample(value, schema);
    }

    @Override
    public Optional<JsonNode> createBooleanExample(Boolean value, Schema schema) {
        return this.exampleJsonValueGenerator.createBooleanExample(value, schema);
    }

    @Override
    public JsonNode startObject(Optional<String> name) {
        return this.exampleJsonValueGenerator.startObject(name);
    }

    @Override
    public void addPropertyExamples(JsonNode object, List<PropertyExample<JsonNode>> properties) {
        this.exampleJsonValueGenerator.addPropertyExamples(object, properties);
    }

    @Override
    public Optional<JsonNode> createEmptyObjectExample() {
        return this.exampleJsonValueGenerator.createEmptyObjectExample();
    }

    @Override
    public Optional<JsonNode> createStringExample(String value, Schema schema) {
        return this.exampleJsonValueGenerator.createStringExample(value, schema);
    }

    @Override
    public Optional<JsonNode> createEnumExample(String anEnumValue, Schema schema) {
        return this.exampleJsonValueGenerator.createEnumExample(anEnumValue, schema);
    }

    @Override
    public Optional<JsonNode> createUnknownSchemaStringTypeExample(String schemaType) {
        return this.exampleJsonValueGenerator.createUnknownSchemaStringTypeExample(schemaType);
    }

    @Override
    public Optional<JsonNode> createUnknownSchemaStringFormatExample(String schemaFormat) {
        return this.exampleJsonValueGenerator.createUnknownSchemaStringFormatExample(schemaFormat);
    }

    @Override
    public JsonNode createArrayExample(Optional<String> name, JsonNode arrayItem) {
        return this.exampleJsonValueGenerator.createArrayExample(name, arrayItem);
    }

    @Override
    public JsonNode createRaw(Object exampleValueString) {
        return this.exampleJsonValueGenerator.createRaw(exampleValueString);
    }

    @Override
    public JsonNode getExampleOrNull(Optional<String> fieldName, Schema schema, Object example) {
        return this.exampleJsonValueGenerator.getExampleOrNull(fieldName, schema, example);
    }
}
