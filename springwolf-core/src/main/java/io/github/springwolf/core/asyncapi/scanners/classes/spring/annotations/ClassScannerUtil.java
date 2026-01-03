// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.classes.spring.annotations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
public abstract class ClassScannerUtil {
    /**
     * Get classes from basePackages that match the filter
     * @param basePackages comma separated list of packages
     */
    public static List<Class<?>> getClasses(String basePackages, TypeFilter filter, Environment environment) {
        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false, environment);

        provider.addIncludeFilter(filter);

        return Arrays.stream(basePackages.replaceAll("\\s", "").split(","))
                .flatMap(basePackage -> provider.findCandidateComponents(basePackage).stream()
                        .map(BeanDefinition::getBeanClassName)
                        .map(ClassScannerUtil::getClass)
                        .flatMap(Optional::stream))
                .collect(toList());
    }

    private static Optional<Class<?>> getClass(String className) {
        try {
            log.trace("Found candidate class: {}", className);
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            log.warn("Failed to get class for name: {}", className);
            return Optional.empty();
        }
    }
}
