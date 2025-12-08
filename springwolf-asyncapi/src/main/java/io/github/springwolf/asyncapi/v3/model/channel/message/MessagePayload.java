// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.channel.message;

import io.github.springwolf.asyncapi.v3.jackson.model.channel.message.MessagePayloadSerializer;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tools.jackson.databind.annotation.JsonSerialize;

@Getter
@JsonSerialize(using = MessagePayloadSerializer.class)
@EqualsAndHashCode
@ToString
public class MessagePayload {
    private MultiFormatSchema multiFormatSchema;
    private SchemaObject schema;
    private SchemaReference reference;

    private MessagePayload(MultiFormatSchema multiFormatSchema) {
        this.multiFormatSchema = multiFormatSchema;
    }

    private MessagePayload(SchemaObject schema) {
        this.schema = schema;
    }

    private MessagePayload(SchemaReference reference) {
        this.reference = reference;
    }

    public static MessagePayload of(MultiFormatSchema multiFormatSchema) {
        return new MessagePayload(multiFormatSchema);
    }

    public static MessagePayload of(SchemaObject schema) {
        return new MessagePayload(schema);
    }

    public static MessagePayload of(SchemaReference reference) {
        return new MessagePayload(reference);
    }
}
