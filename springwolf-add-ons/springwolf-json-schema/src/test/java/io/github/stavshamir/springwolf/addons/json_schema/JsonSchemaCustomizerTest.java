// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.addons.json_schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.types.Components;
import io.swagger.v3.oas.models.media.ObjectSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

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
    public void handleEmptySchemaTest() {
        // given
        AsyncAPI asyncAPI = createAsyncApi();

        // when
        jsonSchemaCustomizer.customize(asyncAPI);

        // then
        assertThat(asyncAPI).isEqualTo(createAsyncApi());
    }

    @Test
    public void shouldAddJsonSchemaExtensionTest() throws JsonProcessingException {
        // given
        AsyncAPI asyncAPI = createAsyncApi();
        asyncAPI.getComponents().setSchemas(Map.of("schema", new ObjectSchema()));

        when(jsonSchemaGenerator.fromSchema(any(), any())).thenReturn("mock-string");

        // when
        jsonSchemaCustomizer.customize(asyncAPI);

        // then
        assertThat(asyncAPI.getComponents().getSchemas().get("schema").getExtensions())
                .isEqualTo(Map.of("x-json-schema", "mock-string"));
    }

    private static AsyncAPI createAsyncApi() {
        AsyncAPI asyncAPI = new AsyncAPI();
        asyncAPI.setComponents(Components.builder().schemas(Map.of()).build());
        return asyncAPI;
    }
}
