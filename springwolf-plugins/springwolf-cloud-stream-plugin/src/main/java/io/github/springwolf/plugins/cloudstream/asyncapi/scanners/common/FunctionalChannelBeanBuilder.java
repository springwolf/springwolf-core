// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import io.github.springwolf.plugins.cloudstream.annotation.GooglePubSubSchemaSetting;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class FunctionalChannelBeanBuilder {
    private final PayloadClassExtractor extractor;

    public Set<FunctionalChannelBeanData> fromMethodBean(Method methodBean) {
        Class<?> returnType = methodBean.getReturnType();
        Annotation schemaSetting = methodBean.getAnnotation(GooglePubSubSchemaSetting.class);
        if (Consumer.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = getReturnTypeGenerics(methodBean).get(0);
            return Set.of(ofConsumer(methodBean.getName(), payloadType, schemaSetting));
        }

        if (Supplier.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = getReturnTypeGenerics(methodBean).get(0);
            return Set.of(ofSupplier(methodBean.getName(), payloadType, schemaSetting));
        }

        if (Function.class.isAssignableFrom(returnType)) {
            Class<?> inputType = getReturnTypeGenerics(methodBean).get(0);
            Class<?> outputType = getReturnTypeGenerics(methodBean).get(1);

            return Set.of(
                    ofConsumer(methodBean.getName(), inputType, schemaSetting),
                    ofSupplier(methodBean.getName(), outputType, schemaSetting));
        }

        return Collections.emptySet();
    }

    private static FunctionalChannelBeanData ofConsumer(String name, Class<?> payloadType, Annotation schemaSetting) {
        return new FunctionalChannelBeanData(
                name, payloadType, FunctionalChannelBeanData.BeanType.CONSUMER, name + "-in-0", schemaSetting);
    }

    private static FunctionalChannelBeanData ofSupplier(String name, Class<?> payloadType, Annotation schemaSetting) {
        return new FunctionalChannelBeanData(
                name, payloadType, FunctionalChannelBeanData.BeanType.SUPPLIER, name + "-out-0", schemaSetting);
    }

    private List<Class<?>> getReturnTypeGenerics(Method methodBean) {
        ParameterizedType genericReturnType = (ParameterizedType) methodBean.getGenericReturnType();
        return Arrays.stream(genericReturnType.getActualTypeArguments())
                .map(extractor::typeToClass)
                .collect(toList());
    }
}
