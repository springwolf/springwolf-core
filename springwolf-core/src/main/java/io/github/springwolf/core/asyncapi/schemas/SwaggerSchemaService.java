// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import com.fasterxml.jackson.databind.JavaType;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaFormat;
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
    private final ModelConverters converter_openapi30 = ModelConverters.getInstance(false);
    private final ModelConverters converter_openapi31 = ModelConverters.getInstance(true);

    private final List<SchemasPostProcessor> schemaPostProcessors;
    private final SwaggerSchemaUtil swaggerSchemaUtil;
    private final SpringwolfConfigProperties properties;

    public SwaggerSchemaService(
            List<ModelConverter> externalModelConverters,
            List<SchemasPostProcessor> schemaPostProcessors,
            SwaggerSchemaUtil swaggerSchemaUtil,
            SpringwolfConfigProperties properties) {

        externalModelConverters.forEach(converter_openapi30::addConverter);
        externalModelConverters.forEach(converter_openapi31::addConverter);
        this.schemaPostProcessors = schemaPostProcessors;
        this.swaggerSchemaUtil = swaggerSchemaUtil;
        this.properties = properties;
    }

    public record ExtractedSchemas(ComponentSchema rootSchema, Map<String, ComponentSchema> referencedSchemas) {}

    /**
     * Invokes all postprocessors for the given schema (headers). As the {@link SchemasPostProcessor} interface processes
     * *Swagger* schemas, this method converts the given {@link SchemaObject} to a Swagger schema, invokes all
     * postprocessors and converts the result back to a {@link SchemaObject}
     * <p>NOTE</p>
     * The conversion between the AsyncApi {@link SchemaObject} and Swagger schema instance is not a 'full conversion'. Only
     * root attributes of the schema and the first level of properties are converted. Providing {@link SchemaObject}s with deep
     * property hierarchy will result in a corrupted result.
     * <br/>
     * A typical usecase for this method is postprocessing of header schemas, which have typically a simple structure.
     *
     * @param schemaWithoutRef
     * @return
     */
    public ComponentSchema postProcessSchemaWithoutRef(SchemaObject schemaWithoutRef) {
        String schemaName = schemaWithoutRef.getTitle();

        // create a swagger schema to invoke the postprocessors. Copy attributes vom headers to (Swagger) headerSchema
        ObjectSchema headerSchema = new ObjectSchema();
        headerSchema.setName(schemaName);
        headerSchema.setTitle(schemaWithoutRef.getTitle());
        headerSchema.setDescription(schemaWithoutRef.getDescription());

        // transform properties of headers to a properties Map of Swagger schemas.
        // (Only one level, no deep transformation, see SwaggerSchemaUtil#mapToSwagger)
        //
        Map<String, Schema> properties = schemaWithoutRef.getProperties().entrySet().stream()
                .map((property) ->
                        Map.entry(property.getKey(), (Schema<?>) swaggerSchemaUtil.mapToSwagger(property.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        headerSchema.setProperties(properties);

        // call postprocessors
        Map<String, Schema> newSchemasToProcess = Map.of(schemaName, headerSchema);
        postProcessSchemas(newSchemasToProcess, new HashMap<>(newSchemasToProcess), DEFAULT_CONTENT_TYPE);

        // convert Swagger schema back to an AsnycApi SchemaObject
        return swaggerSchemaUtil.mapSchema(headerSchema, SchemaFormat.ASYNCAPI_V3);
    }

    public ExtractedSchemas postProcessSimpleSchema(Class<?> type) {
        return this.resolveSchema(type, "", SchemaFormat.ASYNCAPI_V3);
    }

    /**
     * creates a {@link ExtractedSchemas} from the given type. The resulting ExtractedSchemas will contain the root
     * schema (which is always a schema ref) and a List of conrecte schemas referenced from the root schema.
     *
     * @param type
     * @param contentType
     * @param schemaFormat SchemaFormat to use
     * @return
     */
    public ExtractedSchemas resolveSchema(Type type, String contentType, SchemaFormat schemaFormat) {
        String actualContentType =
                StringUtils.isBlank(contentType) ? properties.getDocket().getDefaultContentType() : contentType;

        // use swagger to resolve type to a swagger ResolvedSchema Object.
        ModelConverters converterToUse =
                switch (schemaFormat) {
                    case ASYNCAPI_V3 -> converter_openapi30;
                    case OPENAPI_V3 -> converter_openapi30;
                    case OPENAPI_V3_1 -> converter_openapi31;
                    default -> throw new IllegalArgumentException("SchemaFormat not supported: " + schemaFormat);
                };

        ResolvedSchema resolvedSchema = runWithFqnSetting(
                (unused) -> converterToUse.resolveAsResolvedSchema(new AnnotatedType(type).resolveAsRef(true)));

        if (resolvedSchema == null) {
            // defaulting to stringSchema when resolvedSchema is null
            Schema<?> stringPropertySchema =
                    PrimitiveType.fromType(String.class).createProperty();
            ComponentSchema stringComponentSchema = swaggerSchemaUtil.mapSchema(stringPropertySchema, schemaFormat);
            return new ExtractedSchemas(stringComponentSchema, Map.of());
        } else {
            Map<String, Schema> newSchemasToProcess = new LinkedHashMap<>(resolvedSchema.referencedSchemas);
            newSchemasToProcess.putIfAbsent(getNameFromType(type), resolvedSchema.schema);

            preProcessSchemas(resolvedSchema.schema, newSchemasToProcess, type);
            HashMap<String, Schema> processedSchemas = new HashMap<>(newSchemasToProcess);
            postProcessSchemas(newSchemasToProcess, processedSchemas, actualContentType);

            return createExtractedSchemas(resolvedSchema.schema, processedSchemas, schemaFormat);
        }
    }

    /**
     * creates an {@link ExtractedSchemas} return type for the given swagger root schema and swagger referenced schemas.
     *
     * @param rootSchema        Swagger root schema
     * @param referencedSchemas all referenced swagger schemas
     * @param schemaFormat      the schema-format of the schemas inside the resulting ExtractedSchemas
     * @return
     */
    private ExtractedSchemas createExtractedSchemas(
            Schema rootSchema, Map<String, Schema> referencedSchemas, SchemaFormat schemaFormat) {
        ComponentSchema rootComponentSchema = swaggerSchemaUtil.mapSchemaOrRef(rootSchema, schemaFormat);
        Map<String, ComponentSchema> referencedComponentSchemas =
                swaggerSchemaUtil.mapSchemasMap(referencedSchemas, schemaFormat);

        return new ExtractedSchemas(rootComponentSchema, referencedComponentSchemas);
    }

    private void preProcessSchemas(Schema payloadSchema, Map<String, Schema> schemas, Type type) {
        processCommonModelConverters(payloadSchema, schemas);
        processAsyncApiPayloadAnnotation(schemas, type);
        processSchemaAnnotation(payloadSchema, type);
    }

    /**
     * When (springwolf common) model converters are used,
     * the native schema is {@param payloadSchema} and the target, converted to schema is part of {@param schemas}.
     * <p>
     * This method will remove the native and puts the target, converted schema in place.
     */
    private void processCommonModelConverters(Schema payloadSchema, Map<String, Schema> schemas) {
        schemas.values().stream()
                .filter(schema -> schema.getType() == null)
                .filter(schema -> schema.get$ref() != null)
                .toList()
                .forEach(schema -> {
                    String targetSchemaName = schema.getName();
                    String sourceSchemaName = ReferenceUtil.getLastSegment(schema.get$ref());

                    Schema<?> actualSchema = schemas.get(sourceSchemaName);

                    if (actualSchema != null) {
                        schemas.put(targetSchemaName, actualSchema);
                        schemas.remove(sourceSchemaName);

                        updatePayloadSchemaRef(payloadSchema, targetSchemaName, sourceSchemaName);
                    }
                });
    }

    private void updatePayloadSchemaRef(Schema schema, String targetSchemaName, String sourceSchemaName) {
        if (schema != null && schema.get$ref() != null) {
            String refTypeName = ReferenceUtil.getLastSegment(schema.get$ref());
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
