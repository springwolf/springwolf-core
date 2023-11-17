// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.addons.json_schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class JsonSchemaGenerator {
    private final ObjectMapper objectMapper;

    public Object fromSchema(Schema<?> schema, Map<String, Schema> definitions) throws JsonProcessingException {
        ObjectNode node = fromSchemaInternal(schema, definitions, new HashSet<>());
        node.put("$schema", "https://json-schema.org/draft-04/schema#");

        return objectMapper.readValue(node.toString(), Object.class);
    }

    private ObjectNode fromSchemaInternal(Schema<?> schema, Map<String, Schema> definitions, Set<Schema> visited) {
        if (schema != null && !visited.contains(schema)) {
            visited.add(schema);

            return mapToJsonSchema(schema, definitions, visited);
        }
        return objectMapper.createObjectNode();
    }

    private ObjectNode mapToJsonSchema(Schema<?> schema, Map<String, Schema> definitions, Set<Schema> visited) {
        ObjectNode node = objectMapper.createObjectNode();

        if (schema.getAnyOf() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (Schema ofSchema : schema.getAnyOf()) {
                arrayNode.add(fromSchemaInternal(ofSchema, definitions, visited));
            }
            node.put("anyOf", arrayNode);
        }
        if (schema.getAllOf() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (Schema ofSchema : schema.getAllOf()) {
                arrayNode.add(fromSchemaInternal(ofSchema, definitions, visited));
            }
            node.put("allOf", arrayNode);
        }
        if (schema.getConst() != null) {
            node.put("const", schema.getConst().toString());
        }
        if (schema.getDescription() != null) {
            node.put("description", schema.getDescription());
        }
        if (schema.getEnum() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (Object property : schema.getEnum()) {
                arrayNode.add(property.toString());
            }
            if (schema.getNullable() != null && schema.getNullable()) {
                arrayNode.add("null");
            }
            node.set("enum", arrayNode);
        }
        if (schema.getFormat() != null) {
            node.put("format", schema.getFormat());
        }
        if (schema.getItems() != null) {
            node.set("items", fromSchemaInternal(schema.getItems(), definitions, visited));
        }
        if (schema.getMaximum() != null) {
            node.put("maximum", schema.getMaximum());
        }
        if (schema.getMinimum() != null) {
            node.put("minimum", schema.getMinimum());
        }
        if (schema.getMaxItems() != null) {
            node.put("maxItems", schema.getMaxItems());
        }
        if (schema.getMinItems() != null) {
            node.put("minItems", schema.getMinItems());
        }
        if (schema.getMaxLength() != null) {
            node.put("maxLength", schema.getMaxLength());
        }
        if (schema.getMinLength() != null) {
            node.put("minLength", schema.getMinLength());
        }
        if (schema.getMultipleOf() != null) {
            node.put("multipleOf", schema.getMultipleOf());
        }
        if (schema.getName() != null) {
            node.put("name", schema.getName());
        }
        if (schema.getNot() != null) {
            node.put("not", fromSchemaInternal(schema.getNot(), definitions, visited));
        }
        if (schema.getOneOf() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (Schema ofSchema : schema.getOneOf()) {
                arrayNode.add(fromSchemaInternal(ofSchema, definitions, visited));
            }
            node.put("oneOf", arrayNode);
        }
        if (schema.getPattern() != null) {
            node.put("pattern", schema.getPattern());
        }
        if (schema.getProperties() != null && !schema.getProperties().isEmpty()) {
            node.set("properties", buildProperties(schema, definitions, visited));
        }
        if (schema.getRequired() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (String property : schema.getRequired()) {
                arrayNode.add(property);
            }
            node.set("required", arrayNode);
        }
        if (schema.getTitle() != null) {
            node.put("title", schema.getTitle());
        }
        if (schema.getType() != null) {
            if (schema.getNullable() != null && schema.getNullable()) {
                ArrayNode arrayNode = objectMapper.createArrayNode();
                arrayNode.add(schema.getType());
                arrayNode.add("null");
                node.set("type", arrayNode);
            } else {
                node.put("type", schema.getType());
            }
        }
        if (schema.getUniqueItems() != null) {
            node.put("uniqueItems", schema.getUniqueItems());
        }

        return node;
    }

    private JsonNode buildProperties(Schema<?> schema, Map<String, Schema> definitions, Set<Schema> visited) {
        ObjectNode node = objectMapper.createObjectNode();

        for (Map.Entry<String, Schema> propertySchemaSet :
                schema.getProperties().entrySet()) {
            Schema propertySchema = propertySchemaSet.getValue();

            if (propertySchema != null && propertySchema.get$ref() != null) {
                String schemaName = StringUtils.substringAfterLast(propertySchema.get$ref(), "/");
                propertySchema = definitions.get(schemaName);
            }

            node.set(propertySchemaSet.getKey(), fromSchemaInternal(propertySchema, definitions, visited));
        }

        return node;
    }
}
