// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchemaObjectMergerTest {

    @Test
    void merge() {
        SchemaObject initial = new SchemaObject();
        initial.getProperties().put("key1", "value1");

        // when
        SchemaObject merged = SchemaObjectMerger.merge(initial);

        // then
        assertEquals(merged, initial);
    }

    @Test
    void mergeAndIgnoreNullValues() {
        SchemaObject initial = new SchemaObject();
        initial.getProperties().put("key1", "value1");

        // when
        SchemaObject merged = SchemaObjectMerger.merge(initial, null, null);

        // then
        assertEquals(merged, initial);
    }

    @Test
    void mergeWhileNotOverwritingInitialValues() {
        SchemaObject initial = new SchemaObject();
        initial.getProperties().put("key1", "value1");

        SchemaObject schema1 = new SchemaObject();
        initial.getProperties().put("key1", "value2");
        schema1.getProperties().put("key2", "value2");

        SchemaObject schema2 = new SchemaObject();
        schema1.getProperties().put("key2", "value3");
        schema2.getProperties().put("key3", "value3");

        // when
        SchemaObject merged = SchemaObjectMerger.merge(initial, schema1, schema2);

        // then
        assertEquals(merged.getProperties().size(), 3);
        assertEquals(merged.getProperties().get("key1"), "value1");
        assertEquals(merged.getProperties().get("key2"), "value2");
        assertEquals(merged.getProperties().get("key3"), "value3");
    }
}
