// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.classes;

import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SpringwolfClassScanner implements ClassScanner {

    private final ComponentClassScanner scanner;
    private final BeanMethodsScanner beanMethodsScanner;

    @Override
    public Set<Class<?>> scan() {
        Stream<Class<?>> components = scanner.scan().stream();
        Stream<Class<?>> configurationBeans =
                beanMethodsScanner.getBeanMethods().stream().map(Method::getReturnType);

        return Stream.concat(components, configurationBeans).collect(Collectors.toSet());
    }
}
