// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.swagger.v3.core.converter.ModelConverter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface StandalonePluginContext {
    default StandalonePluginResult load(StandaloneContext context) throws IOException {
        return StandalonePluginResult.builder().build();
    }

    default Collection<ModelConverter> getModelConverters() {
        return List.of();
    }

    default Collection<AsyncApiCustomizer> getAsyncApiCustomizers() {
        return List.of();
    }
}
