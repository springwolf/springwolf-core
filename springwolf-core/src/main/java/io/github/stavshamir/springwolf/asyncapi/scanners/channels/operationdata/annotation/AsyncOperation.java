// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaderSchema;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation is mapped to {@link OperationData}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
@Inherited
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
     * Mapped to {@link OperationData#getMessage()}
     */
    AsyncMessage message() default @AsyncMessage();

    /**
     * The servers on which this channel is available, list of names of Server Objects.
     * If servers is empty then this channel is available on all servers defined in the Async API.
     * Mapped to ({@link OperationData#getServers()}
     * @return
     */
    String[] servers() default {};

    /**
     * Overwrite the Springwolf auto-detected payload type.
     * <p>
     * Mapped to {@link OperationData#getPayloadType()}
     */
    Class<?> payloadType() default Object.class;

    /**
     * Mapped to {@link OperationData#getHeaders()}
     */
    Headers headers() default @Headers();

    @Retention(RetentionPolicy.CLASS)
    @Target({})
    @Inherited
    public @interface Headers {
        /**
         * Mapped to {@link AsyncHeaders#getSchemaName()}
         */
        String schemaName() default "";

        Header[] values() default {};

        @Retention(RetentionPolicy.CLASS)
        @Target({})
        @Inherited
        public @interface Header {
            /**
             * Mapped to {@link AsyncHeaderSchema#getHeaderName()}
             */
            String name();

            /**
             * Mapped to {@link AsyncHeaderSchema#getDescription()}
             */
            String description() default "";

            /**
             * Mapped to {@link AsyncHeaderSchema#getDefault()}
             */
            String value();
        }
    }
}
