// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

public class ComponentClassScanner extends AbstractAnnotatedClassScanner<Component> implements ClassScanner {

    public ComponentClassScanner(AsyncApiDocketService asyncApiDocketService, Environment environment) {
        super(asyncApiDocketService, environment);
    }

    @Override
    protected Class<Component> getAnnotationClass() {
        return Component.class;
    }
}
