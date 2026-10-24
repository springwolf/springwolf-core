// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.jackson.model.channel.message;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

public class MessageHeadersSerializer extends ValueSerializer<MessageHeaders> {
    @Override
    public void serialize(MessageHeaders value, tools.jackson.core.JsonGenerator gen, SerializationContext ctxt) {
        if (value.getReference() != null) {
            gen.writePOJO(value.getReference());
        } else if (value.getSchema() != null) {
            gen.writePOJO(value.getSchema());
        } else {
            gen.writePOJO(value.getMultiFormatSchema());
        }
    }
}
