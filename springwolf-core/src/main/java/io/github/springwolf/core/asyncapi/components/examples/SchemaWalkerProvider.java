// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples;

import io.github.springwolf.core.asyncapi.components.examples.walkers.SchemaWalker;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * Selects the appropriate SchemaWalker for a given content type
 */
@RequiredArgsConstructor
public class SchemaWalkerProvider {

    private final List<SchemaWalker> schemaWalkers;

    public Optional<SchemaWalker> generatorFor(String contentType) {
        return schemaWalkers.stream()
                .filter(walker -> walker.canHandle(contentType))
                .findFirst();
    }
}
