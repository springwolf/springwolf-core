package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@Slf4j
public class ConfigurationComponentsScanner extends AbstractClassPathComponentsScanner {

  private final String basePackage;
  private final Predicate<Class<?>> classPredicate;

  public ConfigurationComponentsScanner(String basePackage) {
    this(basePackage, it -> true);
  }

  public ConfigurationComponentsScanner(String basePackage, Predicate<Class<?>> classPredicate) {
    if (StringUtils.isEmpty(basePackage)) {
      throw new IllegalArgumentException("There must be a non-empty basePackage given");
    }

    this.basePackage = basePackage;
    this.classPredicate = classPredicate;
  }

  @Override
  public Set<Class<?>> scanForComponents() {
    final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AnnotationTypeFilter(Configuration.class));

    log.debug("Scanning for component classes in configuration package {}", basePackage);
    return this.filterBeanDefinitionsToClasses(provider.findCandidateComponents(basePackage))
        .map(configurationClass -> {

          Set<Class<?>> beans = new LinkedHashSet<>();
          for (final Method method : configurationClass.getDeclaredMethods()) {
            final Bean bean = AnnotationUtils.getAnnotation(method, Bean.class);
            if (bean != null) {
              final Class<?> returnType = method.getReturnType();
              if (this.classPredicate.test(returnType) && this.isSuitableComponent(returnType)) {
                beans.add(returnType);
              }
            }
          }

          return beans;
        })
        .flatMap(Set::stream)
        .collect(Collectors.toSet());
  }
}
