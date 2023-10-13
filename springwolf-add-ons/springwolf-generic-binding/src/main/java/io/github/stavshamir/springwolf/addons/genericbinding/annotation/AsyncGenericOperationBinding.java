// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.addons.genericbinding.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.AsyncOperationBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Springwolf cannot support all available protocol bindings that exist.
 * To allow users to manually define them, {@link AsyncGenericOperationBinding} can be used.
 * <p>
 * Use the {@link AsyncGenericOperationBinding#fields()} to define the attributes
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@AsyncOperationBinding
public @interface AsyncGenericOperationBinding {
    /**
     * The name of the binding
     */
    String type();

    /**
     * All binding fields
     */
    String[] fields() default {};
}
