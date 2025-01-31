// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.handler.IgnoreErrorsBindHandler;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringwolfConfigPropertiesLoader {

    public Environment loadEnvironment(List<String> profiles) throws IOException {
        StandardEnvironment environment = new StandardEnvironment();

        loadYamlResource(environment, "application.yaml");
        loadPropertiesResource(environment, "application.properties");

        profiles.forEach(profile -> {
            try {
                loadYamlResource(environment, "application-" + profile + ".yaml");
                loadPropertiesResource(environment, "application-" + profile + ".properties");
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load properties for profile: " + profile, e);
            }
        });

        if (profiles.contains("test")) {
            loadTestProperties(environment);
        }

        return environment;
    }

    public SpringwolfConfigProperties loadSpringwolfConfigProperties(Environment environment) {
        return loadConfigProperties(environment, "springwolf", SpringwolfConfigProperties.class);
    }

    public <T> T loadConfigProperties(Environment environment, String prefix, Class<T> clazz) {
        return Binder.get(environment, new IgnoreErrorsBindHandler())
                .bind(prefix, clazz)
                .orElseThrow(() -> new IllegalStateException("Failed to bind properties"));
    }

    private static void loadTestProperties(StandardEnvironment environment) {
        Map<String, Object> testProperties = new HashMap<>();
        testProperties.put("springwolf.enabled", "true");
        testProperties.put("springwolf.docket.info.title", "Info title was loaded from spring properties");
        testProperties.put("springwolf.docket.info.version", "1.0.0");
        testProperties.put("springwolf.docket.id", "urn:io:github:springwolf:example");
        testProperties.put("springwolf.docket.default-content-type", "application/json");
        testProperties.put("springwolf.docket.servers.test-protocol.protocol", "test");
        testProperties.put("springwolf.docket.servers.test-protocol.host", "some-server:1234");
        environment.getPropertySources().addLast(new MapPropertySource("testProperties", testProperties));
    }

    private static void loadPropertiesResource(StandardEnvironment environment, String fileName) throws IOException {
        Resource applicationPropertiesResource = new ClassPathResource(fileName);
        if (applicationPropertiesResource.exists()) {
            PropertiesPropertySourceLoader propertiesLoader = new PropertiesPropertySourceLoader();
            MapPropertySource propertiesPropertySource = (MapPropertySource) propertiesLoader
                    .load(fileName, applicationPropertiesResource)
                    .get(0);
            Map<String, Object> properties = propertiesPropertySource.getSource();
            environment.getPropertySources().addLast(new MapPropertySource("applicationProperties", properties));
        }
    }

    private static void loadYamlResource(StandardEnvironment environment, String fileName) throws IOException {
        Resource applicationYamlResource = new ClassPathResource(fileName);
        if (applicationYamlResource.exists()) {
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
            MapPropertySource yamlPropertySource = (MapPropertySource)
                    sourceLoader.load(fileName, applicationYamlResource).get(0);
            Map<String, Object> properties = yamlPropertySource.getSource();
            environment.getPropertySources().addLast(new MapPropertySource("yamlProperties", properties));
        }
    }
}
