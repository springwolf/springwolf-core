// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.components;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.springwolf.asyncapi.v3.jackson.model.channel.message.ComponentSchemaSerializer;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@JsonSerialize(using = ComponentSchemaSerializer.class)
public class ComponentSchema {
    private MultiFormatSchema multiFormatSchema;
    private SchemaObject schema;
    private SchemaReference reference;

    private ComponentSchema(MultiFormatSchema multiFormatSchema) {
        this.multiFormatSchema = multiFormatSchema;
    }

    private ComponentSchema(SchemaObject schema) {
        this.schema = schema;
    }

    private ComponentSchema(SchemaReference reference) {
        this.reference = reference;
    }

    public static ComponentSchema of(MultiFormatSchema multiFormatSchema) {
        return new ComponentSchema(multiFormatSchema);
    }

    public static ComponentSchema of(SchemaObject schema) {
        return new ComponentSchema(schema);
    }

    public static ComponentSchema of(SchemaReference reference) {
        return new ComponentSchema(reference);
    }
}
