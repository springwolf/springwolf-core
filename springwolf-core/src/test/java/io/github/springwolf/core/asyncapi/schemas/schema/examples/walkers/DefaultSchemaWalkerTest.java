// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultSchemaWalkerTest {

    @Test
    void getTypeForExampleValue() {
        DefaultSchemaWalker<Object, Object> schemaWalker = new DefaultSchemaWalker<>(null);

        Schema<Object> schema1 = new Schema<>(); // no types defined.
        Schema<Object> schema2 = new Schema<>();
        schema2.setType("string");
        Schema<Object> schema3 = new Schema<>();
        schema3.setTypes(Set.of("string", "null"));
        Schema<Object> schema4 = new Schema<>();
        schema4.setTypes(Set.of("string", "integer"));

        assertThat(schemaWalker.getTypeForExampleValue(schema1)).isNull();
        assertThat(schemaWalker.getTypeForExampleValue(schema2)).isEqualTo("string");
        assertThat(schemaWalker.getTypeForExampleValue(schema3)).isEqualTo("string");
        assertThat(schemaWalker.getTypeForExampleValue(schema4)).isEqualTo("integer");
    }

    @Test
    void getDefaultValue() {
        DefaultSchemaWalker<Object, Object> schemaWalker = new DefaultSchemaWalker<>(new DummyExampleValueGenerator());

        Schema<Object> schema1 = new Schema<>();
        schema1.setType("string");
        Schema<Object> schema2 = new Schema<>();
        schema2.setType("string");
        schema2.setConst("constValue");
        Schema<Object> schema3 = new Schema<>();
        schema3.setType("string");
        schema3.setEnum(List.of("example1", "example2"));

        assertThat(schemaWalker.fromSchema(schema1, Map.of())).isEqualTo("string");
        assertThat(schemaWalker.fromSchema(schema2, Map.of())).isEqualTo("constValue");
        assertThat(schemaWalker.fromSchema(schema3, Map.of())).isEqualTo("example1");
    }

    @Test
    void getDefaultValueForNonStringEnum() {
        DefaultSchemaWalker<Object, Object> schemaWalker = new DefaultSchemaWalker<>(new DummyExampleValueGenerator());
        Schema<Object> schema = new Schema<>();
        schema.setEnum(List.of(7));

        assertThat(schemaWalker.fromSchema(schema, Map.of())).isEqualTo("7");
    }
}
