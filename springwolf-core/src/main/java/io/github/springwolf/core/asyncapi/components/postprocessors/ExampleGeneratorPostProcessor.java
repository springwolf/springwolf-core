// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.SchemaWalker;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class ExampleGeneratorPostProcessor implements SchemasPostProcessor {

    private final SchemaWalkerProvider schemaWalkerProvider;

    @Override
    public void process(Schema schema, Map<String, Schema> definitions, String contentType) {
        if (schema.getExample() == null) {
            log.debug("Generate example for {}", schema.getName());

            Optional<SchemaWalker> schemaWalkerOptional = schemaWalkerProvider.generatorFor(contentType);
            if (schemaWalkerOptional.isPresent()) {
                Object example = schemaWalkerOptional.get().fromSchema(schema, definitions);
                schema.setExample(example);
            } else {
                log.debug("No matching SchemaWalker for contentType {} for example {}", contentType, schema.getName());
            }
        }
    }
}
