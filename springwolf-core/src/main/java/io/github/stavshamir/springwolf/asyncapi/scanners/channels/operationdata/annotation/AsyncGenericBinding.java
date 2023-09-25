package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Springwolf cannot support all available protocol bindings that exist.
 * To allow users to manually define them, {@link AsyncGenericBinding} can be used.
 * <p>
 * Use the {@link AsyncGenericBinding#fields()} to define the attributes
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface AsyncGenericBinding {
    /**
     * The name of the binding
     */
    String name();

    /**
     * All binding fields
     */
    AsyncGenericBindingField[] fields() default {};
}
