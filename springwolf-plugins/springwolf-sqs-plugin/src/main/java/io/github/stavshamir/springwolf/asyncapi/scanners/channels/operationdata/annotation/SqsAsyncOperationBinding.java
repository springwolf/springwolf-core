package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @SqsAsyncOperationBinding} is a method-level annotation used in combination with {@link AsyncPublisher} or {@link AsyncListener}.
 * It configures the operation binding for the SQS protocol.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface SqsAsyncOperationBinding {

    String type() default "sqs";
}
