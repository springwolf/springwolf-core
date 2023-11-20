// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.SpringPayloadAnnotationTypeExtractor;
import lombok.Data;

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

@Data
class FunctionalChannelBeanData {

    private final String beanName;
    private final Class<?> payloadType;
    private final BeanType beanType;
    private final String cloudStreamBinding;
    private static final SpringPayloadAnnotationTypeExtractor extractor =
            new SpringPayloadAnnotationTypeExtractor(); // TODO: move

    static Set<FunctionalChannelBeanData> fromMethodBean(Method methodBean) {
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
            return fromFunctionBean(methodBean);
        }

        return Collections.emptySet();
    }

    private static FunctionalChannelBeanData ofConsumer(String name, Class<?> payloadType) {
        return new FunctionalChannelBeanData(name, payloadType, BeanType.CONSUMER, name + "-in-0");
    }

    private static FunctionalChannelBeanData ofSupplier(String name, Class<?> payloadType) {
        return new FunctionalChannelBeanData(name, payloadType, BeanType.SUPPLIER, name + "-out-0");
    }

    private static Set<FunctionalChannelBeanData> fromFunctionBean(Method methodBean) {
        String name = methodBean.getName();

        Class<?> inputType = getReturnTypeGenerics(methodBean).get(0);
        Class<?> outputType = getReturnTypeGenerics(methodBean).get(1);

        return Set.of(ofConsumer(name, inputType), ofSupplier(name, outputType));
    }

    private static List<Class<?>> getReturnTypeGenerics(Method methodBean) {
        ParameterizedType genericReturnType = (ParameterizedType) methodBean.getGenericReturnType();
        return Arrays.stream(genericReturnType.getActualTypeArguments())
                .map(extractor::typeToClass)
                .collect(toList());
    }

    enum BeanType {
        CONSUMER,
        SUPPLIER
    }
}
