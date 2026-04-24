// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @Getter
    private final ModelConverters modelConverters;

    public ModelConvertersProvider(
            SpringwolfConfigProperties springwolfConfigProperties, List<ModelConverter> externalModelConverters) {

        typeNameResolver = createTypeNameResolver(springwolfConfigProperties);

        boolean isOpenApi31 = isOpenApi31(springwolfConfigProperties);
        ModelResolver modelResolver = createModelResolver(isOpenApi31, typeNameResolver);
        objectMapper = modelResolver.objectMapper();

        modelConverters = createModelConverters(isOpenApi31, modelResolver);
        externalModelConverters.forEach(modelConverters::addConverter);
    }

    private TypeNameResolver createTypeNameResolver(SpringwolfConfigProperties springwolfConfigProperties) {
        return new SpringwolfTypeNameResolver(springwolfConfigProperties.isUseFqn());
    }

    private boolean isOpenApi31(
            SpringwolfConfigProperties springwolfConfigProperties) {
        return switch (springwolfConfigProperties.getDocket().getPayloadSchemaFormat()) {
            case ASYNCAPI_V3, OPENAPI_V3 -> false;
            case OPENAPI_V3_1 -> true;
        };
    }

    private ModelConverters createModelConverters(boolean isOpenApi31, ModelResolver modelResolver) {
        ModelConverters modelConverters = new ModelConverters(isOpenApi31);

        replaceModelResolver(modelConverters, modelResolver);

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
