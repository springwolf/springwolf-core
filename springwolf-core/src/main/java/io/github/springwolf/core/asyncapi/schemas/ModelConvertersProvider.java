package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaFormat;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * Provides fully prepared {@link io.swagger.v3.core.converter.ModelConverters} for openapi v3 and v3.1
 * requirements.
 */
@RequiredArgsConstructor
public class ModelConvertersProvider implements InitializingBean {

    private final List<ModelConverter> externalModelConverters;

    private ModelConverters converter_openapi30;
    private ModelConverters converter_openapi31;


    /**
     * provides the appropriate {@link ModelConverters} for the given {@link SchemaFormat}.
     *
     * @param schemaFormat the schemaFormat that shall be generated.
     * @return the appropriate {@link ModelConverters} or {@link IllegalArgumentException} if the given format is not supported.
     */
    public ModelConverters getModelConverterForSchemaFormat(SchemaFormat schemaFormat) {
        return switch (schemaFormat) {
            case ASYNCAPI_V3 -> converter_openapi30;
            case OPENAPI_V3 -> converter_openapi30;
            case OPENAPI_V3_1 -> converter_openapi31;
            default -> throw new IllegalArgumentException("SchemaFormat not supported: " + schemaFormat);
        };
    }


    /**
     * initializes instance variables after all constructor properties are set.
     */
    @Override
    public void afterPropertiesSet() {
        converter_openapi30 = new ModelConverters(false);
        converter_openapi31 = new ModelConverters(true);
        externalModelConverters.forEach(converter_openapi30::addConverter);
        externalModelConverters.forEach(converter_openapi31::addConverter);
    }

}
