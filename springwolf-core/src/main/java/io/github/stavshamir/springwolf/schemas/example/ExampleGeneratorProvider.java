// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExampleGeneratorProvider {

    private final List<SchemaWalker> generators;

    public Optional<SchemaWalker> generatorFor(String contentType) {
        return generators.stream()
                .filter(generator -> generator.canHandle(contentType))
                .findFirst();
    }
}
