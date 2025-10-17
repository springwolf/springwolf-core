// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HeaderSchemaObjectMergerTest {

    @Test
    void merge() {
        SchemaObject initial =
                SchemaObject.builder().properties(new HashMap<>()).build();
        initial.getProperties().put("key1", "value1");

        // when
        SchemaObject merged = HeaderSchemaObjectMerger.merge(initial);

        // then
        assertThat(SchemaObject.builder().properties(Map.of("key1", "value1")).build())
                .isEqualTo(merged);
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
        assertThat(SchemaObject.builder().properties(Map.of("key1", "value1")).build())
                .isEqualTo(merged);
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
        assertThat(merged.getTitle()).isEqualTo("initial-title-1820791802");
        assertThat(merged.getDescription()).isEqualTo("this-is-initial");
        assertThat(merged.getProperties().size()).isEqualTo(3);
        assertThat(merged.getProperties().get("key1")).isEqualTo("value1");
        assertThat(merged.getProperties().get("key2")).isEqualTo("value2");
        assertThat(merged.getProperties().get("key3")).isEqualTo("value3");
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
        assertThat(merged.getTitle()).isEqualTo("Headers-824725166");
        assertThat(merged.getDescription()).isEqualTo("this-is-schema1");
        assertThat(merged.getProperties().size()).isEqualTo(1);
        assertThat(merged.getProperties().get("key2")).isEqualTo("value2");
    }
}
