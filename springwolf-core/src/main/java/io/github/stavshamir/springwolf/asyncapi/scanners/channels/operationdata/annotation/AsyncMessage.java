package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message.DEFAULT_SCHEMA_FORMAT;

/**
 * Annotation is mapped to {@link Message}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface AsyncMessage {
    /**
     * Mapped to {@link Message#getDescription()}
     */
    String description() default "";

    /**
     * Mapped to {@link Message#getMessageId()}
     */
    String messageId() default "";

    /**
     * Mapped to {@link Message#getName()}
     */
    String name() default "";

    /**
     * Mapped to {@link Message#getSchemaFormat()}
     */
    String schemaFormat() default DEFAULT_SCHEMA_FORMAT;

    /**
     * Mapped to {@link Message#getTitle()}
     */
    String title() default "";
}
