// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.converters.enums;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.jackson.TypeNameResolver;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;

import java.util.Iterator;

/**
 * Workaround for https://github.com/swagger-api/swagger-core/pull/4727
 */
public class NameInReffedSchemaModelConverter implements ModelConverter {

    @Override
    public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        JavaType javaType = Json.mapper().constructType(type.getType());
        if (chain.hasNext()) {
            Schema<?> schema = chain.next().resolve(type, context, chain);

            if (schema != null && javaType != null && javaType.isEnumType()) {
                if (schema.get$ref() != null) {
                    Schema<?> definedModel = context.resolve(type);
                    if (definedModel != null && definedModel.getName() == null) {
                        definedModel.setName(TypeNameResolver.std.nameForType(javaType));
                    }
                }
            }
            return schema;
        }
        return null;
    }
}
