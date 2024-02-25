// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.jackson.model.channel.message;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;

import java.io.IOException;

public class MessagePayloadSerializer extends JsonSerializer<MessagePayload> {
    @Override
    public void serialize(MessagePayload value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value.getReference() != null) {
            gen.writeObject(value.getReference());
        } else if (value.getSchema() != null) {
            gen.writeObject(value.getSchema());
        } else {
            gen.writeObject(value.getMultiFormatSchema());
        }
    }
}
