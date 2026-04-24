// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.core.jackson.TypeNameResolver;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Json31;
import lombok.Getter;

import java.util.List;

/**
 * Provides fully prepared {@link io.swagger.v3.core.converter.ModelConverters} for openapi v3 and v3.1
 * requirements.
 */
public class ModelConvertersProvider {
    @Getter
    private final TypeNameResolver typeNameResolver;

    @Getter
    private final ModelConverters modelConverters;

    public ModelConvertersProvider(
            SpringwolfConfigProperties springwolfConfigProperties, List<ModelConverter> externalModelConverters) {

        typeNameResolver = createTypeNameResolver(springwolfConfigProperties);

        modelConverters = createModelConverter(springwolfConfigProperties, typeNameResolver);
        externalModelConverters.forEach(modelConverters::addConverter);
    }

    private TypeNameResolver createTypeNameResolver(SpringwolfConfigProperties springwolfConfigProperties) {
        return new SpringwolfTypeNameResolver(springwolfConfigProperties.isUseFqn());
    }

    private ModelConverters createModelConverter(
            SpringwolfConfigProperties springwolfConfigProperties, TypeNameResolver typeNameResolver) {
        return switch (springwolfConfigProperties.getDocket().getPayloadSchemaFormat()) {
            case ASYNCAPI_V3, OPENAPI_V3 -> createModelConverters(false, typeNameResolver);
            case OPENAPI_V3_1 -> createModelConverters(true, typeNameResolver);
        };
    }

    private ModelConverters createModelConverters(boolean openapi31, TypeNameResolver typeNameResolver) {
        ModelConverters modelConverters = new ModelConverters(openapi31);

        ModelResolver replacementResolver = createModelResolver(openapi31, typeNameResolver);
        replaceModelResolver(modelConverters, replacementResolver);

        return modelConverters;
    }

    /**
     * Create a new ModelResolver with an own {@link TypeNameResolver} for thread-safety
     */
    private static ModelResolver createModelResolver(boolean isOpenapi31, TypeNameResolver typeNameResolver) {
        return isOpenapi31
                ? new ModelResolver(Json31.mapper(), typeNameResolver).openapi31(true)
                : new ModelResolver(Json.mapper(), typeNameResolver);
    }

    /**
     * The default {@link ModelConverters} has only a single {@link ModelResolver} that is replaced with a Springwolf one
     */
    private static void replaceModelResolver(ModelConverters modelConverters, ModelResolver replacementResolver) {
        modelConverters.getConverters().stream()
                .filter(conv -> conv instanceof ModelResolver)
                .findFirst()
                .ifPresent(modelConverter -> {
                    modelConverters.removeConverter(modelConverter);
                    modelConverters.addConverter(replacementResolver);
                });
    }
}
