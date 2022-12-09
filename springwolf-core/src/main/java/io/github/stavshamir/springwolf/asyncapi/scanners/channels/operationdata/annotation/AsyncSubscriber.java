package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation is mapped to {@link ConsumerData}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface AsyncSubscriber {
    /**
     * Mapped to {@link OperationData}
     */
    AsyncOperation operation();
}
