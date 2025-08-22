// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @AsyncChannelBinding} is a meta-annotation used to identify Channel Binding annotations.
 * </p>
 * The annotations annotated with {@code @AsyncChannelBinding} are intended to provide the Channel Bindings
 * Object documentation. Those implementations are usually available in its own plugin, like {@code springwolf-kafka-plugin}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.ANNOTATION_TYPE})
@Inherited
public @interface AsyncChannelBinding {}
