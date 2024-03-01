// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.classes;

import io.github.springwolf.core.configuration.docket.AsyncApiDocket;

import java.util.Set;

public interface ClassScanner {

    /**
     * Scan your project for components potentially containing asynchronous listeners,
     * based on the configuration of your registered {@link AsyncApiDocket}.
     *
     * @return A set of found classes.
     */
    Set<Class<?>> scan();
}
