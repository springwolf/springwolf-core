// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.asyncapi.AsyncApiCreationException;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DefaultStandaloneApplication implements StandaloneApplication {
    private final AnnotationConfigApplicationContext diContext = new AnnotationConfigApplicationContext();

    /**
     * Create instance via {@link Builder#builder()}.
     */
    private DefaultStandaloneApplication(ConfigurableEnvironment environment, List<Class<?>> standaloneConfigurations) {
        diContext.setEnvironment(environment);

        standaloneConfigurations.forEach(diContext::register);
        // populate ConfigurationProperties beans
        ConfigurationPropertiesBindingPostProcessor.register(diContext);

        try {
            diContext.refresh();
            this.verifyContextHasAllRequiredBeans();
        } catch (RuntimeException | Error e) {
            diContext.close();
            throw e;
        }
    }

    private void verifyContextHasAllRequiredBeans() {
        try {
            this.getAsyncApiService();
        } catch (Exception e) {
            throw new AsyncApiCreationException(e);
        }
    }

    @Override
    public AsyncApiService getAsyncApiService() {
        return diContext.getBean(AsyncApiService.class);
    }

    @Override
    public void close() {
        diContext.close();
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Create a standalone application context using spring mechanisms internally, while skipping Spring Boot auto-configurations.
     * <p>
     * By default, all Springwolf configuration classes annotated with {@link StandaloneConfiguration} will be registered in the context.
     * Use {@link StandaloneConfigurationDiscoverer#scan(String, ConfigurableEnvironment)} to discover configurations
     * <p>
     * Custom configuration classes or beans in custom packages can be added using {@link Builder#addScanPackage(String)},
     * for example to override beans or to add a {@link io.github.springwolf.core.asyncapi.AsyncApiCustomizer}
     */
    @NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static class Builder {

        @Nullable
        private ConfigurableEnvironment environment;

        private final List<String> scanPackages =
                new ArrayList<>(List.of(SpringwolfConfigConstants.SPRINGWOLF_PACKAGE));

        public Builder setEnvironment(ConfigurableEnvironment environment) {
            this.environment = environment;
            return this;
        }

        public Builder addScanPackage(String scanPackage) {
            return addScanPackages(List.of(scanPackage));
        }

        public Builder addScanPackages(List<String> scanPackages) {
            this.scanPackages.addAll(scanPackages);
            return this;
        }

        public Builder setScanPackages(List<String> scanPackages) {
            this.scanPackages.clear();
            return addScanPackages(scanPackages);
        }

        public DefaultStandaloneApplication buildAndStart() {
            ConfigurableEnvironment actualEnvironment =
                    environment != null ? environment : StandaloneEnvironmentLoader.load();
            List<Class<?>> actualStandaloneConfigurations =
                    StandaloneConfigurationDiscoverer.scan(String.join(",", scanPackages), actualEnvironment);

            return new DefaultStandaloneApplication(actualEnvironment, actualStandaloneConfigurations);
        }
    }
}
