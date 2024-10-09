// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import com.fasterxml.jackson.databind.JavaType;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.annotations.AsyncApiPayload;
import io.github.springwolf.core.asyncapi.components.postprocessors.SchemasPostProcessor;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.core.jackson.TypeNameResolver;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.PrimitiveType;
import io.swagger.v3.core.util.RefUtils;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public record ExtractedSchemas(ComponentSchema rootSchema, Map<String, SchemaObject> referencedSchemas) {}

    public SchemaObject extractSchema(SchemaObject headers) {
        String schemaName = headers.getTitle();

        ObjectSchema headerSchema = new ObjectSchema();
        headerSchema.setName(schemaName);
        headerSchema.setTitle(headers.getTitle());
        headerSchema.setDescription(headers.getDescription());
        Map<String, Schema> properties = headers.getProperties().entrySet().stream()
                .map((property) -> Map.entry(property.getKey(), (Schema<?>)
                        swaggerSchemaUtil.mapToSwagger((SchemaObject) property.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        headerSchema.setProperties(properties);

        Map<String, Schema> newSchemasToProcess = Map.of(schemaName, headerSchema);
        postProcessSchemas(newSchemasToProcess, new HashMap<>(newSchemasToProcess), DEFAULT_CONTENT_TYPE);

        return swaggerSchemaUtil.mapSchema(headerSchema);
    }

    public ExtractedSchemas extractSchema(Class<?> type) {
        return this.resolveSchema(type, "");
    }

    public ExtractedSchemas resolveSchema(Type type, String contentType) {
        String actualContentType =
                StringUtils.isBlank(contentType) ? properties.getDocket().getDefaultContentType() : contentType;
        ResolvedSchema resolvedSchema = runWithFqnSetting(
                (unused) -> converter.resolveAsResolvedSchema(new AnnotatedType(type).resolveAsRef(true)));

        if (resolvedSchema == null) {
            // defaulting to stringSchema when resolvedSchema is null
            SchemaObject payloadSchema = swaggerSchemaUtil.mapSchema(
                    PrimitiveType.fromType(String.class).createProperty());
            return new ExtractedSchemas(ComponentSchema.of(payloadSchema), Map.of());
        } else {
            Map<String, Schema> newSchemasToProcess = new LinkedHashMap<>(resolvedSchema.referencedSchemas);
            newSchemasToProcess.putIfAbsent(getNameFromType(type), resolvedSchema.schema);

            preProcessSchemas(resolvedSchema.schema, newSchemasToProcess, type);
            HashMap<String, Schema> processedSchemas = new HashMap<>(newSchemasToProcess);
            postProcessSchemas(newSchemasToProcess, processedSchemas, actualContentType);

            return new ExtractedSchemas(
                    swaggerSchemaUtil.mapSchemaOrRef(resolvedSchema.schema),
                    swaggerSchemaUtil.mapSchemasMap(processedSchemas));
        }
    }

    private void preProcessSchemas(Schema payloadSchema, Map<String, Schema> schemas, Type type) {
        processCommonModelConverters(payloadSchema, schemas);
        processAsyncApiPayloadAnnotation(schemas, type);
        processSchemaAnnotation(payloadSchema, type);
    }

    private void processCommonModelConverters(Schema payloadSchema, Map<String, Schema> schemas) {
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

                        adaptPayloadSchema(payloadSchema, targetSchemaName, sourceSchemaName);
                    }
                });
    }

    private void adaptPayloadSchema(Schema schema, String targetSchemaName, String sourceSchemaName) {
        if (schema != null && schema.get$ref() != null) {
            String refTypeName = StringUtils.substringAfterLast(schema.get$ref(), "/");
            if (refTypeName.equals(sourceSchemaName)) {
                schema.$ref(RefUtils.constructRef(targetSchemaName));
            }
        }
    }

    private void processSchemaAnnotation(Schema payloadSchema, Type type) {
        JavaType javaType = Json.mapper().constructType(type);
        Class<?> clazz = javaType.getRawClass();
        if (payloadSchema != null) {
            var schemaAnnotation = clazz.getAnnotation(io.swagger.v3.oas.annotations.media.Schema.class);
            if (schemaAnnotation != null && StringUtils.isNotBlank(schemaAnnotation.description())) {
                payloadSchema.setDescription(schemaAnnotation.description());
            }
        }
    }

    private void processAsyncApiPayloadAnnotation(Map<String, Schema> schemas, Type type) {
        JavaType javaType = Json.mapper().constructType(type);
        Class<?> clazz = javaType.getRawClass();
        List<Field> withPayloadAnnotatedFields = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(AsyncApiPayload.class))
                .toList();

        if (withPayloadAnnotatedFields.size() == 1) {
            String schemaName = getNameFromType(type);
            String fieldName = withPayloadAnnotatedFields.get(0).getName();
            schemas.entrySet().stream()
                    .filter(e -> e.getKey().equals(schemaName))
                    .filter(e -> e.getValue() != null)
                    .filter(e -> e.getValue().getProperties() != null)
                    .forEach(e ->
                            e.setValue((Schema<?>) e.getValue().getProperties().get(fieldName)));
        } else if (withPayloadAnnotatedFields.size() > 1) {
            log.warn(
                    ("Found more than one field with @AsyncApiPayload annotation in class {}. "
                            + "Falling back and ignoring annotation."),
                    type.getTypeName());
        }
    }

    private <R> R runWithFqnSetting(Function<Void, R> callable) {
        boolean previousUseFqn = TypeNameResolver.std.getUseFqn();
        TypeNameResolver.std.setUseFqn(properties.isUseFqn());

        R result = callable.apply(null);

        TypeNameResolver.std.setUseFqn(previousUseFqn);
        return result;
    }

    public String getNameFromType(Type type) {
        PrimitiveType primitiveType = PrimitiveType.fromType(type);
        if (primitiveType != null && properties.isUseFqn()) {
            return primitiveType.getKeyClass().getName();
        }
        JavaType javaType = Json.mapper().constructType(type);
        return runWithFqnSetting((unused) -> TypeNameResolver.std.nameForType(javaType));
    }

    public String getSimpleNameFromType(Type type) {
        JavaType javaType = Json.mapper().constructType(type);
        TypeNameResolver.std.setUseFqn(false);
        String name = TypeNameResolver.std.nameForType(javaType);
        TypeNameResolver.std.setUseFqn(properties.isUseFqn());
        return name;
    }

    private void postProcessSchemas(
            Map<String, Schema> schemasToProcess, Map<String, Schema> schemas, String contentType) {
        boolean schemasHadEntries = !schemas.isEmpty();
        for (Schema schema : schemasToProcess.values()) {
            for (SchemasPostProcessor processor : schemaPostProcessors) {
                processor.process(schema, schemas, contentType);

                if (schemasHadEntries && !schemas.containsValue(schema)) {
                    // If the post-processor removed the schema, we can stop processing
                    break;
                }
            }
        }
    }
}
