// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.controller.dtos.MessageDto;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Used in plugins with publishing enabled.
 * Located in springwolf-core to allow sharing of code
 */
@RequiredArgsConstructor
@Slf4j
public class PublishingPayloadCreator {

    private final ComponentsService componentsService;
    private final ObjectMapper jsonMapper;

    public Result createPayloadObject(MessageDto message) {
        String messageType = message.getType();

        if (message.getPayload() == null || Objects.equals(MessageDto.EMPTY, message.getPayload())) {
            return new Result(null, Optional.empty());
        }

        Map<String, ComponentSchema> knownSchemas = componentsService.getSchemas();
        Set<String> knownSchemaNames = knownSchemas.keySet();
        for (Map.Entry<String, ComponentSchema> schemaEntry : knownSchemas.entrySet()) {
            String schemaName = schemaEntry.getKey();
            ComponentSchema componentSchema = schemaEntry.getValue();
            SchemaObject schema = componentSchema.getSchema();

            // security: match against user input, but always use our controlled data from the DefaultSchemaService
            if (schemaName != null && schemaName.equals(messageType)) {
                try {
                    Object payload = resolveActualPayload(message, schema, schemaName);
                    return new Result(payload, Optional.empty());
                } catch (ClassNotFoundException | JacksonException | IllegalArgumentException ex) {
                    String errorMessage = MessageFormat.format(
                            "Unable to create payload {0} from data: {1}", schemaName, message.getPayload());
                    log.info(errorMessage, ex);
                    return new Result(null, Optional.of(errorMessage));
                }
            }
        }

        String errorMessage =
                MessageFormat.format("Specified type {0} is not a registered springwolf schema.", messageType);
        String knownPayloadsMessage =
                MessageFormat.format(" Known types: [{0}]", StringUtils.join(knownSchemaNames, ", "));
        log.info("{}{}", errorMessage, knownPayloadsMessage);
        return new Result(null, Optional.of(errorMessage));
    }

    private Object resolveActualPayload(MessageDto message, SchemaObject schema, String schemaName)
            throws ClassNotFoundException, JacksonException, IllegalArgumentException {
        String firstNonNullType = schema.getType().stream()
                .filter(type -> !type.equals(SchemaType.NULL))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported schema type: null"));
        switch (firstNonNullType) {
            case SchemaType.BOOLEAN -> {
                return jsonMapper.readValue(message.getPayload(), Boolean.class);
            }
            case SchemaType.INTEGER -> {
                return jsonMapper.readValue(message.getPayload(), Long.class);
            }
            case SchemaType.NUMBER -> {
                return jsonMapper.readValue(message.getPayload(), Double.class);
            }
            case SchemaType.OBJECT -> {
                Class<?> payloadClass = Class.forName(schemaName);
                return jsonMapper.readValue(message.getPayload(), payloadClass);
            }
            case SchemaType.STRING -> {
                return jsonMapper.readValue(message.getPayload(), String.class);
            }
            default -> throw new IllegalArgumentException("Unsupported schema type: " + firstNonNullType);
        }
    }

    public record Result(@Nullable Object payload, Optional<String> errorMessage) {}
}
