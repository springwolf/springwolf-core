package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * One individual binding field with a name and a value
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface AsyncGenericBindingField {
    /**
     * The name of the field.
     * <p>
     * Nested attributes and arrays can be specified through a jsonpath like syntax
     */
    String name();


    /**
     * The field value
     */
    String value();
}
