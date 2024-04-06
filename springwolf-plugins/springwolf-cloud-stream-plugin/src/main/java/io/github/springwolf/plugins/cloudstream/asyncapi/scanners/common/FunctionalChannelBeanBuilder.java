// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class FunctionalChannelBeanBuilder {
    private final PayloadClassExtractor extractor;

    public Set<FunctionalChannelBeanData> build(AnnotatedElement element) {
        Class<?> type = getRawType(element);

        if (Consumer.class.isAssignableFrom(type)) {
            Class<?> payloadType = getTypeGenerics(element).get(0);
            return Set.of(ofConsumer(element, payloadType));
        }

        if (Supplier.class.isAssignableFrom(type)) {
            Class<?> payloadType = getTypeGenerics(element).get(0);
            return Set.of(ofSupplier(element, payloadType));
        }

        if (Function.class.isAssignableFrom(type)) {
            Class<?> inputType = getTypeGenerics(element).get(0);
            Class<?> outputType = getTypeGenerics(element).get(1);

            return Set.of(ofConsumer(element, inputType), ofSupplier(element, outputType));
        }

        return Collections.emptySet();
    }

    private static Class<?> getRawType(AnnotatedElement element) {
        if (element instanceof Method m) {
            return m.getReturnType();
        }

        if (element instanceof Class<?> c) {
            return c;
        }

        throw new IllegalArgumentException("Must be a Method or Class");
    }

    private static FunctionalChannelBeanData ofConsumer(AnnotatedElement element, Class<?> payloadType) {
        String name = getElementName(element);
        String cloudStreamBinding = firstCharToLowerCase(name) + "-in-0";
        return new FunctionalChannelBeanData(
                name, element, payloadType, FunctionalChannelBeanData.BeanType.CONSUMER, cloudStreamBinding);
    }

    private static FunctionalChannelBeanData ofSupplier(AnnotatedElement element, Class<?> payloadType) {
        String name = getElementName(element);
        String cloudStreamBinding = firstCharToLowerCase(name) + "-out-0";
        return new FunctionalChannelBeanData(
                name, element, payloadType, FunctionalChannelBeanData.BeanType.SUPPLIER, cloudStreamBinding);
    }

    private static String firstCharToLowerCase(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    private static String getElementName(AnnotatedElement element) {
        if (element instanceof Method m) {
            return m.getName();
        }

        if (element instanceof Class<?> c) {
            return c.getSimpleName();
        }

        throw new IllegalArgumentException("Must be a Method or Class");
    }

    private List<Class<?>> getTypeGenerics(AnnotatedElement element) {
        if (element instanceof Method m) {
            ParameterizedType genericReturnType = (ParameterizedType) m.getGenericReturnType();
            return getTypeGenerics(genericReturnType);
        }

        if (element instanceof Class<?> c) {
            return getTypeGenerics(c);
        }

        throw new IllegalArgumentException("Must be a Method or Class");
    }

    private List<Class<?>> getTypeGenerics(Class<?> c) {
        Predicate<Class<?>> isConsumerPredicate = Consumer.class::isAssignableFrom;
        Predicate<Class<?>> isSupplierPredicate = Supplier.class::isAssignableFrom;
        Predicate<Class<?>> isFunctionPredicate = Function.class::isAssignableFrom;
        Predicate<Class<?>> hasFunctionalInterfacePredicate =
                isConsumerPredicate.or(isSupplierPredicate).or(isFunctionPredicate);

        return Arrays.stream(c.getGenericInterfaces())
                .filter(type -> type instanceof ParameterizedType)
                .map(type -> (ParameterizedType) type)
                .filter(type -> type.getRawType() instanceof Class<?>)
                .filter(type -> hasFunctionalInterfacePredicate.test((Class<?>) type.getRawType()))
                .map(this::getTypeGenerics)
                .findFirst()
                .orElse(Collections.emptyList());
    }

    private List<Class<?>> getTypeGenerics(ParameterizedType parameterizedType) {
        return Arrays.stream(parameterizedType.getActualTypeArguments())
                .map(extractor::typeToClass)
                .collect(toList());
    }
}
