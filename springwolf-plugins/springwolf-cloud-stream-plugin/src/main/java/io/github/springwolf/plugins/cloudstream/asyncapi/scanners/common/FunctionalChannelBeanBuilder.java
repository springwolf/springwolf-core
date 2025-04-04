// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.TypeExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ResolvableType;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class FunctionalChannelBeanBuilder {
    private final TypeExtractor typeExtractor;

    public Set<FunctionalChannelBeanData> build(AnnotatedElement element) {
        Class<?> type = getRawType(element);

        if (Consumer.class.isAssignableFrom(type) || BiConsumer.class.isAssignableFrom(type)) {
            List<Type> typeGenerics = getTypeGenerics(element);
            if (typeGenerics.isEmpty()) {
                return Collections.emptySet();
            }
            Type payloadType = typeGenerics.get(0);
            return Set.of(ofConsumer(element, payloadType));
        }

        if (Supplier.class.isAssignableFrom(type)) {
            List<Type> typeGenerics = getTypeGenerics(element);
            if (typeGenerics.isEmpty()) {
                return Collections.emptySet();
            }
            Type payloadType = typeGenerics.get(0);
            return Set.of(ofSupplier(element, payloadType));
        }

        if (Function.class.isAssignableFrom(type)) {
            List<Type> typeGenerics = getTypeGenerics(element);
            if (typeGenerics.size() != 2) {
                return Collections.emptySet();
            }
            Type inputType = typeGenerics.get(0);
            Type outputType = typeGenerics.get(1);

            return Set.of(ofConsumer(element, inputType), ofSupplier(element, outputType));
        }

        if (BiFunction.class.isAssignableFrom(type)) {
            List<Type> typeGenerics = getTypeGenerics(element);
            if (typeGenerics.size() != 3) {
                return Collections.emptySet();
            }
            Type inputType = typeGenerics.get(0);
            Type outputType = typeGenerics.get(2);

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

    private static FunctionalChannelBeanData ofConsumer(AnnotatedElement element, Type payloadType) {
        String name = getElementName(element);
        String cloudStreamBinding = firstCharToLowerCase(name) + "-in-0";
        return new FunctionalChannelBeanData(
                name, element, payloadType, FunctionalChannelBeanData.BeanType.CONSUMER, cloudStreamBinding);
    }

    private static FunctionalChannelBeanData ofSupplier(AnnotatedElement element, Type payloadType) {
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

    private List<Type> getTypeGenerics(AnnotatedElement element) {
        if (element instanceof Method m) {
            Type returnType = getMethodReturnType(m);
            if (returnType instanceof ParameterizedType) {
                return getTypeGenerics(((ParameterizedType) returnType));
            } else {
                return Collections.emptyList();
            }
        }

        if (element instanceof Class<?> c) {
            ResolvableType resolvableType = ResolvableType.forClass(c);
            Type type = getParameterizedType(resolvableType);
            if (type instanceof ParameterizedType) {
                return getTypeGenerics((ParameterizedType) type);
            } else {
                return Collections.emptyList();
            }
        }

        throw new IllegalArgumentException("Must be a Method or Class");
    }

    private Type getMethodReturnType(Method m) {
        ResolvableType resolvableType = ResolvableType.forMethodReturnType(m);
        return getParameterizedType(resolvableType);
    }

    private Type getParameterizedType(ResolvableType resolvableType) {
        if (Consumer.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
            return resolvableType.as(Consumer.class).getType();
        } else if (BiConsumer.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
            return resolvableType.as(BiConsumer.class).getType();
        } else if (Supplier.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
            return resolvableType.as(Supplier.class).getType();
        } else if (Function.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
            return resolvableType.as(Function.class).getType();
        } else if (BiFunction.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
            return resolvableType.as(BiFunction.class).getType();
        }
        throw new IllegalArgumentException("Illegal type: " + resolvableType.getRawClass());
    }

    private List<Type> getTypeGenerics(ParameterizedType parameterizedType) {
        return Arrays.stream(parameterizedType.getActualTypeArguments())
                .map(typeExtractor::extractActualType)
                .toList();
    }
}
