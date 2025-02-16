// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.springwolf.addons.json_schema.JsonSchemaGenerator;
import io.github.springwolf.addons.json_schema.configuration.SpringwolfJsonSchemaAutoConfiguration;
import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;

import java.util.Collection;
import java.util.List;

public class JsonSchemaPluginContext implements StandalonePluginContext {
    @Override
    public Collection<AsyncApiCustomizer> getAsyncApiCustomizers() {
        SpringwolfJsonSchemaAutoConfiguration jsonSchemaConfiguration = new SpringwolfJsonSchemaAutoConfiguration();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaGenerator jsonSchemaGenerator = jsonSchemaConfiguration.jsonSchemaGenerator(objectMapper);
        AsyncApiCustomizer jsonSchemaCustomizer = jsonSchemaConfiguration.jsonSchemaCustomizer(jsonSchemaGenerator);

        return List.of(jsonSchemaCustomizer);
    }
}
