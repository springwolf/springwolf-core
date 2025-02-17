// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone.common;

import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.handler.IgnoreErrorsBindHandler;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpringwolfConfigPropertiesLoader {

    public static ConfigurableEnvironment loadEnvironment(List<String> profiles) {
        StandardEnvironment environment = new StandardEnvironment();
        if (!profiles.isEmpty()) {
            environment.setActiveProfiles(profiles.toArray(new String[0]));
        }

        ConfigDataEnvironmentPostProcessor.applyTo(environment);

        return environment;
    }

    // TODO: remove
    public static ConfigurableEnvironment loadEnvironmentOutdated(List<String> profiles) throws IOException {
        StandardEnvironment environment = new StandardEnvironment();
        profiles.forEach(profile -> {
            try {
                loadYamlResource(environment, "application-" + profile + ".yaml");
                loadPropertiesResource(environment, "application-" + profile + ".properties");
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load properties for profile: " + profile, e);
            }
        });

        loadPropertiesResource(environment, "application.properties");
        loadYamlResource(environment, "application.yaml");

        return environment;
    }

    public static <T> T loadConfigProperties(Environment environment, String prefix, Class<T> clazz) {
        return Binder.get(environment, new IgnoreErrorsBindHandler())
                .bind(prefix, clazz)
                .orElseThrow(() -> new IllegalStateException("Failed to bind properties"));
    }

    private static void loadPropertiesResource(StandardEnvironment environment, String fileName) throws IOException {
        Resource applicationPropertiesResource = new ClassPathResource(fileName);
        if (applicationPropertiesResource.exists()) {
            PropertiesPropertySourceLoader propertiesLoader = new PropertiesPropertySourceLoader();
            MapPropertySource propertySource = (MapPropertySource) propertiesLoader
                    .load(fileName, applicationPropertiesResource)
                    .get(0);
            Map<String, Object> properties = removeOriginTrackedPropertySource(propertySource);
            environment.getPropertySources().addLast(new MapPropertySource("applicationProperties", properties));
        }
    }

    private static void loadYamlResource(StandardEnvironment environment, String fileName) throws IOException {
        Resource applicationYamlResource = new ClassPathResource(fileName);
        if (applicationYamlResource.exists()) {
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
            MapPropertySource propertySource = (MapPropertySource)
                    sourceLoader.load(fileName, applicationYamlResource).get(0);
            Map<String, Object> properties = removeOriginTrackedPropertySource(propertySource);
            environment.getPropertySources().addLast(new MapPropertySource("yamlProperties", properties));
        }
    }

    /**
     * OriginTrackedMapPropertySource does not support placeholder replacement, therefore it is removed
     */
    private static Map<String, Object> removeOriginTrackedPropertySource(MapPropertySource propertySource) {
        if (propertySource instanceof OriginTrackedMapPropertySource) {
            return propertySource.getSource().entrySet().stream()
                    .map(entry -> Map.entry(entry.getKey(), entry.getValue().toString()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return propertySource.getSource();
    }
}
