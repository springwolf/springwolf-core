package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.stereotype.Component;

/**
 * Scans the started {@link ApplicationContext} for possible components.
 * <p>
 * The preferred method for accurate probing of runtime components created by:
 * <ul>
 *   <li>@{@link Component}-annotated classes</li>
 *   <li>@{@link Bean}-annotated methods inside @{@link Configuration}-annotated classes</li>
 *   <li>@{@link Bean}-annotated methods that give back a {@link org.springframework.beans.factory.FactoryBean}</li>
 *   <li>Any external library that enhances your runtime with custom instances</li>
 * </ul>
 */
public class ApplicationContextComponentsScanner implements ComponentsScanner, ApplicationContextAware, InitializingBean {

  private final Predicate<Class<?>> predicate;
  private ListableBeanFactory listableBeanFactory;

  public ApplicationContextComponentsScanner(Predicate<Class<?>> predicate) {
    this.predicate = predicate;
  }

  public ApplicationContextComponentsScanner(String basePackage) {
    this(clazz -> {
      String clazzName = clazz.getName();
      return clazzName.equals(basePackage) || clazzName.startsWith(basePackage + ".");
    });
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.listableBeanFactory = applicationContext;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    Assert.notNull(this.listableBeanFactory, "The application context must be set");
  }

  @Override
  public Set<Class<?>> scanForComponents() {

    Set<Class<?>> components = new LinkedHashSet<>();
    for (String beanName : this.listableBeanFactory.getBeanDefinitionNames()) {
      Class<?> beanType = this.listableBeanFactory.getType(beanName);
      if (this.predicate == null || this.predicate.test(beanType)) {
        components.add(beanType);
      }
    }

    return components;
  }
}
