// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SchemaWalkerProvider {

    private final List<SchemaWalker> schemaWalkers;

    public Optional<SchemaWalker> generatorFor(String contentType) {
        return schemaWalkers.stream()
                .filter(walker -> walker.canHandle(contentType))
                .findFirst();
    }
}
