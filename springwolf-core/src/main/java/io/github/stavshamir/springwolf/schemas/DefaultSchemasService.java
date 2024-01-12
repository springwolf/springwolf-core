// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.postprocessor.SchemasPostProcessor;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.TypeNameResolver;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Slf4j
public class DefaultSchemasService implements SchemasService {

    private final ModelConverters converter = ModelConverters.getInstance();
    private final List<SchemasPostProcessor> schemaPostProcessors;
    private final SpringwolfConfigProperties properties;

    private final Map<String, Schema> definitions = new HashMap<>();

    public DefaultSchemasService(
            List<ModelConverter> externalModelConverters,
            List<SchemasPostProcessor> schemaPostProcessors,
            SpringwolfConfigProperties properties) {

        externalModelConverters.forEach(converter::addConverter);
        this.schemaPostProcessors = schemaPostProcessors;
        this.properties = properties;
    }

    @Override
    public Map<String, Schema> getDefinitions() {
        return definitions;
    }

    @Override
    public String register(AsyncHeaders headers) {
        log.debug("Registering schema for {}", headers.getSchemaName());

        MapSchema headerSchema = new MapSchema();
        headerSchema.setName(headers.getSchemaName());
        headerSchema.properties(headers);

        this.definitions.put(headers.getSchemaName(), headerSchema);
        postProcessSchema(headerSchema);

        return headers.getSchemaName();
    }

    @Override
    public String register(Class<?> type) {
        log.debug("Registering schema for {}", type.getSimpleName());

        Map<String, Schema> schemas = runWithFqnSetting((unused) -> converter.readAll(type));

        this.definitions.putAll(schemas);
        schemas.values().forEach(this::postProcessSchema);

        if (schemas.isEmpty() && type.equals(String.class)) {
            return registerString();
        }

        if (schemas.size() == 1) {
            return new ArrayList<>(schemas.keySet()).get(0);
        }

        Set<String> resolvedPayloadModelName = converter.read(type).keySet();
        if (!resolvedPayloadModelName.isEmpty()) {
            return new ArrayList<>(resolvedPayloadModelName).get(0);
        }

        return type.getSimpleName();
    }

    private String registerString() {
        String schemaName = "String";
        StringSchema schema = new StringSchema();

        this.definitions.put(schemaName, schema);
        postProcessSchema(schema);

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

    private void postProcessSchema(Schema schema) {
        schemaPostProcessors.forEach(processor -> processor.process(schema, definitions));
    }
}
