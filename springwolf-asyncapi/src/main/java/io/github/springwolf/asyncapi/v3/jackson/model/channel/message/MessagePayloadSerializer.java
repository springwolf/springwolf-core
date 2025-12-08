// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.jackson.model.channel.message;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

public class MessagePayloadSerializer extends ValueSerializer<MessagePayload> {
    @Override
    public void serialize(MessagePayload value, JsonGenerator gen, SerializationContext serializers)
            throws JacksonException {
        if (value.getReference() != null) {
            gen.writePOJO(value.getReference());
        } else if (value.getSchema() != null) {
            gen.writePOJO(value.getSchema());
        } else {
            gen.writePOJO(value.getMultiFormatSchema());
        }
    }
}
