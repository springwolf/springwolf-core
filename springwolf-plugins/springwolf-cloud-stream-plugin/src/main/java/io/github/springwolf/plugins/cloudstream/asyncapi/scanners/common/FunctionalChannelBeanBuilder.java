// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.TypeExtractor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ResolvableType;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        final AnnotatedClassOrMethod annotatedClassOrMethod = getAnnotatedClassOrMethodOrThrow(element, typeExtractor);

        Class<?> type = annotatedClassOrMethod.getRawType();

        if (Consumer.class.isAssignableFrom(type) || BiConsumer.class.isAssignableFrom(type)) {
            List<Type> typeGenerics = annotatedClassOrMethod.getTypeGenerics();
            if (typeGenerics.isEmpty()) {
                return Collections.emptySet();
            }
            Type payloadType = typeGenerics.get(0);
            return Set.of(ofConsumer(annotatedClassOrMethod, payloadType));
        }

        if (Supplier.class.isAssignableFrom(type)) {
            List<Type> typeGenerics = annotatedClassOrMethod.getTypeGenerics();
            if (typeGenerics.isEmpty()) {
                return Collections.emptySet();
            }
            Type payloadType = typeGenerics.get(0);
            return Set.of(ofSupplier(annotatedClassOrMethod, payloadType));
        }

        if (Function.class.isAssignableFrom(type)) {
            List<Type> typeGenerics = annotatedClassOrMethod.getTypeGenerics();
            if (typeGenerics.size() != 2) {
                return Collections.emptySet();
            }
            Type inputType = typeGenerics.get(0);
            Type outputType = typeGenerics.get(1);

            return Set.of(
                    ofConsumer(annotatedClassOrMethod, inputType), ofSupplier(annotatedClassOrMethod, outputType));
        }

        if (BiFunction.class.isAssignableFrom(type)) {
            List<Type> typeGenerics = annotatedClassOrMethod.getTypeGenerics();
            if (typeGenerics.size() != 3) {
                return Collections.emptySet();
            }
            Type inputType = typeGenerics.get(0);
            Type outputType = typeGenerics.get(2);

            return Set.of(
                    ofConsumer(annotatedClassOrMethod, inputType), ofSupplier(annotatedClassOrMethod, outputType));
        }

        return Collections.emptySet();
    }

    private static AnnotatedClassOrMethod getAnnotatedClassOrMethodOrThrow(
            AnnotatedElement annotatedElement, TypeExtractor typeExtractor) {
        if (annotatedElement instanceof Method m) {
            return AnnotatedClassOrMethod.forMethod(annotatedElement, m, typeExtractor);
        }

        if (annotatedElement instanceof Class<?> c) {
            return AnnotatedClassOrMethod.forClass(annotatedElement, c, typeExtractor);
        }

        throw new IllegalArgumentException("Must be a Method or Class");
    }

    private static FunctionalChannelBeanData ofConsumer(
            AnnotatedClassOrMethod annotatedClassOrMethod, Type payloadType) {
        String name = annotatedClassOrMethod.getName();
        String cloudStreamBinding = firstCharToLowerCase(name) + "-in-0";
        return new FunctionalChannelBeanData(
                name,
                annotatedClassOrMethod.annotatedElement,
                payloadType,
                FunctionalChannelBeanData.BeanType.CONSUMER,
                cloudStreamBinding);
    }

    private static FunctionalChannelBeanData ofSupplier(
            AnnotatedClassOrMethod annotatedClassOrMethod, Type payloadType) {
        String name = annotatedClassOrMethod.getName();
        String cloudStreamBinding = firstCharToLowerCase(name) + "-out-0";
        return new FunctionalChannelBeanData(
                name,
                annotatedClassOrMethod.annotatedElement,
                payloadType,
                FunctionalChannelBeanData.BeanType.SUPPLIER,
                cloudStreamBinding);
    }

    private static String firstCharToLowerCase(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class AnnotatedClassOrMethod {

        private final AnnotatedElement annotatedElement;
        private final String name;
        private final Class<?> rawType;
        private final List<Type> typeGenerics;

        public static AnnotatedClassOrMethod forClass(
                AnnotatedElement element, Class<?> clazz, TypeExtractor typeExtractor) {
            return new AnnotatedClassOrMethod(
                    element, clazz.getSimpleName(), clazz, getTypeGenericsForClass(clazz, typeExtractor));
        }

        public static AnnotatedClassOrMethod forMethod(
                AnnotatedElement element, Method method, TypeExtractor typeExtractor) {
            return new AnnotatedClassOrMethod(
                    element, method.getName(), method.getReturnType(), getTypeGenericsForMethod(method, typeExtractor));
        }

        private static List<Type> getTypeGenericsForClass(Class<?> clazz, TypeExtractor typeExtractor) {

            ResolvableType resolvableType = ResolvableType.forClass(clazz);
            Optional<Type> type = getParameterizedType(resolvableType);
            if (type.isPresent() && type.get() instanceof ParameterizedType) {
                return getTypeGenerics((ParameterizedType) type.get(), typeExtractor);
            } else {
                return Collections.emptyList();
            }
        }

        private static List<Type> getTypeGenericsForMethod(Method method, TypeExtractor typeExtractor) {

            Optional<Type> returnType = getMethodReturnType(method);
            if (returnType.isPresent() && returnType.get() instanceof ParameterizedType) {
                return getTypeGenerics(((ParameterizedType) returnType.get()), typeExtractor);
            } else {
                return Collections.emptyList();
            }
        }

        private static List<Type> getTypeGenerics(ParameterizedType parameterizedType, TypeExtractor typeExtractor) {
            return Arrays.stream(parameterizedType.getActualTypeArguments())
                    .map(typeExtractor::extractActualType)
                    .toList();
        }

        private static Optional<Type> getParameterizedType(ResolvableType resolvableType) {

            if (Consumer.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
                return Optional.of(resolvableType.as(Consumer.class).getType());
            } else if (BiConsumer.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
                return Optional.of(resolvableType.as(BiConsumer.class).getType());
            } else if (Supplier.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
                return Optional.of(resolvableType.as(Supplier.class).getType());
            } else if (Function.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
                return Optional.of(resolvableType.as(Function.class).getType());
            } else if (BiFunction.class.isAssignableFrom(resolvableType.resolve(Object.class))) {
                return Optional.of(resolvableType.as(BiFunction.class).getType());
            }
            return Optional.empty();
        }

        private static Optional<Type> getMethodReturnType(Method m) {
            ResolvableType resolvableType = ResolvableType.forMethodReturnType(m);
            return getParameterizedType(resolvableType);
        }
    }
}
