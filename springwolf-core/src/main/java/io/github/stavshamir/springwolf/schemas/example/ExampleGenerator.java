// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import io.swagger.v3.oas.models.media.Schema;
import jakarta.annotation.Nullable;

import java.util.Map;

/**
 * Builds an example that is embedded into {@link Schema#setExample(Object)}
 *
 * Handles types defined in https://www.asyncapi.com/docs/reference/specification/v3.0.0#dataTypeFormat
 */
public interface ExampleGenerator {

    /**
     * Build an example for a schema
     * @param schema The schema for which an example is build
     * @param definitions Other schemas that might be referenced
     * @return the example, null if no example can be generated
     */
    @Nullable
    Object fromSchema(Schema schema, Map<String, Schema> definitions);

    class ExampleGeneratingException extends RuntimeException {
        public ExampleGeneratingException(String message) {
            super(message);
        }
    }
}
