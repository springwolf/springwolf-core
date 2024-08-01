// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.annotations.AsyncApiPayload;
import io.github.springwolf.core.asyncapi.components.postprocessors.SchemasPostProcessor;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.TypeNameResolver;
import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties.ConfigDocket.DEFAULT_CONTENT_TYPE;

@Slf4j
public class SwaggerSchemaService {
    private final ModelConverters converter = ModelConverters.getInstance();
    private final List<SchemasPostProcessor> schemaPostProcessors;
    private final SwaggerSchemaUtil swaggerSchemaUtil;
    private final SpringwolfConfigProperties properties;

    public SwaggerSchemaService(
            List<ModelConverter> externalModelConverters,
            List<SchemasPostProcessor> schemaPostProcessors,
            SwaggerSchemaUtil swaggerSchemaUtil,
            SpringwolfConfigProperties properties) {

        externalModelConverters.forEach(converter::addConverter);
        this.schemaPostProcessors = schemaPostProcessors;
        this.swaggerSchemaUtil = swaggerSchemaUtil;
        this.properties = properties;
    }

    public record ExtractedSchemas(String rootSchemaName, Map<String, SchemaObject> schemas) {
        public SchemaObject getRootSchema() {
            return schemas.get(rootSchemaName);
        }
    }

    public SchemaObject extractSchema(SchemaObject headers) {
        String schemaName = headers.getTitle();

        ObjectSchema headerSchema = new ObjectSchema();
        headerSchema.setName(schemaName);
        headerSchema.setDescription(headers.getDescription());
        Map<String, Schema> properties = headers.getProperties().entrySet().stream()
                .map((property) -> Map.entry(property.getKey(), (Schema<?>)
                        swaggerSchemaUtil.mapToSwagger((SchemaObject) property.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        headerSchema.setProperties(properties);

        postProcessSchema(headerSchema, new HashMap<>(Map.of(schemaName, headerSchema)), DEFAULT_CONTENT_TYPE);

        return swaggerSchemaUtil.mapSchema(headerSchema);
    }

    public ExtractedSchemas extractSchema(Class<?> type) {
        return this.extractSchema(type, "");
    }

    public ExtractedSchemas extractSchema(Class<?> type, String contentType) {
        String actualContentType =
                StringUtils.isBlank(contentType) ? properties.getDocket().getDefaultContentType() : contentType;

        Map<String, Schema> swaggerSchemas =
                new LinkedHashMap<>(runWithFqnSetting((unused) -> converter.readAll(type)));

        String schemaName = getSchemaName(type, swaggerSchemas);
        preProcessSchemas(swaggerSchemas, schemaName, type);

        Map<String, Schema> postProcessedSchemas = new HashMap<>(swaggerSchemas);
        for (Schema schema : swaggerSchemas.values()) {
            postProcessSchema(schema, postProcessedSchemas, actualContentType);
        }

        Map<String, SchemaObject> schemas = postProcessedSchemas.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), swaggerSchemaUtil.mapSchema(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new ExtractedSchemas(schemaName, schemas);
    }

    private String getSchemaName(Class<?> type, Map<String, Schema> schemas) {
        if (schemas.isEmpty()) {
            // swagger-parser does not create schemas for primitives
            if (type.equals(String.class) || type.equals(Character.class) || type.equals(Byte.class)) {
                return registerPrimitive(String.class, new StringSchema(), schemas);
            }
            if (Boolean.class.isAssignableFrom(type)) {
                return registerPrimitive(Boolean.class, new BooleanSchema(), schemas);
            }
            if (Number.class.isAssignableFrom(type)) {
                return registerPrimitive(Number.class, new NumberSchema(), schemas);
            }
            if (Object.class.isAssignableFrom(type)) {
                return registerPrimitive(Object.class, new ObjectSchema(), schemas);
            }
        }

        if (schemas.size() == 1) {
            return schemas.keySet().stream().findFirst().get();
        }

        Set<String> resolvedPayloadModelName =
                runWithFqnSetting((unused) -> converter.read(type).keySet());
        if (!resolvedPayloadModelName.isEmpty()) {
            return resolvedPayloadModelName.stream().findFirst().get();
        }

        return getNameFromClass(type);
    }

    private void preProcessSchemas(Map<String, Schema> schemas, String schemaName, Class<?> type) {
        processCommonModelConverters(schemas);
        processAsyncApiPayloadAnnotation(schemas, schemaName, type);
        processSchemaAnnotation(schemas, schemaName, type);
    }

    private void processCommonModelConverters(Map<String, Schema> schemas) {
        schemas.values().stream()
                .filter(schema -> schema.getType() == null)
                .filter(schema -> schema.get$ref() != null)
                .forEach(schema -> {
                    String targetSchemaName = schema.getName();
                    String sourceSchemaName = StringUtils.substringAfterLast(schema.get$ref(), "/");

                    Schema<?> actualSchema = schemas.get(sourceSchemaName);

                    if (actualSchema != null) {
                        schemas.put(targetSchemaName, actualSchema);
                        schemas.remove(sourceSchemaName);
                    }
                });
    }

    private void processSchemaAnnotation(Map<String, Schema> schemas, String schemaName, Class<?> type) {
        Schema schemaForType = schemas.get(schemaName);
        if (schemaForType != null) {
            var schemaAnnotation = type.getAnnotation(io.swagger.v3.oas.annotations.media.Schema.class);
            if (schemaAnnotation != null) {
                String description = schemaAnnotation.description();
                if (StringUtils.isNotBlank(description)) {
                    schemaForType.setDescription(description);
                }
            }
        }
    }

    private void processAsyncApiPayloadAnnotation(Map<String, Schema> schemas, String schemaName, Class<?> type) {
        List<Field> withPayloadAnnotatedFields = Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(AsyncApiPayload.class))
                .toList();

        if (withPayloadAnnotatedFields.size() == 1) {
            Schema envelopSchema = schemas.get(schemaName);
            if (envelopSchema != null && envelopSchema.getProperties() != null) {
                String fieldName = withPayloadAnnotatedFields.get(0).getName();
                Schema actualSchema = (Schema) envelopSchema.getProperties().get(fieldName);
                if (actualSchema != null) {
                    schemas.put(schemaName, actualSchema);
                }
            }

        } else if (withPayloadAnnotatedFields.size() > 1) {
            log.warn(
                    ("Found more than one field with @AsyncApiPayload annotation in class {}. "
                            + "Falling back and ignoring annotation."),
                    type.getName());
        }
    }

    private String registerPrimitive(Class<?> type, Schema schema, Map<String, Schema> schemas) {
        String schemaName = getNameFromClass(type);
        schema.setName(schemaName);

        schemas.put(schemaName, schema);
        postProcessSchema(schema, schemas, DEFAULT_CONTENT_TYPE);

        return schemaName;
    }

    private <R> R runWithFqnSetting(Function<Void, R> callable) {
        boolean previousUseFqn = TypeNameResolver.std.getUseFqn();
        TypeNameResolver.std.setUseFqn(properties.isUseFqn());

        R result = callable.apply(null);

        TypeNameResolver.std.setUseFqn(previousUseFqn);
        return result;
    }

    private String getNameFromClass(Class<?> type) {
        if (properties.isUseFqn()) {
            return type.getName();
        }
        return type.getSimpleName();
    }

    private void postProcessSchema(Schema schema, Map<String, Schema> schemas, String contentType) {
        for (SchemasPostProcessor processor : schemaPostProcessors) {
            processor.process(schema, schemas, contentType);

            if (!schemas.containsValue(schema)) {
                break;
            }
        }
    }
}
