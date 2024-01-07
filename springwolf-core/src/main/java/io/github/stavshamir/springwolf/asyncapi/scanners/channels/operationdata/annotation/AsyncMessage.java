// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation is mapped to {@link MessageObject}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Inherited
public @interface AsyncMessage {
    /**
     * Mapped to {@link MessageObject#getDescription()}
     */
    String description() default "";

    /**
     * Mapped to {@link MessageObject#getMessageId()}
     */
    String messageId() default "";

    /**
     * Mapped to {@link MessageObject#getName()}
     */
    String name() default "";

    /**
     * Mapped to {@link MessageObject#getContentType()}
     */
    String contentType() default "application/json";

    /**
     * Mapped to {@link MessageObject#getTitle()}
     */
    String title() default "";
}
