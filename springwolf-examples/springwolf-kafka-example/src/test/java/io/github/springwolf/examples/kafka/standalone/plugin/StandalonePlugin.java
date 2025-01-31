// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone.plugin;

import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.swagger.v3.core.converter.ModelConverter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface StandalonePlugin {
    default StandalonePluginResult load(StandalonePluginContext context) throws IOException {
        return StandalonePluginResult.builder().build();
    }

    default Collection<ModelConverter> getModelConverters() {
        return List.of();
    }

    default Collection<AsyncApiCustomizer> getAsyncApiCustomizers() {
        return List.of();
    }
}
