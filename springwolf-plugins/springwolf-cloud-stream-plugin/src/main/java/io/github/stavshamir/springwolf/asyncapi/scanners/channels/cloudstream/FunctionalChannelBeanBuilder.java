// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.SpringPayloadAnnotationTypeExtractor;
import lombok.RequiredArgsConstructor;

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
    private final SpringPayloadAnnotationTypeExtractor extractor;

    public Set<FunctionalChannelBeanData> fromMethodBean(Method methodBean) {
        Class<?> returnType = methodBean.getReturnType();

        if (Consumer.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = getReturnTypeGenerics(methodBean).get(0);
            return Set.of(ofConsumer(methodBean.getName(), payloadType));
        }

        if (Supplier.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = getReturnTypeGenerics(methodBean).get(0);
            return Set.of(ofSupplier(methodBean.getName(), payloadType));
        }

        if (Function.class.isAssignableFrom(returnType)) {
            Class<?> inputType = getReturnTypeGenerics(methodBean).get(0);
            Class<?> outputType = getReturnTypeGenerics(methodBean).get(1);

            return Set.of(ofConsumer(methodBean.getName(), inputType), ofSupplier(methodBean.getName(), outputType));
        }

        return Collections.emptySet();
    }

    private static FunctionalChannelBeanData ofConsumer(String name, Class<?> payloadType) {
        return new FunctionalChannelBeanData(
                name, payloadType, FunctionalChannelBeanData.BeanType.CONSUMER, name + "-in-0");
    }

    private static FunctionalChannelBeanData ofSupplier(String name, Class<?> payloadType) {
        return new FunctionalChannelBeanData(
                name, payloadType, FunctionalChannelBeanData.BeanType.SUPPLIER, name + "-out-0");
    }

    private List<Class<?>> getReturnTypeGenerics(Method methodBean) {
        ParameterizedType genericReturnType = (ParameterizedType) methodBean.getGenericReturnType();
        return Arrays.stream(genericReturnType.getActualTypeArguments())
                .map(extractor::typeToClass)
                .collect(toList());
    }
}
