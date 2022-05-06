package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
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
    public Set<Class<?>> scanForComponents(String basePackage) {
        return this.scanForComponents(basePackage, null);
    }

    @Override
    public Set<Class<?>> scanForComponents(String basePackage, String configurationBasePackage) {

        Set<Class<?>> components;
        if (StringUtils.isNotBlank(basePackage) && StringUtils.isNotBlank(configurationBasePackage)) {
            log.debug("Scanning for component classes in configuration package {}, filtered by beans in {}", configurationBasePackage, basePackage);
            components = this.scanForAutoConfigurationComponents(basePackage);
            components.addAll(this.scanForComponentsInConfiguration(basePackage, configurationBasePackage));

        } else if (StringUtils.isNotBlank(configurationBasePackage)) {
            log.debug("Scanning for component classes in configuration package {}", basePackage);
            components = this.scanForComponentsInConfiguration(null, configurationBasePackage);

        } else if (StringUtils.isNotBlank(basePackage)) {
            log.debug("Scanning for component classes in {}", basePackage);
            components = this.scanForAutoConfigurationComponents(basePackage);

        } else {
            throw new IllegalArgumentException("There must be either a basePackage or configurationBasePackage given");
        }

        return components;
    }

    private Set<Class<?>> scanForAutoConfigurationComponents(String basePackage) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Component.class));
        provider.addExcludeFilter(new AnnotationTypeFilter(Configuration.class));

        return provider.findCandidateComponents(basePackage).stream()
            .map(BeanDefinition::getBeanClassName)
            .map(this::getClass)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(toSet());
    }

    private Set<Class<?>> scanForComponentsInConfiguration(String basePackage, String configurationBasePackage) {
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Configuration.class));

        return provider.findCandidateComponents(configurationBasePackage).stream()
            .map(BeanDefinition::getBeanClassName)
            .map(this::getClass)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(configurationClass -> {

                Set<Class<?>> beans = new LinkedHashSet<>();
                for (final Method method : configurationClass.getDeclaredMethods()) {
                    final Bean bean = AnnotationUtils.getAnnotation(method, Bean.class);
                    if (bean != null) {
                        final Class<?> returnType = method.getReturnType();
                        final String beanPackage = returnType.getPackage().getName();
                        if (basePackage == null || beanPackage.equals(basePackage) || beanPackage.startsWith(basePackage + ".")) {
                            beans.add(returnType);
                        }
                    }
                }

                return beans;
            })
            .flatMap(Set::stream)
            .collect(Collectors.toSet());
    }

    private Optional<Class<?>> getClass(String className) {
        try {
            log.debug("Found candidate class: {}", className);
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            log.warn("Class {} not found", className);
            return Optional.empty();
        }
    }

}
