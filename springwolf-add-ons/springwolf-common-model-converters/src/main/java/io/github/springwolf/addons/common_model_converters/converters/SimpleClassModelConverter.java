// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.converters;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;

import java.util.Iterator;

/**
 * Sometimes adding documentation annotation is not possible on classes, i.e. they are marked as final or are from external libraries.
 * This converter allows to add documentation to such classes. Duplicate the _target_ class as _native_ into your project and add documentation annotations.
 * Then, create an instance (@Bean, @Component) of this converter in your configuration mapping the classes.
 *
 * Example: {@link io.github.springwolf.addons.common_model_converters.converters.monetaryamount.MonetaryAmountConverter}
 */
public class SimpleClassModelConverter implements ModelConverter {

    private final Class<?> targetClass;
    private final Class<?> nativeClass;

    public SimpleClassModelConverter(Class<?> targetClass, Class<?> nativeClass) {
        this.targetClass = targetClass;
        this.nativeClass = nativeClass;
    }

    @Override
    public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        Class<?> rawClass = getRawClass(type);
        boolean isNativeType = isNativeType(rawClass);

        if (isNativeType) {
            type = new AnnotatedType(targetClass).resolveAsRef(true);
        }

        Schema<?> schema = proceedWithChain(type, context, chain);

        if (isNativeType && schema != null && rawClass != null) {
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

    private boolean isNativeType(Class<?> rawClass) {
        return nativeClass.isAssignableFrom(rawClass);
    }
}
