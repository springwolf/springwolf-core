// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers;

import io.swagger.v3.oas.models.media.Schema;

import java.util.Optional;

public record SchemaContainer(Optional<String> name, Schema schema) {
    /**
     * The name of the schema or parent schema (in case of refs, arrays, etc.)
     */
    public Optional<String> name() {
        return name;
    }

    public Schema schema() {
        return schema;
    }
}
