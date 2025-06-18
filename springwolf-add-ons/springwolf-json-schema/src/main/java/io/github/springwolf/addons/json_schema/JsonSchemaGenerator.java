// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.json_schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class JsonSchemaGenerator {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Object fromSchema(ComponentSchema schema, Map<String, ComponentSchema> definitions)
            throws JsonProcessingException {
        ObjectNode node = fromSchemaInternal(schema, definitions, new HashSet<>());
        node.put("$schema", "https://json-schema.org/draft-04/schema#");

        return objectMapper.readValue(node.toString(), Object.class);
    }

    private ObjectNode fromSchemaInternal(
            ComponentSchema schema, Map<String, ComponentSchema> definitions, Set<ComponentSchema> visited) {
        if (schema != null && !visited.contains(schema)) {
            visited.add(schema);

            return mapToJsonSchema(schema, definitions, visited);
        }
        return objectMapper.createObjectNode();
    }

    private ObjectNode mapToJsonSchema(
            ComponentSchema componentSchema, Map<String, ComponentSchema> definitions, Set<ComponentSchema> visited) {
        ObjectNode node = objectMapper.createObjectNode();

        SchemaObject schema = getSchemaObject(componentSchema, definitions);
        if (schema == null) {
            return node;
        }

        if (schema.getAnyOf() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (ComponentSchema ofSchema : schema.getAnyOf()) {
                ComponentSchema resolvedSchemaObject = resolveSchemaRef(ofSchema, definitions);
                arrayNode.add(fromSchemaInternal(resolvedSchemaObject, definitions, visited));
            }
            node.set("anyOf", arrayNode);
        }
        if (schema.getAllOf() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (ComponentSchema allSchema : schema.getAllOf()) {
                ComponentSchema resolvedSchemaObject = resolveSchemaRef(allSchema, definitions);
                arrayNode.add(fromSchemaInternal(resolvedSchemaObject, definitions, visited));
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
            ComponentSchema resolvedSchemaObject = resolveSchemaRef(schema.getItems(), definitions);
            node.set("items", fromSchemaInternal(resolvedSchemaObject, definitions, visited));
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
            ComponentSchema resolvedSchemaObject = resolveSchemaRef(schema.getNot(), definitions);
            node.set("not", fromSchemaInternal(resolvedSchemaObject, definitions, visited));
        }
        if (schema.getOneOf() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (ComponentSchema ofSchema : schema.getOneOf()) {
                ComponentSchema resolvedSchemaObject = resolveSchemaRef(ofSchema, definitions);
                arrayNode.add(fromSchemaInternal(resolvedSchemaObject, definitions, visited));
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
            SchemaObject schema, Map<String, ComponentSchema> definitions, Set<ComponentSchema> visited) {
        ObjectNode node = objectMapper.createObjectNode();

        for (Map.Entry<String, Object> propertySchemaSet :
                schema.getProperties().entrySet()) {
            Object value = propertySchemaSet.getValue();
            if (value instanceof ComponentSchema componentSchema) {
                //            SchemaObject propertySchema = getSchemaObject(propertySchemaSet.getValue(), definitions);
                ObjectNode propertySchemaMapped = fromSchemaInternal(componentSchema, definitions, visited);
                node.set(propertySchemaSet.getKey(), propertySchemaMapped);
            }
        }

        return node;
    }


    private ComponentSchema resolveSchemaRef(ComponentSchema componentSchema, Map<String, ComponentSchema> definitions) {
        if (componentSchema == null || componentSchema.getReference() == null) {
            return componentSchema;
        }
        String schemaName = ReferenceUtil.getLastSegment(
                componentSchema.getReference().getRef());
        ComponentSchema cs = definitions.get(schemaName);
        return cs;
    }

    /**
     * tries to obtain the schema object of the given {@link ComponentSchema}, either the directly
     * set {@link SchemaObject} or the {@link SchemaObject} inside a {@link MultiFormatSchema} with schema format 'async_api'.
     * If componentSchema contains a {@link SchemaReference}, the method tries to resolve the target {@link ComponentSchema} and uses
     * this method recursively to resolve the schema object.
     *
     * @param componentSchema the {@link ComponentSchema} to get a {@link SchemaObject} from
     * @param definitions List of referenced {@link ComponentSchema}s
     * @return the found {@link SchemaObject} or null.
     */
    @Nullable
    private static SchemaObject getSchemaObject(
            ComponentSchema componentSchema, Map<String, ComponentSchema> definitions) {
        if (componentSchema == null) {
            return null;
        }

        if (componentSchema.getSchema() != null) {
            return componentSchema.getSchema();
        }
        if (componentSchema.getReference() != null) {
            if (componentSchema.getReference().getRef() != null) {
                String schemaName = ReferenceUtil.getLastSegment(
                        componentSchema.getReference().getRef());
                ComponentSchema cs = definitions.get(schemaName);
                // recursion, get schema inside CompoentSchema. Reference chains should finish with a concrete
                // SchemaObject
                return getSchemaObject(cs, definitions);
            }
            return componentSchema.getSchema();
        }
        return null;
    }
}
