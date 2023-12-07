// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.components;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.stavshamir.springwolf.asyncapi.core.v3.jackson.model.channel.message.ComponentSchemaSerializer;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.schema.MultiFormatSchema;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.schema.Schema;
import lombok.Getter;

@Getter
@JsonSerialize(using = ComponentSchemaSerializer.class)
public class ComponentSchema {
    private MultiFormatSchema multiFormatSchema;
    private Schema schema;
    private MessageReference reference;

    private ComponentSchema(MultiFormatSchema multiFormatSchema) {
        this.multiFormatSchema = multiFormatSchema;
    }

    private ComponentSchema(Schema schema) {
        this.schema = schema;
    }

    private ComponentSchema(MessageReference reference) {
        this.reference = reference;
    }

    public static ComponentSchema of(MultiFormatSchema multiFormatSchema) {
        return new ComponentSchema(multiFormatSchema);
    }

    public static ComponentSchema of(Schema schema) {
        return new ComponentSchema(schema);
    }

    public static ComponentSchema of(MessageReference reference) {
        return new ComponentSchema(reference);
    }
}
