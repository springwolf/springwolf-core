// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import java.util.List;

public class StandaloneEnvironmentLoader {
    public static ConfigurableEnvironment loadEnvironment(List<String> profiles) {
        StandardEnvironment environment = new StandardEnvironment();
        if (!profiles.isEmpty()) {
            environment.setActiveProfiles(profiles.toArray(new String[0]));
        }

        ConfigDataEnvironmentPostProcessor.applyTo(environment);

        return environment;
    }
}
