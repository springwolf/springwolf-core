// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas.converters;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.PrimitiveType;
import io.swagger.v3.oas.models.media.Schema;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

@NoArgsConstructor
@Slf4j
public class SchemaTitleModelConverter implements ModelConverter {
    @Override
    public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        JavaType javaType = Json.mapper().constructType(type.getType());
        if (chain.hasNext()) {
            Schema<?> schema = chain.next().resolve(type, context, chain);
            if (schema != null && PrimitiveType.createProperty(type.getType()) == null) {
                if (schema.get$ref() != null) {
                    Schema<?> defModel = context.resolve(type);
                    if (defModel != null && defModel.getTitle() == null) {
                        defModel.setTitle(javaType.getRawClass().getSimpleName());
                    }
                }
            }
            return schema;
        }
        return null;
    }
}
