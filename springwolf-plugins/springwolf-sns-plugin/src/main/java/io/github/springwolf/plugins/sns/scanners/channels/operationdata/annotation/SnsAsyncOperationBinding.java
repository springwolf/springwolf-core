// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.scanners.channels.operationdata.annotation;

import io.github.springwolf.core.asyncapi.scanners.channels.annotation.AsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @SnsAsyncOperationBinding} is a method-level annotation used in combination with {@link AsyncPublisher} or {@link AsyncListener}.
 * It configures the operation binding for the SNS protocol.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@AsyncOperationBinding
@Inherited
public @interface SnsAsyncOperationBinding {

    String type() default "sns";

    String protocol();

    SnsAsyncOperationBindingIdentifier endpoint();

    boolean rawMessageDelivery() default true;
}
