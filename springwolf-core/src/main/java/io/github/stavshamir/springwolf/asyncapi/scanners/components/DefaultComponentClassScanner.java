package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultComponentClassScanner implements ComponentClassScanner {

    private final AsyncApiDocket docket;

    @Override
    public Set<Class<?>> scanForComponents() {
        String basePackage = docket.getBasePackage();
        if (StringUtils.isBlank(basePackage)) {
            throw new IllegalArgumentException("Base package must not be blank");
        }

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Component.class));

        log.debug("Scanning for @Component classes in {}", basePackage);
        return provider.findCandidateComponents(basePackage).stream()
                .map(BeanDefinition::getBeanClassName)
                .map(this::getClass)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toSet());
    }

    private Optional<Class<?>> getClass(String className) {
        try {
            log.debug("Found candidate class: {}", className);
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            log.warn("Failed to get class for name: {}", className);
            return Optional.empty();
        }
    }

}
