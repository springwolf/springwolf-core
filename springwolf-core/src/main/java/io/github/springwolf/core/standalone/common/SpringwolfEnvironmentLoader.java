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

public class SpringwolfEnvironmentLoader {
    public static ConfigurableEnvironment loadEnvironment(List<String> profiles) {
        StandardEnvironment environment = new StandardEnvironment();
        if (!profiles.isEmpty()) {
            environment.setActiveProfiles(profiles.toArray(new String[0]));
        }

        ConfigDataEnvironmentPostProcessor.applyTo(environment);

        return environment;
    }
}
