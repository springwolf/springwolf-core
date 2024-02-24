// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @AsyncOperationBinding} is a meta-annotation used to identify Operation Binding annotations.
 * </p>
 * The annotations annotated with {@code @AsyncOperationBinding} are intended to provide the Operation Bindings
 * Object documentation. Those implementations are usually available in its own plugin, like {@code springwolf-kafka-plugin}
 * or {@code springwolf-amqp-plugin}
 *
 * Maintainer note: Move to io.github.springwolf.core.asyncapi.scanners.bindings
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.ANNOTATION_TYPE})
@Inherited
public @interface AsyncOperationBinding {}
