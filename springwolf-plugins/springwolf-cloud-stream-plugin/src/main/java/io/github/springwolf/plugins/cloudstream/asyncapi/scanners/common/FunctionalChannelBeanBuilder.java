// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
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
    private final PayloadClassExtractor extractor;

    public Set<FunctionalChannelBeanData> fromMethodBean(Method methodBean) {
        Class<?> returnType = methodBean.getReturnType();
        if (Consumer.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = getReturnTypeGenerics(methodBean).get(0);
            return Set.of(ofConsumer(methodBean, payloadType));
        }

        if (Supplier.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = getReturnTypeGenerics(methodBean).get(0);
            return Set.of(ofSupplier(methodBean, payloadType));
        }

        if (Function.class.isAssignableFrom(returnType)) {
            Class<?> inputType = getReturnTypeGenerics(methodBean).get(0);
            Class<?> outputType = getReturnTypeGenerics(methodBean).get(1);

            return Set.of(ofConsumer(methodBean, inputType), ofSupplier(methodBean, outputType));
        }

        return Collections.emptySet();
    }

    private static FunctionalChannelBeanData ofConsumer(Method method, Class<?> payloadType) {
        return new FunctionalChannelBeanData(
                method, payloadType, FunctionalChannelBeanData.BeanType.CONSUMER, method.getName() + "-in-0");
    }

    private static FunctionalChannelBeanData ofSupplier(Method method, Class<?> payloadType) {
        return new FunctionalChannelBeanData(
                method, payloadType, FunctionalChannelBeanData.BeanType.SUPPLIER, method.getName() + "-out-0");
    }

    private List<Class<?>> getReturnTypeGenerics(Method methodBean) {
        ParameterizedType genericReturnType = (ParameterizedType) methodBean.getGenericReturnType();
        return Arrays.stream(genericReturnType.getActualTypeArguments())
                .map(extractor::typeToClass)
                .collect(toList());
    }
}
