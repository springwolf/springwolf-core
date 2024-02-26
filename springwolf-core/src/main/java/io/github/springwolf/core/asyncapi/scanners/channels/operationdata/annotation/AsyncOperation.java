// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
@Inherited
public @interface AsyncOperation {
    String channelName();

    String description() default "";

    AsyncMessage message() default @AsyncMessage();

    /**
     * The servers on which this channel is available, list of names of Server Objects.
     * If servers is empty then this channel is available on all servers defined in the Async API.
     */
    String[] servers() default {};

    /**
     * Overwrite the Springwolf auto-detected payload type.
     */
    Class<?> payloadType() default Object.class;

    Headers headers() default @Headers();

    @Retention(RetentionPolicy.CLASS)
    @Target({})
    @Inherited
    @interface Headers {
        String schemaName() default "";

        Header[] values() default {};

        @Retention(RetentionPolicy.CLASS)
        @Target({})
        @Inherited
        @interface Header {
            String name();

            String description() default "";

            String value();
        }
    }
}
