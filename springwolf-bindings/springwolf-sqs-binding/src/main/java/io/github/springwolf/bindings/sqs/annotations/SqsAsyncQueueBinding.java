// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sqs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ANNOTATION_TYPE only needed to be usable from SQS Plugin Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Inherited
public @interface SqsAsyncQueueBinding {

    String name() default "";

    boolean fifoQueue() default true;

    int deliveryDelay() default 0;
}
