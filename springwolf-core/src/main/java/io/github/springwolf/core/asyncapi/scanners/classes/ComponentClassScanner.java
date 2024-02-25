// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.classes;

import io.github.springwolf.core.configuration.AsyncApiDocketService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Set;

public class ComponentClassScanner implements ClassScanner {

    private final AnnotationClassScanner<Component> scanner;

    public ComponentClassScanner(AsyncApiDocketService asyncApiDocketService, Environment environment) {
        this.scanner = new AnnotationClassScanner<>(Component.class, asyncApiDocketService, environment);
    }

    @Override
    public Set<Class<?>> scan() {
        return scanner.scan();
    }
}
