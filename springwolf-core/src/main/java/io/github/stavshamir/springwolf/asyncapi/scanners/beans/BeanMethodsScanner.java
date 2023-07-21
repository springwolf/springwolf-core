package io.github.stavshamir.springwolf.asyncapi.scanners.beans;

import java.lang.reflect.Method;
import java.util.Set;

public interface BeanMethodsScanner {

    /**
     * @return Methods annotated with @Bean which are declared in @Configuration classes from the base package.
     */
    Set<Method> getBeanMethods();
}
