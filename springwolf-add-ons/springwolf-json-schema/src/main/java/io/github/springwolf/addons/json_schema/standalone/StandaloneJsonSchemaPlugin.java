// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.json_schema.standalone;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.springwolf.addons.json_schema.JsonSchemaGenerator;
import io.github.springwolf.addons.json_schema.configuration.SpringwolfJsonSchemaAutoConfiguration;
import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.github.springwolf.core.standalone.plugin.StandalonePlugin;

import java.util.List;

public class StandaloneJsonSchemaPlugin implements StandalonePlugin {
    @Override
    public List<AsyncApiCustomizer> getAsyncApiCustomizers() {
        SpringwolfJsonSchemaAutoConfiguration jsonSchemaConfiguration = new SpringwolfJsonSchemaAutoConfiguration();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaGenerator jsonSchemaGenerator = jsonSchemaConfiguration.jsonSchemaGenerator(objectMapper);
        AsyncApiCustomizer jsonSchemaCustomizer = jsonSchemaConfiguration.jsonSchemaCustomizer(jsonSchemaGenerator);

        return List.of(jsonSchemaCustomizer);
    }
}
