package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;

@Slf4j
public abstract class AbstractClassPathComponentsScanner implements ComponentsScanner {

  protected Stream<Class<?>> filterBeanDefinitionsToClasses(Set<BeanDefinition> beanDefinitions) {
    return beanDefinitions.stream()
        .map(BeanDefinition::getBeanClassName)
        .map(this::getClass)
        .filter(Optional::isPresent)
        .filter(it -> this.isSuitableComponent(it.get()))
        .map(Optional::get);
  }

  protected boolean isSuitableComponent(Class<?> clazz) {

    if (FactoryBean.class.isAssignableFrom(clazz)) {

      log.debug("Skipping FactoryBean '{}'. If needed, instead use ApplicationContextComponentsScanner", clazz);
      return false;
    }

    if (clazz.isInterface()) {

      // Skipping interface. This is possible if a @Configuration gives back a @Bean with interface return type.
      return false;
    }

    return true;
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
