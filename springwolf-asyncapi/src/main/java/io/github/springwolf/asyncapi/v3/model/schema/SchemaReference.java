// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.springwolf.asyncapi.v3.model.Reference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SchemaReference implements Schema, Reference {

    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }

    public static SchemaReference fromSchema(String schemaName) {
        return new SchemaReference("#/components/schemas/" + schemaName);
    }
}
