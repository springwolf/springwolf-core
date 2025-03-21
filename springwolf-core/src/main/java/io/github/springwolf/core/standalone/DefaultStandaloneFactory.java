// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

/**
 * Create a standalone application context using spring mechanisms internally, while skipping Spring Boot auto-configurations.
 * <p>
 * By default, all Springwolf configuration classes annotated with {@link StandaloneConfiguration} will be registered in the context.
 * Use {@link StandaloneConfigurationDiscoverer#scan(String,ConfigurableEnvironment)} to discover configurations
 * <p>
 * Custom configuration classes or beans can be added,
 * for example to override beans or to add a {@link io.github.springwolf.core.asyncapi.AsyncApiCustomizer}
 */
public class DefaultStandaloneFactory implements StandaloneFactory {
    private final AnnotationConfigApplicationContext diContext = new AnnotationConfigApplicationContext();

    public DefaultStandaloneFactory() {
        this(StandaloneEnvironmentLoader.load().getProperty("springwolf.docket.base-package"));
    }

    public DefaultStandaloneFactory(String applicationBasePackage) {
        this(applicationBasePackage, StandaloneEnvironmentLoader.load());
    }

    public DefaultStandaloneFactory(String applicationBasePackage, ConfigurableEnvironment environment) {
        this(applicationBasePackage, environment, StandaloneConfigurationDiscoverer.scan(environment));
    }

    /**
     * Create a standalone application context with custom configurations
     *
     * @param applicationBasePackages The application base packages that Springwolf will scan
     * @param environment The spring environment to use (e.g. properties, profiles)
     * @param standaloneConfigurations All standalone configurations to register in the context
     */
    public DefaultStandaloneFactory(
            String applicationBasePackages,
            ConfigurableEnvironment environment,
            List<Class<?>> standaloneConfigurations) {
        standaloneConfigurations.forEach(diContext::register);
        ConfigurationPropertiesBindingPostProcessor.register(diContext); // populate ConfigurationProperties beans
        diContext.setEnvironment(environment);
        diContext.refresh();

        // overwrite scanning package
        diContext.getBean(SpringwolfConfigProperties.class).getDocket().setBasePackage(applicationBasePackages);
    }

    @Override
    public AsyncApiService getAsyncApiService() {
        return diContext.getBean(AsyncApiService.class);
    }
}
