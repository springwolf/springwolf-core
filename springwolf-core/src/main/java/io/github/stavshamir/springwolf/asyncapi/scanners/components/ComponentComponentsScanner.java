package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

@Slf4j
public class ComponentComponentsScanner extends AbstractClassPathComponentsScanner {

  private final String basePackage;

  public ComponentComponentsScanner(String basePackage) {
    if (StringUtils.isEmpty(basePackage)) {
      throw new IllegalArgumentException("There must be a non-empty basePackage given");
    }

    this.basePackage = basePackage;
  }

  @Override
  public Set<Class<?>> scanForComponents() {
    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AnnotationTypeFilter(Component.class));

    log.debug("Scanning for component classes in {}", basePackage);
    return this.filterBeanDefinitionsToClasses(provider.findCandidateComponents(basePackage))
        .collect(toSet());
  }
}
