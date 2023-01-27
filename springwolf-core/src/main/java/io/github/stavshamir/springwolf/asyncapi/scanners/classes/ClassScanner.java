package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;

import java.util.Set;

public interface ClassScanner {

    /**
     * Scan your project for components potentially containing asynchronous listeners, based on the
     * configuration of your registered {@link AsyncApiDocket}.
     *
     * @return A set of found classes.
     */
    Set<Class<?>> scan();
}
