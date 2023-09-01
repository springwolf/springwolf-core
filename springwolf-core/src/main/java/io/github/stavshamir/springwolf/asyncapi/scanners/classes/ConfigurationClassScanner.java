package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurationClassScanner extends AbstractAnnotatedClassScanner<Configuration> implements ClassScanner {

    public ConfigurationClassScanner(AsyncApiDocketService asyncApiDocketService, Optional<Environment> environment) {
        super(asyncApiDocketService, environment);
    }

    @Override
    protected Class<Configuration> getAnnotationClass() {
        return Configuration.class;
    }
}
