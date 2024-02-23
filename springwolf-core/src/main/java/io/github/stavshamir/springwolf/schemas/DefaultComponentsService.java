// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.AsyncApiPayload;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.postprocessor.SchemasPostProcessor;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.TypeNameResolver;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties.ConfigDocket.DEFAULT_CONTENT_TYPE;

@Slf4j
public class DefaultComponentsService implements ComponentsService {

    private final ModelConverters converter = ModelConverters.getInstance();
    private final List<SchemasPostProcessor> schemaPostProcessors;
    private final SwaggerSchemaUtil swaggerSchemaUtil;
    private final SpringwolfConfigProperties properties;

    private final Map<String, Schema> schemas = new HashMap<>();
    private final Map<String, Message> messages = new HashMap<>();

    public DefaultComponentsService(
            List<ModelConverter> externalModelConverters,
            List<SchemasPostProcessor> schemaPostProcessors,
            SwaggerSchemaUtil swaggerSchemaUtil,
            SpringwolfConfigProperties properties) {

        externalModelConverters.forEach(converter::addConverter);
        this.schemaPostProcessors = schemaPostProcessors;
        this.swaggerSchemaUtil = swaggerSchemaUtil;
        this.properties = properties;
    }

    @Override
    public Map<String, SchemaObject> getSchemas() {
        return schemas.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), swaggerSchemaUtil.mapSchema(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public String registerSchema(AsyncHeaders headers) {
        log.debug("Registering schema for {}", headers.getSchemaName());

        MapSchema headerSchema = new MapSchema();
        headerSchema.setName(headers.getSchemaName());
        headerSchema.properties(headers);

        this.schemas.put(headers.getSchemaName(), headerSchema);
        postProcessSchema(headerSchema, DEFAULT_CONTENT_TYPE);

        return headers.getSchemaName();
    }

    @Override
    public String registerSchema(Class<?> type) {
        return this.registerSchema(type, properties.getDocket().getDefaultContentType());
    }

    @Override
    public String registerSchema(Class<?> type, String contentType) {
        log.debug("Registering schema for {}", type.getSimpleName());
        String actualContentType =
                StringUtils.isBlank(contentType) ? properties.getDocket().getDefaultContentType() : contentType;

        Map<String, Schema> schemas = new LinkedHashMap<>(runWithFqnSetting((unused) -> converter.readAll(type)));

        String schemaName = getSchemaName(type, schemas);

        preProcessSchemas(schemas, schemaName, type);
        schemas.forEach(this.schemas::putIfAbsent);
        schemas.values().forEach(schema -> postProcessSchema(schema, actualContentType));

        return schemaName;
    }

    @Override
    public Map<String, Message> getMessages() {
        return this.messages;
    }

    @Override
    public MessageReference registerMessage(MessageObject message) {
        log.debug("Registering message for {}", message.getName());

        messages.putIfAbsent(message.getName(), message);

        return MessageReference.toComponentMessage(message);
    }

    private String getSchemaName(Class<?> type, Map<String, Schema> schemas) {
        if (schemas.isEmpty() && type.equals(String.class)) {
            return registerString();
        }

        if (schemas.size() == 1) {
            return new ArrayList<>(schemas.keySet()).get(0);
        }

        Set<String> resolvedPayloadModelName =
                runWithFqnSetting((unused) -> converter.read(type).keySet());
        if (!resolvedPayloadModelName.isEmpty()) {
            return new ArrayList<>(resolvedPayloadModelName).get(0);
        }

        return type.getSimpleName();
    }

    private void preProcessSchemas(Map<String, Schema> schemas, String schemaName, Class<?> type) {
        processAsyncApiPayloadAnnotation(schemas, schemaName, type);
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
            log.warn(("Found more than one field with @AsyncApiPayload annotation in class %s. "
                            + "Falling back and ignoring annotation.")
                    .formatted(type.getName()));
        }
    }

    private String registerString() {
        String schemaName = "String";
        StringSchema schema = new StringSchema();
        schema.setName(String.class.getName());

        this.schemas.put(schemaName, schema);
        postProcessSchema(schema, DEFAULT_CONTENT_TYPE);

        return schemaName;
    }

    private <R> R runWithFqnSetting(Function<Void, R> callable) {
        boolean previousUseFqn = TypeNameResolver.std.getUseFqn();
        if (properties.isUseFqn()) {
            TypeNameResolver.std.setUseFqn(true);
        }

        R result = callable.apply(null);

        if (properties.isUseFqn()) {
            TypeNameResolver.std.setUseFqn(previousUseFqn);
        }
        return result;
    }

    private void postProcessSchema(Schema schema, String contentType) {
        for (SchemasPostProcessor processor : schemaPostProcessors) {
            processor.process(schema, schemas, contentType);

            if (!schemas.containsValue(schema)) {
                break;
            }
        }
    }
}
