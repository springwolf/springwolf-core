// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.beans;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.AnnotationUtil;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ConfigurationClassScanner;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
public class DefaultBeanMethodsScanner implements BeanMethodsScanner {

    private final ConfigurationClassScanner configurationClassScanner;

    @Override
    public Set<Method> getBeanMethods() {
        return configurationClassScanner.scan().stream()
                .map(Class::getDeclaredMethods)
                .map(Arrays::asList)
                .flatMap(List::stream)
                .filter(method -> AnnotationUtil.findAnnotation(Bean.class, method) != null)
                .collect(toSet());
    }
}
