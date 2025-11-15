// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultSchemaWalkerTest {

    @Test
    void getTypeForExampleValue() {
        DefaultSchemaWalker schemaWalker = new DefaultSchemaWalker<>(null);

        Schema schema1 = new Schema(); // no types defined.
        Schema schema2 = new Schema().type("string");
        Schema schema3 = new Schema().types(Set.of("string", "null"));
        Schema schema4 = new Schema().types(Set.of("string", "integer"));

        assertThat(schemaWalker.getTypeForExampleValue(schema1)).isNull();
        assertThat(schemaWalker.getTypeForExampleValue(schema2)).isEqualTo("string");
        assertThat(schemaWalker.getTypeForExampleValue(schema3)).isEqualTo("string");
        assertThat(schemaWalker.getTypeForExampleValue(schema4)).isEqualTo("integer");
    }
}
