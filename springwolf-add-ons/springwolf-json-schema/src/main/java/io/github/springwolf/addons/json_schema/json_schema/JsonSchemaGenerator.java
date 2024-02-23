// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.json_schema.json_schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class JsonSchemaGenerator {
    private final ObjectMapper objectMapper;

    public Object fromSchema(SchemaObject schema, Map<String, SchemaObject> definitions)
            throws JsonProcessingException {
        ObjectNode node = fromSchemaInternal(schema, definitions, new HashSet<>());
        node.put("$schema", "https://json-schema.org/draft-04/schema#");

        return objectMapper.readValue(node.toString(), Object.class);
    }

    private ObjectNode fromSchemaInternal(
            SchemaObject schema, Map<String, SchemaObject> definitions, Set<SchemaObject> visited) {
        if (schema != null && !visited.contains(schema)) {
            visited.add(schema);

            return mapToJsonSchema(schema, definitions, visited);
        }
        return objectMapper.createObjectNode();
    }

    private ObjectNode mapToJsonSchema(
            SchemaObject schema, Map<String, SchemaObject> definitions, Set<SchemaObject> visited) {
        ObjectNode node = objectMapper.createObjectNode();

        if (schema.getAnyOf() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (ComponentSchema ofSchema : schema.getAnyOf()) {
                SchemaObject schemaObject = getSchemaObject(ofSchema, definitions);
                arrayNode.add(fromSchemaInternal(schemaObject, definitions, visited));
            }
            node.set("anyOf", arrayNode);
        }
        if (schema.getAllOf() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (ComponentSchema allSchema : schema.getAllOf()) {
                SchemaObject schemaObject = getSchemaObject(allSchema, definitions);
                arrayNode.add(fromSchemaInternal(schemaObject, definitions, visited));
            }
            node.set("allOf", arrayNode);
        }
        if (schema.getConstValue() != null) {
            node.put("const", schema.getConstValue().toString());
        }
        if (schema.getDescription() != null) {
            node.put("description", schema.getDescription());
        }
        if (schema.getEnumValues() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (Object property : schema.getEnumValues()) {
                arrayNode.add(property.toString());
            }
            node.set("enum", arrayNode);
        }
        if (schema.getExclusiveMinimum() != null) {
            node.put("exclusiveMinimum", schema.getExclusiveMinimum());
        }
        if (schema.getExclusiveMaximum() != null) {
            node.put("exclusiveMaximum", schema.getExclusiveMaximum());
        }
        if (schema.getFormat() != null) {
            node.put("format", schema.getFormat());
        }
        if (schema.getItems() != null) {
            SchemaObject schemaObject = getSchemaObject(schema.getItems(), definitions);
            node.set("items", fromSchemaInternal(schemaObject, definitions, visited));
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
        if (schema.getNot() != null) {
            SchemaObject schemaObject = getSchemaObject(schema.getNot(), definitions);
            node.set("not", fromSchemaInternal(schemaObject, definitions, visited));
        }
        if (schema.getOneOf() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (ComponentSchema ofSchema : schema.getOneOf()) {
                SchemaObject schemaObject = getSchemaObject(ofSchema, definitions);
                arrayNode.add(fromSchemaInternal(schemaObject, definitions, visited));
            }
            node.set("oneOf", arrayNode);
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
            node.put("type", schema.getType());
        }
        if (schema.getUniqueItems() != null) {
            node.put("uniqueItems", schema.getUniqueItems());
        }

        return node;
    }

    private JsonNode buildProperties(
            SchemaObject schema, Map<String, SchemaObject> definitions, Set<SchemaObject> visited) {
        ObjectNode node = objectMapper.createObjectNode();

        for (Map.Entry<String, Object> propertySchemaSet :
                schema.getProperties().entrySet()) {
            SchemaObject propertySchema = getSchemaObject(propertySchemaSet.getValue(), definitions);
            ObjectNode propertySchemaMapped = fromSchemaInternal(propertySchema, definitions, visited);
            node.set(propertySchemaSet.getKey(), propertySchemaMapped);
        }

        return node;
    }

    private static SchemaObject getSchemaObject(Object schema, Map<String, SchemaObject> definitions) {
        if (schema instanceof SchemaObject) {
            return (SchemaObject) schema;
        } else if (schema instanceof ComponentSchema) {
            ComponentSchema componentSchema = (ComponentSchema) schema;
            if (componentSchema.getReference() != null
                    && componentSchema.getReference().getRef() != null) {
                String schemaName = StringUtils.substringAfterLast(
                        componentSchema.getReference().getRef(), "/");
                return definitions.get(schemaName);
            }
            return componentSchema.getSchema();
        }
        return null;
    }
}
