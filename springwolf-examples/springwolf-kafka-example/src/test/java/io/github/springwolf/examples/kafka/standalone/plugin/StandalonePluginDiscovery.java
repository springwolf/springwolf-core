// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone.plugin;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.springframework.core.annotation.Order;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Scans the classpath for {@link StandalonePlugin} implementations.
 * <p/>
 * Requires that these plugins have a no-args constructor.
 */
public class StandalonePluginDiscovery {
    private static final String SPRINGWOLF_BASE_PACKAGE = "io.github.springwolf";

    public static List<StandalonePlugin> scan()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return scan(SPRINGWOLF_BASE_PACKAGE);
    }

    public static List<StandalonePlugin> scan(String basePackage)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try (ScanResult scanResult =
                new ClassGraph().enableAllInfo().acceptPackages(basePackage).scan()) {
            List<Class<?>> classList =
                    scanResult.getClassesImplementing(StandalonePlugin.class).loadClasses();
            sortByOrderAnnotation(classList);

            List<StandalonePlugin> instances = new ArrayList<>();
            for (Class<?> clazz : classList) {
                @SuppressWarnings("unchecked") // ensured due to scanResult getClassesImplementing filter
                Class<StandalonePlugin> pluginClass = (Class<StandalonePlugin>) clazz;

                instances.add(pluginClass.getConstructor().newInstance());
            }
            return instances;
        }
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
