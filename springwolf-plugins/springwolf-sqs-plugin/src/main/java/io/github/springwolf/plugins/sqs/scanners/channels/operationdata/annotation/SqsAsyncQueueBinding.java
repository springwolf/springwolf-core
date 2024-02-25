// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.scanners.channels.operationdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Inherited
public @interface SqsAsyncQueueBinding {

    String name() default "";

    boolean fifoQueue() default true;

    int deliveryDelay() default 0;
}
