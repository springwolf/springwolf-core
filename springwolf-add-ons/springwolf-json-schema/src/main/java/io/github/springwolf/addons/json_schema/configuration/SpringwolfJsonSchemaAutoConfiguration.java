// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.json_schema.configuration;

import io.github.springwolf.addons.json_schema.JsonSchemaCustomizer;
import io.github.springwolf.addons.json_schema.JsonSchemaGenerator;
import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@StandaloneConfiguration
public class SpringwolfJsonSchemaAutoConfiguration {

    @Bean
    public JsonSchemaGenerator jsonSchemaGenerator() {
        return new JsonSchemaGenerator();
    }

    @Bean
    public AsyncApiCustomizer jsonSchemaCustomizer(JsonSchemaGenerator jsonSchemaGenerator) {
        return new JsonSchemaCustomizer(jsonSchemaGenerator);
    }
}
