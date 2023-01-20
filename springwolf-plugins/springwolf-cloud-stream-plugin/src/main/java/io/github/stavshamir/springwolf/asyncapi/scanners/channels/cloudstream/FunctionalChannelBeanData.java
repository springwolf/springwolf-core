package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

import com.google.common.collect.ImmutableSet;
import lombok.Data;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

    static Set<FunctionalChannelBeanData> fromMethodBean(Method methodBean) {
        Class<?> returnType = methodBean.getReturnType();

        if (Consumer.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = getReturnTypeGenerics(methodBean).get(0);
            return ImmutableSet.of(ofConsumer(methodBean.getName(), payloadType));
        }

        if (Supplier.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = getReturnTypeGenerics(methodBean).get(0);
            return ImmutableSet.of(ofSupplier(methodBean.getName(), payloadType));
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

    private static ImmutableSet<FunctionalChannelBeanData> fromFunctionBean(Method methodBean) {
        String name = methodBean.getName();

        Class<?> inputType = getReturnTypeGenerics(methodBean).get(0);
        Class<?> outputType = getReturnTypeGenerics(methodBean).get(1);

        return ImmutableSet.of(
                ofConsumer(name, inputType),
                ofSupplier(name, outputType)
        );
    }

    private static List<Class<?>> getReturnTypeGenerics(Method methodBean) {
        ParameterizedType genericReturnType = (ParameterizedType) methodBean.getGenericReturnType();
        return Arrays.stream(genericReturnType.getActualTypeArguments())
                .map(FunctionalChannelBeanData::toClassObject)
                .collect(toList());
    }

    private static Class<?> toClassObject(Type type) {
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        }

        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();

            if ("org.apache.kafka.streams.kstream.KStream".equals(rawType.getTypeName())) {
                return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[1];
            }

            return (Class<?>) rawType;
        }

        throw new IllegalArgumentException("Cannot handle Type which is not Class or ParameterizedType, but was given: " + type.getClass());
    }

    enum BeanType {
        CONSUMER,
        SUPPLIER
    }

}
