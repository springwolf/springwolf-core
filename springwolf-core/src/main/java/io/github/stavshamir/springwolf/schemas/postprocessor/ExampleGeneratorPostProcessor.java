// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.postprocessor;

import io.github.stavshamir.springwolf.schemas.example.ExampleGenerator;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class ExampleGeneratorPostProcessor implements SchemasPostProcessor {
    private final ExampleGenerator exampleGenerator;

    @Override
    public void process(Schema schema, Map<String, Schema> definitions) {
        // Even though the schemas are removed from the definitions, they are still processed and throws an error when
        // they don't find themselves in the definitions.
        if (schema.getName().startsWith("org.apache.avro")) {
            log.debug("Skipping avro schema: " + schema.getName());
            return;
        }

        if (schema.getExample() == null) {
            log.debug("Generate example for {}", schema.getName());

            Object example = exampleGenerator.fromSchema(schema, definitions);
            schema.setExample(example);
        }
    }
}
