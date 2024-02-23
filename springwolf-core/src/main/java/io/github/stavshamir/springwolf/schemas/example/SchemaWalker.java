// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import io.swagger.v3.oas.models.media.Schema;
import jakarta.annotation.Nullable;

import java.util.Map;

/**
 * Builds an example for a schema
 */
public interface SchemaWalker<R> {

    /**
     * Build an example for a schema
     * @param schema The schema for which an example is build
     * @param definitions Other schemas that might be referenced
     * @return the example, null if no example can be generated
     */
    @Nullable
    R fromSchema(Schema schema, Map<String, Schema> definitions);

    /**
     * Check if the walker can handle the given content type
     *
     * @param contentType The content type to check
     */
    boolean canHandle(String contentType);

    class ExampleGeneratingException extends RuntimeException {
        public ExampleGeneratingException(String message) {
            super(message);
        }
    }
}
