// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.annotations;

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

        String description() default "";

        Header[] values() default {};

        /**
         * Indicate that no headers are used in this operation.
         * <p>
         * All other properties of this annotation are ignored if this is set to true.
         */
        boolean notUsed() default false;

        @Retention(RetentionPolicy.CLASS)
        @Target({})
        @Inherited
        @interface Header {
            String name();

            String description() default "";

            String value() default "";

            /**
             * The format of the header value according to AsyncAPI specification.
             * <p>
             * Common formats include:
             * <ul>
             *   <li>"int32" - 32-bit signed integer</li>
             *   <li>"int64" - 64-bit signed integer</li>
             *   <li>"date" - RFC 3339 date</li>
             *   <li>"date-time" - RFC 3339 date-time</li>
             * </ul>
             *
             * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#dataTypeFormat">AsyncAPI Data Type Format</a>
             * @return the format string, empty by default
             */
            String format() default "";
        }
    }
}
