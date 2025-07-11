// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    private final ObjectMapper objectMapper;

    public Result createPayloadObject(MessageDto message) {
        String messagePayloadType = message.getPayloadType();

        if (MessageDto.EMPTY.equals(message.getPayload())) {
            return new Result(null, Optional.empty());
        }

        Map<String, ComponentSchema> knownSchemas = componentsService.getSchemas();
        Set<String> knownSchemaNames = knownSchemas.keySet();
        for (Map.Entry<String, ComponentSchema> schemaEntry : knownSchemas.entrySet()) {
            String schemaName = schemaEntry.getKey();
            ComponentSchema componentSchema = schemaEntry.getValue();
            SchemaObject schema = componentSchema.getSchema();

            // security: match against user input, but always use our controlled data from the DefaultSchemaService
            if (schemaName != null && schemaName.equals(messagePayloadType)) {
                try {
                    Object payload = resolveActualPayload(message, schema, schemaName);
                    return new Result(payload, Optional.empty());
                } catch (ClassNotFoundException | JsonProcessingException | IllegalArgumentException ex) {
                    String errorMessage = MessageFormat.format(
                            "Unable to create payload {0} from data: {1}", schemaName, message.getPayload());
                    log.info(errorMessage, ex);
                    return new Result(null, Optional.of(errorMessage));
                }
            }
        }

        String errorMessage = MessageFormat.format(
                "Specified payloadType {0} is not a registered springwolf schema.", messagePayloadType);
        String knownPayloadsMessage =
                MessageFormat.format(" Known payloadTypes: [{0}]", StringUtils.join(knownSchemaNames, ", "));
        log.info("{}{}", errorMessage, knownPayloadsMessage);
        return new Result(null, Optional.of(errorMessage));
    }

    private Object resolveActualPayload(MessageDto message, SchemaObject schema, String schemaName)
            throws ClassNotFoundException, JsonProcessingException, IllegalArgumentException {
        switch (schema.getType()) {
            case SchemaType.BOOLEAN -> {
                return objectMapper.readValue(message.getPayload(), Boolean.class);
            }
            case SchemaType.INTEGER -> {
                return objectMapper.readValue(message.getPayload(), Long.class);
            }
            case SchemaType.NUMBER -> {
                return objectMapper.readValue(message.getPayload(), Double.class);
            }
            case SchemaType.OBJECT -> {
                Class<?> payloadClass = Class.forName(schemaName);
                return objectMapper.readValue(message.getPayload(), payloadClass);
            }
            case SchemaType.STRING -> {
                return objectMapper.readValue(message.getPayload(), String.class);
            }
            default -> throw new IllegalArgumentException("Unsupported schema type: " + schema.getType());
        }
    }

    public record Result(@Nullable Object payload, Optional<String> errorMessage) {}
}
