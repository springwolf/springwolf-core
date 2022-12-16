package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Slf4j
public abstract class AbstractAnnotatedClassScanner<T extends Annotation> implements ClassScanner {

    @Autowired
    private AsyncApiDocketService asyncApiDocketService;

    /**
     * @return The class object of the annotation to scan.
     */
    protected abstract Class<T> getAnnotationClass();

    @Override
    public Set<Class<?>> scan() {
        String basePackage = asyncApiDocketService.getAsyncApiDocket().getBasePackage();
        if (StringUtils.isBlank(basePackage)) {
            throw new IllegalArgumentException("Base package must not be blank");
        }

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(getAnnotationClass()));

        log.debug("Scanning for {} classes in {}", getAnnotationClass().getSimpleName(), basePackage);
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
