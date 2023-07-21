package io.github.stavshamir.springwolf.asyncapi.scanners.beans;

import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ConfigurationClassScanner;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class DefaultBeanMethodsScanner implements BeanMethodsScanner {

    private final ConfigurationClassScanner configurationClassScanner;

    @Override
    public Set<Method> getBeanMethods() {
        Set<Class<?>> configurationClasses = configurationClassScanner.scan();

        Stream<Method> methods = configurationClasses.stream()
                .map(Class::getDeclaredMethods)
                .map(Arrays::asList)
                .flatMap(List::stream);

        return methods.filter(method -> method.isAnnotationPresent(Bean.class)).collect(toSet());
    }
}
