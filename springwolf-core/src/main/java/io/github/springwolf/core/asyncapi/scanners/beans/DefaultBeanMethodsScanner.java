// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.beans;

import io.github.springwolf.core.asyncapi.scanners.classes.spring.ConfigurationClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
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
                .filter(method -> AnnotationScannerUtil.findAnnotation(Bean.class, method) != null)
                .collect(toSet());
    }
}
