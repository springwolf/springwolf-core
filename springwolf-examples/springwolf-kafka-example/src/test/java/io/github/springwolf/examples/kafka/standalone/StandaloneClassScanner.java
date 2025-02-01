// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

class StandaloneClassScanner implements ClassScanner {
    private final HashSet<Class<?>> classes;

    public StandaloneClassScanner(String basePackage, SpringwolfConfigProperties properties) {
        String actualBasePackage =
                basePackage != null ? basePackage : properties.getDocket().getBasePackage();

        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()
                .acceptPackages(actualBasePackage)
                .scan()) {
            classes = new HashSet<>(
                    scanResult.getClassesWithAnnotation(Component.class).loadClasses());
        }
    }

    @Override
    public Set<Class<?>> scan() {
        return classes;
    }
}
