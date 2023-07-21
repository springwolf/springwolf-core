package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationClassScanner extends AbstractAnnotatedClassScanner<Configuration> implements ClassScanner {

    @Override
    protected Class<Configuration> getAnnotationClass() {
        return Configuration.class;
    }
}
