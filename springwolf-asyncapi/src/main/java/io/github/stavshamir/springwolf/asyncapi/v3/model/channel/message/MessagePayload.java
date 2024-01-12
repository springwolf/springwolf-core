// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.model.channel.message.MessagePayloadSerializer;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@JsonSerialize(using = MessagePayloadSerializer.class)
@EqualsAndHashCode
public class MessagePayload {
    private MultiFormatSchema multiFormatSchema;
    private SchemaObject schema;
    private MessageReference reference;

    private MessagePayload(MultiFormatSchema multiFormatSchema) {
        this.multiFormatSchema = multiFormatSchema;
    }

    private MessagePayload(SchemaObject schema) {
        this.schema = schema;
    }

    private MessagePayload(MessageReference reference) {
        this.reference = reference;
    }

    public static MessagePayload of(MultiFormatSchema multiFormatSchema) {
        return new MessagePayload(multiFormatSchema);
    }

    public static MessagePayload of(SchemaObject schema) {
        return new MessagePayload(schema);
    }

    public static MessagePayload of(MessageReference reference) {
        return new MessagePayload(reference);
    }
}
