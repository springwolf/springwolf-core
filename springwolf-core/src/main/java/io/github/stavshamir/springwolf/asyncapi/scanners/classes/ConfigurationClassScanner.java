// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

public class ConfigurationClassScanner extends AbstractAnnotatedClassScanner<Configuration> implements ClassScanner {

    public ConfigurationClassScanner(AsyncApiDocketService asyncApiDocketService, Environment environment) {
        super(asyncApiDocketService, environment);
    }

    @Override
    protected Class<Configuration> getAnnotationClass() {
        return Configuration.class;
    }
}
