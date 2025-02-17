// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone.plugin;

import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.swagger.v3.core.converter.ModelConverter;

import java.io.IOException;
import java.util.List;

/**
 * replaced by StandaloneConfiguration
 * // TODO: remove
 */
@Deprecated
public interface StandalonePlugin {

    default StandalonePluginResult load(StandalonePluginContext context) throws IOException {
        return StandalonePluginResult.builder().build();
    }

    default List<ModelConverter> getModelConverters() {
        return List.of();
    }

    default List<AsyncApiCustomizer> getAsyncApiCustomizers() {
        return List.of();
    }
}
