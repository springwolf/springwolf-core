// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Marker interface to indicate the actual payload class in envelop wrapper classes.
 * <p>
 * Since primitive data types cannot be documented directly (like String, Integer, etc.), an envelope class is needed:
 * <pre>{@code
 * public class StringEnvelop {
 *   @AsyncApiPayload
 *   @Schema(description = "Payload description using @Schema annotation", maxLength = 10)
 *   String payload;
 * }
 * }</pre>
 * <p>
 * In case the signature of the listener/publisher method can not be changed to use the envelope class,
 * the envelope class can be set as {@link AsyncOperation#payloadType()}
 * in the {@link AsyncListener} and/or {@link AsyncPublisher} annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD})
@Inherited
public @interface AsyncApiPayload {}
