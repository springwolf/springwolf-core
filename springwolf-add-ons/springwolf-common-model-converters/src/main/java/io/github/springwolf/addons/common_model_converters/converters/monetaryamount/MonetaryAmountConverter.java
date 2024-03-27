// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.converters.monetaryamount;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;

import java.util.Iterator;

public class MonetaryAmountConverter implements ModelConverter {

    @Override
    public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        JavaType javaType = Json.mapper().constructType(type.getType());
        if (javaType != null) {
            Class<?> cls = javaType.getRawClass();
            if (javax.money.MonetaryAmount.class.isAssignableFrom(cls))
                type = new AnnotatedType(MonetaryAmount.class).resolveAsRef(true);
        }
        return (chain.hasNext()) ? chain.next().resolve(type, context, chain) : null;
    }
}
