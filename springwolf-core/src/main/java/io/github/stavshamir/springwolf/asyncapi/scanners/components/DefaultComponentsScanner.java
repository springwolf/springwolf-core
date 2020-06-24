package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import lombok.extern.slf4j.Slf4j;
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
public class DefaultComponentsScanner implements ComponentsScanner {

    @Override
    public Set<Class<?>> scanForComponents(Package basePackage) {
        return scanForComponents(basePackage.getName());
    }

    @Override
    public Set<Class<?>> scanForComponents(String basePackage) {
        log.debug("Scanning for component classes in {}", basePackage);

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Component.class));

        return provider.findCandidateComponents(basePackage).stream()
                .map(BeanDefinition::getBeanClassName)
                .peek(className -> log.debug("Found candidate class: {}", className))
                .map(this::getClass)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toSet());
    }

    private Optional<Class<?>> getClass(String className) {
        try {
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            log.warn("Class {} not found", className);
            return Optional.empty();
        }
    }

}
