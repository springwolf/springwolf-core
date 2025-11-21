// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;

import java.util.List;

/**
 * Provides fully prepared {@link io.swagger.v3.core.converter.ModelConverters} for openapi v3 and v3.1
 * requirements.
 */
public class ModelConvertersProvider {

    private final SpringwolfConfigProperties springwolfConfigProperties;
    private final ModelConverters converter_openapi30;
    private final ModelConverters converter_openapi31;

    public ModelConvertersProvider(
            SpringwolfConfigProperties springwolfConfigProperties, List<ModelConverter> externalModelConverters) {
        this.springwolfConfigProperties = springwolfConfigProperties;

        converter_openapi30 = new ModelConverters(false);
        converter_openapi31 = new ModelConverters(true);
        externalModelConverters.forEach(converter_openapi30::addConverter);
        externalModelConverters.forEach(converter_openapi31::addConverter);
    }

    public ModelConverters getModelConverter() {
        return switch (springwolfConfigProperties.getDocket().getPayloadSchemaFormat()) {
            case ASYNCAPI_V3, OPENAPI_V3 -> converter_openapi30;
            case OPENAPI_V3_1 -> converter_openapi31;
        };
    }
}
