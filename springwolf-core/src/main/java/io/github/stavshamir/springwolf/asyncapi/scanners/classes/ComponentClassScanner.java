package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ComponentClassScanner extends AbstractAnnotatedClassScanner<Component> implements ClassScanner {

    @Override
    protected Class<Component> getAnnotationClass() {
        return Component.class;
    }

}
