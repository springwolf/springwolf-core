// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeaderSchemaObjectMergerTest {

    @Test
    void merge() {
        SchemaObject initial =
                SchemaObject.builder().properties(new HashMap<>()).build();
        initial.getProperties().put("key1", "value1");

        // when
        SchemaObject merged = HeaderSchemaObjectMerger.merge(initial);

        // then
        assertEquals(
                merged,
                SchemaObject.builder().properties(Map.of("key1", "value1")).build());
    }

    @Test
    void mergeAndIgnoreNullValues() {
        SchemaObject initial =
                SchemaObject.builder().properties(new HashMap<>()).build();
        initial.getProperties().put("key1", "value1");

        // when
        SchemaObject merged = HeaderSchemaObjectMerger.merge(
                initial,
                null,
                SchemaObject.builder().build(),
                SchemaObject.builder().properties(Collections.emptyMap()).build());

        // then
        assertEquals(
                merged,
                SchemaObject.builder().properties(Map.of("key1", "value1")).build());
    }

    @Test
    void mergeWhileNotOverwritingInitialValues() {
        SchemaObject initial = SchemaObject.builder()
                .title("initial-title")
                .description("this-is-initial")
                .properties(new HashMap<>())
                .build();
        initial.getProperties().put("key1", "value1");

        SchemaObject schema1 = SchemaObject.builder()
                .description("this-is-schema1")
                .properties(new HashMap<>())
                .build();
        schema1.getProperties().put("key1", "value2");
        schema1.getProperties().put("key2", "value2");

        SchemaObject schema2 = SchemaObject.builder()
                .description("this-is-schema2")
                .properties(new HashMap<>())
                .build();
        schema2.getProperties().put("key2", "value3");
        schema2.getProperties().put("key3", "value3");

        // when
        SchemaObject merged = HeaderSchemaObjectMerger.merge(initial, schema1, schema2);

        // then
        assertEquals(merged.getTitle(), "initial-title-1820791802");
        assertEquals(merged.getDescription(), "this-is-initial");
        assertEquals(merged.getProperties().size(), 3);
        assertEquals(merged.getProperties().get("key1"), "value1");
        assertEquals(merged.getProperties().get("key2"), "value2");
        assertEquals(merged.getProperties().get("key3"), "value3");
    }

    @Test
    void mergeAndTakeFirstNonNull() {
        SchemaObject initial =
                SchemaObject.builder().properties(new HashMap<>()).build();

        SchemaObject schema1 = SchemaObject.builder()
                .description("this-is-schema1")
                .properties(new HashMap<>())
                .build();
        schema1.getProperties().put("key2", "value2");

        SchemaObject schema2 = SchemaObject.builder()
                .description("this-is-schema2")
                .properties(new HashMap<>())
                .build();
        schema2.getProperties().put("key2", "value3");

        // when
        SchemaObject merged = HeaderSchemaObjectMerger.merge(initial, schema1, schema2);

        // then
        assertEquals(merged.getTitle(), "Headers-824725166");
        assertEquals(merged.getDescription(), "this-is-schema1");
        assertEquals(merged.getProperties().size(), 1);
        assertEquals(merged.getProperties().get("key2"), "value2");
    }
}
