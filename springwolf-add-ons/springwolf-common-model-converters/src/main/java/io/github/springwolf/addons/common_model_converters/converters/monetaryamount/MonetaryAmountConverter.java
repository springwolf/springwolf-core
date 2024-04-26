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
        Class<?> rawClass = getRawClass(type);
        boolean isMonetaryAmount = isMonetaryAmountType(rawClass);

        if (isMonetaryAmount) {
            type = new AnnotatedType(MonetaryAmount.class).resolveAsRef(true);
        }

        Schema<?> schema = proceedWithChain(type, context, chain);

        if (isMonetaryAmount && schema != null && rawClass != null) {
            schema.name(rawClass.getName());
        }

        return schema;
    }

    private Class<?> getRawClass(AnnotatedType type) {
        JavaType javaType = Json.mapper().constructType(type.getType());
        return javaType != null ? javaType.getRawClass() : null;
    }

    private Schema proceedWithChain(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        return (chain.hasNext()) ? chain.next().resolve(type, context, chain) : null;
    }

    private boolean isMonetaryAmountType(Class<?> rawClass) {
        return javax.money.MonetaryAmount.class.isAssignableFrom(rawClass);
    }
}
