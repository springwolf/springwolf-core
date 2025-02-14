// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone.plugin;

import io.github.springwolf.core.asyncapi.scanners.classes.spring.annotations.ClassScannerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Scans the classpath for {@link StandalonePlugin} implementations.
 * <p/>
 * Requires that these plugins have a no-args constructor.
 */
@Slf4j
public class StandalonePluginDiscovery {
    private static final String SPRINGWOLF_BASE_PACKAGE = "io.github.springwolf";

    public static List<StandalonePlugin> scan(Environment environment)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return scan(SPRINGWOLF_BASE_PACKAGE, environment);
    }

    public static List<StandalonePlugin> scan(String basePackages, Environment environment)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        AssignableTypeFilter filter = new AssignableTypeFilter(StandalonePlugin.class);
        List<Class<?>> classList = ClassScannerUtil.getClasses(basePackages, filter, environment);

        sortByOrderAnnotation(classList);

        List<StandalonePlugin> instances = new ArrayList<>();
        for (Class<?> clazz : classList) {
            @SuppressWarnings("unchecked") // ensured due to scanResult getClassesImplementing filter
            Class<StandalonePlugin> pluginClass = (Class<StandalonePlugin>) clazz;

            instances.add(pluginClass.getConstructor().newInstance());
        }
        return instances;
    }

    private static void sortByOrderAnnotation(List<Class<?>> classList) {
        classList.sort((a, b) -> {
            Order orderA = a.getAnnotation(Order.class);
            Order orderB = b.getAnnotation(Order.class);
            if (orderA == null) return (orderB == null) ? 0 : 1;
            if (orderB == null) return -1;
            return Integer.compare(orderA.value(), orderB.value());
        });
    }
}
