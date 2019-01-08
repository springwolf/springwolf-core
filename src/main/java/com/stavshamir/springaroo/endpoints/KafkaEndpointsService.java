package com.stavshamir.springaroo.endpoints;

import com.stavshamir.springaroo.Docket;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
public class KafkaEndpointsService {

    private final String basePackage;
    private final KafkaListenersScanner scanner;

    @Getter(lazy = true)
    private final Set<KafkaEndpoint> endpoints = scanPackageForKafkaEndpoints();

    @Autowired
    public KafkaEndpointsService(Docket docket, KafkaListenersScanner kafkaListenersScanner) {
        this.basePackage = docket.getBasePackage();
        this.scanner = kafkaListenersScanner;
    }

    private Set<KafkaEndpoint> scanPackageForKafkaEndpoints() {
        if (scanner == null) {
            log.error("Called before injection of KafkaListenerScanner");
            return Collections.emptySet();
        }

        return getClassesAnnotatedWithComponent().stream()
                    .map(scanner::getKafkaEndpoints)
                    .flatMap(Collection::stream)
                    .peek(endpoint -> log.debug("Registered endpoint: {}", endpoint))
                    .collect(toSet());
    }

    private Set<Class<?>> getClassesAnnotatedWithComponent() {
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
            log.error("Class {} not found", className);
        }

        return Optional.empty();
    }

}
