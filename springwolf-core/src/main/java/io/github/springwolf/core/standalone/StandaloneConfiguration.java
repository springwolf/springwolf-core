// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation for Springwolf standalone configurations.
 * <p>
 * Those classes can be discovered by {@link StandaloneConfigurationDiscoverer}.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StandaloneConfiguration {}
