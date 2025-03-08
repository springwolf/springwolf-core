// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.beans;

import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
public class DefaultBeanMethodsScanner implements BeanMethodsScanner {

    private final ClassScanner componentClassScanner;

    @Override
    public Set<Method> getBeanMethods() {
        return componentClassScanner.scan().stream()
                .map(Class::getDeclaredMethods)
                .map(Arrays::asList)
                .flatMap(List::stream)
                .filter(method -> AnnotationUtil.findFirstAnnotation(Bean.class, method) != null)
                .collect(toSet());
    }
}
