package io.github.stavshamir.swagger4kafka.asyncapi.scanners.components;

import java.util.Set;

public interface ComponentsScanner {

    /**
     * Scan a package and its descendants for classes annotated with @Component, its specializations or meta-annotations.
     * @param basePackage The root package to scan.
     * @return A set of found classes.
     */
    Set<Class<?>> scanForComponents(Package basePackage);

    /**
     * Scan a package and its descendants for classes annotated with @Component, its specializations or meta-annotations.
     * @param basePackage The root package to scan.
     * @return A set of found classes.
     */
    Set<Class<?>> scanForComponents(String basePackage);

}
