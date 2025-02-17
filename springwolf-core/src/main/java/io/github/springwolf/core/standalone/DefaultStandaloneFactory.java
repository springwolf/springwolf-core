// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

@Slf4j
public class DefaultStandaloneFactory implements StandaloneFactory {
    private final AnnotationConfigApplicationContext diContext = new AnnotationConfigApplicationContext();

    public DefaultStandaloneFactory(String applicationBasePackage) {
        this(applicationBasePackage, StandaloneEnvironmentLoader.load());
    }

    public DefaultStandaloneFactory(String applicationBasePackage, ConfigurableEnvironment environment) {
        this(applicationBasePackage, environment, StandaloneConfigurationDiscoverer.scan(environment));
    }

    public DefaultStandaloneFactory(
            String applicationBasePackage,
            ConfigurableEnvironment environment,
            List<Class<?>> standaloneConfigurations) {
        standaloneConfigurations.forEach(diContext::register);
        ConfigurationPropertiesBindingPostProcessor.register(diContext); // populate ConfigurationProperties beans
        diContext.setEnvironment(environment);
        diContext.refresh();

        // overwrite scanning package
        diContext.getBean(SpringwolfConfigProperties.class).getDocket().setBasePackage(applicationBasePackage);
    }

    @Override
    public AsyncApiService getAsyncApiService() {
        return diContext.getBean(AsyncApiService.class);
    }
}
