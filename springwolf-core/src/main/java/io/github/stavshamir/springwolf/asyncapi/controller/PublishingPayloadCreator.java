// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.List;

/**
 * Used in plugins with publishing enabled.
 * Located in springwolf-core to allow sharing of code
 */
@RequiredArgsConstructor
@Slf4j
public class PublishingPayloadCreator {

    private final SchemasService schemasService;
    private final ObjectMapper objectMapper;

    public Result createPayloadObject(MessageDto message) {
        String messagePayloadType = message.getPayloadType();

        List<String> knownSchemaNames = schemasService.getDefinitions().values().stream()
                .map(Schema::getName)
                .toList();
        for (String schemaPayloadType : knownSchemaNames) {
            // security: match against user input, but always use our controlled data from the DefaultSchemaService
            if (schemaPayloadType != null && schemaPayloadType.equals(messagePayloadType)) {
                try {
                    Class<?> payloadClass = Class.forName(schemaPayloadType);
                    Object payload = objectMapper.readValue(message.getPayload(), payloadClass);
                    return new Result(payload, null);
                } catch (ClassNotFoundException | JsonProcessingException ex) {
                    String errorMessage = MessageFormat.format(
                            "Unable to create payload {0} from data: {1}", schemaPayloadType, message.getPayload());
                    log.info(errorMessage, ex);
                    return new Result(null, errorMessage);
                }
            }
        }

        String errorMessage = MessageFormat.format(
                "Specified payloadType {0} is not a registered springwolf schema.", messagePayloadType);
        String knownPayloadsMessage =
                MessageFormat.format(" Known payloadTypes: [{0}]", StringUtils.join(knownSchemaNames, ", "));
        log.info(errorMessage + knownPayloadsMessage);
        return new Result(null, errorMessage);
    }

    public record Result(Object payload, String errorMessage) {}
}
