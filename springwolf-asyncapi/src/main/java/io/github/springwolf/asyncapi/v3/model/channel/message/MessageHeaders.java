// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.channel.message;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.springwolf.asyncapi.v3.jackson.model.channel.message.MessageHeadersSerializer;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@JsonSerialize(using = MessageHeadersSerializer.class)
@EqualsAndHashCode
public class MessageHeaders {
    private MultiFormatSchema multiFormatSchema;
    private SchemaObject schema;
    private MessageReference reference;

    private MessageHeaders(MultiFormatSchema multiFormatSchema) {
        this.multiFormatSchema = multiFormatSchema;
    }

    private MessageHeaders(SchemaObject schema) {
        this.schema = schema;
    }

    private MessageHeaders(MessageReference reference) {
        this.reference = reference;
    }

    public static MessageHeaders of(MultiFormatSchema multiFormatSchema) {
        return new MessageHeaders(multiFormatSchema);
    }

    public static MessageHeaders of(SchemaObject schema) {
        return new MessageHeaders(schema);
    }

    public static MessageHeaders of(MessageReference reference) {
        return new MessageHeaders(reference);
    }
}
