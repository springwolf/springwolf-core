package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import java.util.Set;

public interface ComponentsScanner {

    /**
     * Scan a package and its descendants for classes annotated with @Component, its specializations or meta-annotations.
     * @param basePackage The root package to scan.
     * @return A set of found classes.
     */
    default Set<Class<?>> scanForComponents(String basePackage) {
        return this.scanForComponents(basePackage, null);
    }

    /**
     * Scan a package and its descendants for classes annotated with @Component, its specializations or meta-annotations.
     * @param basePackage The root package to scan. Will be used as filter with {@code configurationBasePackage}. Can be null.
     * @param configurationBasePackage The root configuration package to scan for {@link org.springframework.context.annotation.Bean} components.
     * @return A set of found classes.
     */
    Set<Class<?>> scanForComponents(String basePackage, String configurationBasePackage);

}
