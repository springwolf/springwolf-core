// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.json_schema;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.components.Components;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonSchemaCustomizerTest {

    private JsonSchemaGenerator jsonSchemaGenerator;
    private JsonSchemaCustomizer jsonSchemaCustomizer;

    @BeforeEach
    void setUp() {
        jsonSchemaGenerator = mock(JsonSchemaGenerator.class);
        jsonSchemaCustomizer = new JsonSchemaCustomizer(jsonSchemaGenerator);
    }

    @Test
    void handleEmptySchemaTest() {
        // given
        AsyncAPI asyncAPI = createAsyncApi();

        // when
        jsonSchemaCustomizer.customize(asyncAPI);

        // then
        assertThat(asyncAPI).isEqualTo(createAsyncApi());
    }

    @Test
    void shouldAddJsonSchemaExtensionTest() throws Exception {
        // given
        AsyncAPI asyncAPI = createAsyncApi();
        SchemaObject schemaObject = new SchemaObject();
        schemaObject.setType(Set.of(SchemaType.OBJECT));
        asyncAPI.getComponents().setSchemas(Map.of("schema", ComponentSchema.of(schemaObject)));

        when(jsonSchemaGenerator.fromSchema(any(), any())).thenReturn("mock-string");

        // when
        jsonSchemaCustomizer.customize(asyncAPI);

        // then
        assertThat(asyncAPI.getComponents()
                        .getSchemas()
                        .get("schema")
                        .getSchema()
                        .getExtensionFields())
                .isEqualTo(Map.of("x-json-schema", "mock-string"));
    }

    private static AsyncAPI createAsyncApi() {
        AsyncAPI asyncAPI = new AsyncAPI();
        asyncAPI.setComponents(Components.builder().schemas(Map.of()).build());
        return asyncAPI;
    }
}
