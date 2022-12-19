package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;


import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaderSchema;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation is mapped to {@link OperationData}
 */
@Retention(RetentionPolicy.CLASS)
@Target({})
public @interface AsyncOperation {
    /**
     * Mapped to {@link OperationData#getChannelName()}
     */
    String channelName();

    /**
     * Mapped to {@link OperationData#getDescription()}
     */
    String description() default "";

    /**
     * Mapped to {@link OperationData#getPayloadType()}
     */
    Class<?> payloadType() default Object.class;

    /**
     * Mapped to {@link OperationData#getHeaders()}
     */
    Headers headers() default @Headers();

    @Retention(RetentionPolicy.CLASS)
    @Target({})
    @interface Headers {
        /**
         * Mapped to {@link AsyncHeaders#getSchemaName()}
         */
        String schemaName() default "";

        Header[] values() default {};

        @Retention(RetentionPolicy.CLASS)
        @Target({})
        @interface Header {
            /**
             * Mapped to {@link AsyncHeaderSchema#getHeaderName()}
             */
            String name();

            /**
             * Mapped to {@link AsyncHeaderSchema#getDescription()} ()}
             */
            String description() default "";

            /**
             * Mapped to {@link AsyncHeaderSchema#getDefault()}
             */
            String value();
        }
    }
}
