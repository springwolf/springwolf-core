package io.github.stavshamir.springwolf.asyncapi.scanners.beans;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class BeanMethodsScanner {

    private final AsyncApiDocket docket;

    private Optional<Class<?>> getClass(String className) {
        try {
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    public Set<Method> getBeanMethods() {
        return getConfigurationClasses()
                .map(Class::getDeclaredMethods)
                .map(Arrays::asList)
                .flatMap(List::stream)
                .filter(method -> method.isAnnotationPresent(Bean.class))
                .collect(toSet());
    }

    private Stream<? extends Class<?>> getConfigurationClasses() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Configuration.class));

        return provider.findCandidateComponents("").stream()
                .map(BeanDefinition::getBeanClassName)
                .map(this::getClass)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

}
