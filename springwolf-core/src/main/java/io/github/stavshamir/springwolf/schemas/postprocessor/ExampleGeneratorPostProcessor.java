// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.postprocessor;

import io.github.stavshamir.springwolf.schemas.example.SchemaWalker;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class ExampleGeneratorPostProcessor implements SchemasPostProcessor {
    private final SchemaWalker schemaWalker;

    @Override
    public void process(Schema schema, Map<String, Schema> definitions, String contentType) {
        if (schema.getExample() == null) {
            log.debug("Generate example for {}", schema.getName());
            // TODO: Select generator based on content type
            Object example = schemaWalker.fromSchema(schema, definitions);
            schema.setExample(example);
        }
    }
}
