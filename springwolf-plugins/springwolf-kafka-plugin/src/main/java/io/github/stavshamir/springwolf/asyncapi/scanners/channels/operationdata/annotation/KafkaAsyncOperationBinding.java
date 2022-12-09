package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface KafkaAsyncOperationBinding {

    String type() default "kafka";

    String groupId() default "";

    String clientId() default "";
    String bindingVersion() default "";

}
