package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import java.util.Set;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;

public interface ClassScanner {

    /**
     * Scan your project for components potentially containing asynchronous listeners,
     * based on the configuration of your registered {@link AsyncApiDocket}.
     *
     * @return A set of found classes.
     */
    Set<Class<?>> scan();
}
