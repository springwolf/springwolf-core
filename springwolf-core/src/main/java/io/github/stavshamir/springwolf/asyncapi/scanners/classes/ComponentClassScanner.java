package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComponentClassScanner extends AbstractAnnotatedClassScanner<Component> implements ClassScanner {

    public ComponentClassScanner(AsyncApiDocketService asyncApiDocketService, Optional<Environment> environment) {
        super(asyncApiDocketService, environment);
    }

    @Override
    protected Class<Component> getAnnotationClass() {
        return Component.class;
    }
}
