// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.core.jackson.TypeNameResolver;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Json31;

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

        converter_openapi30 = createModelConverters(false, springwolfConfigProperties.isUseFqn());
        converter_openapi31 = createModelConverters(true, springwolfConfigProperties.isUseFqn());

        externalModelConverters.forEach(converter_openapi30::addConverter);
        externalModelConverters.forEach(converter_openapi31::addConverter);
    }

    /**
     * creates a ModelConverters Instance for openapi31 or openapi30 and the given useFqn-Flag.
     *
     * @param openapi31  true: use openapi31, false: use openapi30 format.
     * @param useFqn
     * @return
     */
    private ModelConverters createModelConverters(boolean openapi31, boolean useFqn) {
        ModelConverters modelConverters = new ModelConverters(openapi31);

        // to set the correkt fqn-Flag, we have to replace the global (static)
        // TypeNameResolver instance with a custom instance.
        // We therefore have to replace the *ModelResolver* instance inside modelconverters,
        // as ModelResolver holds a reference to TypeNameResolver as private final variable.
        TypeNameResolver typeNameResolver = new SpringWolfTypeNameResolver();
        typeNameResolver.setUseFqn(useFqn);

        ModelResolver replacementResolver = openapi31
                ? new ModelResolver(Json31.mapper(), typeNameResolver).openapi31(true)
                : new ModelResolver(Json.mapper(), typeNameResolver);

        ModelConverter resolverToBeReplaced = modelConverters.getConverters().stream()
                .filter(conv -> conv instanceof ModelResolver)
                .findFirst()
                .orElse(null);

        // we replace by removing the found ModelResolver and adding the new one.
        // Adding a ModelConverter *prepends* this converter to the internal list, which could change the
        // order of convertes in the list. But at this point (after constructor of ModelConvertes),
        // the internal list contains only one Modelresolver, so removing and adding works like replacing the converter.
        if (resolverToBeReplaced != null) {
            modelConverters.removeConverter(resolverToBeReplaced);
            modelConverters.addConverter(replacementResolver);
        }
        return modelConverters;
    }

    public ModelConverters getModelConverter() {
        return switch (springwolfConfigProperties.getDocket().getPayloadSchemaFormat()) {
            case ASYNCAPI_V3, OPENAPI_V3 -> converter_openapi30;
            case OPENAPI_V3_1 -> converter_openapi31;
        };
    }
}
