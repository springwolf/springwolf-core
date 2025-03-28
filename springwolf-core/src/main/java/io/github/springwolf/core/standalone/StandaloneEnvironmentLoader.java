// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import java.util.List;

/**
 * Loads a standalone environment using spring mechanisms including env variables, properties and profiles.
 */
public class StandaloneEnvironmentLoader {
    public static ConfigurableEnvironment load() {
        return load(List.of());
    }

    public static ConfigurableEnvironment load(List<String> profiles) {
        StandardEnvironment environment = new StandardEnvironment();
        if (!profiles.isEmpty()) {
            environment.setActiveProfiles(profiles.toArray(new String[0]));
        }

        // Load properties from application.properties
        ConfigDataEnvironmentPostProcessor.applyTo(environment);

        return environment;
    }
}
