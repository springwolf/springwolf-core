package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.google.common.collect.ImmutableSet;
import lombok.Data;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Data
class FunctionalChannelBeanData {

    private final String beanName;
    private final Class<?> payloadType;
    private final BeanType beanType;
    private final String cloudStreamBinding;

    static Set<FunctionalChannelBeanData> fromMethodBean(Method methodBean) {
        Class<?> returnType = methodBean.getReturnType();

        if (Consumer.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = (Class<?>) getReturnTypeGenerics(methodBean)[0];
            return ImmutableSet.of(ofConsumer(methodBean.getName(), payloadType));
        } else if (Supplier.class.isAssignableFrom(returnType)) {
            Class<?> payloadType = (Class<?>) getReturnTypeGenerics(methodBean)[0];
            return ImmutableSet.of(ofSupplier(methodBean.getName(), payloadType));
        } else if (Function.class.isAssignableFrom(returnType)) {
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

        Class<?> inputType = (Class<?>) getReturnTypeGenerics(methodBean)[0];
        Class<?> outputType = (Class<?>) getReturnTypeGenerics(methodBean)[1];

        return ImmutableSet.of(
                ofConsumer(name, inputType),
                ofSupplier(name, outputType)
        );
    }

    private static Type[] getReturnTypeGenerics(Method methodBean) {
        ParameterizedTypeImpl genericReturnType = (ParameterizedTypeImpl) methodBean.getGenericReturnType();
        return genericReturnType.getActualTypeArguments();
    }

    enum BeanType {
        CONSUMER,
        SUPPLIER
    }

}
