// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone.plugin;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.swagger.v3.core.converter.ModelConverter;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface StandalonePlugin {
    static List<StandalonePlugin> discoverPlugins(String basePackage)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try (ScanResult scanResult =
                new ClassGraph().enableAllInfo().acceptPackages(basePackage).scan()) {
            ClassInfoList classesImplementing = scanResult.getClassesImplementing(StandalonePlugin.class);
            List<Class<?>> classList = classesImplementing.loadClasses();
            classList.sort((a, b) -> {
                Order orderA = a.getAnnotation(Order.class);
                Order orderB = b.getAnnotation(Order.class);
                if (orderA == null) return (orderB == null) ? 0 : 1;
                if (orderB == null) return -1;
                return Integer.compare(orderA.value(), orderB.value());
            });

            List<StandalonePlugin> instances = new ArrayList<>();
            for (Class<?> clazz : classList) {
                Class<StandalonePlugin> pluginClass = (Class<StandalonePlugin>) clazz;
                StandalonePlugin instance = pluginClass.getConstructor().newInstance();
                instances.add(instance);
            }
            return instances;
        }
    }

    default StandalonePluginResult load(StandalonePluginContext context) throws IOException {
        return StandalonePluginResult.builder().build();
    }

    default Collection<ModelConverter> getModelConverters() {
        return List.of();
    }

    default Collection<AsyncApiCustomizer> getAsyncApiCustomizers() {
        return List.of();
    }
}
