// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.model.channel.message.MessageHeadersSerializer;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.Schema;
import lombok.Getter;

@Getter
@JsonSerialize(using = MessageHeadersSerializer.class)
public class MessageHeaders {
    private MultiFormatSchema multiFormatSchema;
    private Schema schema;
    private MessageReference reference;

    private MessageHeaders(MultiFormatSchema multiFormatSchema) {
        this.multiFormatSchema = multiFormatSchema;
    }

    private MessageHeaders(Schema schema) {
        this.schema = schema;
    }

    private MessageHeaders(MessageReference reference) {
        this.reference = reference;
    }

    public static MessageHeaders of(MultiFormatSchema multiFormatSchema) {
        return new MessageHeaders(multiFormatSchema);
    }

    public static MessageHeaders of(Schema schema) {
        return new MessageHeaders(schema);
    }

    public static MessageHeaders of(MessageReference reference) {
        return new MessageHeaders(reference);
    }
}
