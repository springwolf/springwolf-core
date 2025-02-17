// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.annotations.ClassScannerUtil;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.standalone.bean.StandaloneStringValueResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.List;

@Slf4j
public class StandaloneDIFactory implements StandaloneFactory {
    private final AnnotationConfigApplicationContext diContext = new AnnotationConfigApplicationContext();

    public static List<Class<?>> discover(ConfigurableEnvironment environment) {
        return discover(SpringwolfConfigConstants.SPRINGWOLF_PACKAGE, environment);
    }

    public static List<Class<?>> discover(String scanPackage, ConfigurableEnvironment environment) {
        final TypeFilter filter = new AnnotationTypeFilter(StandaloneConfiguration.class);
        return ClassScannerUtil.getClasses(scanPackage, filter, environment);
    }

    public StandaloneDIFactory(
            String applicationBasePackage,
            List<Class<?>> standaloneConfigurations,
            ConfigurableEnvironment environment) {
        diContext.setEnvironment(environment);

        // populate ConfigurationProperties beans
        ConfigurationPropertiesBindingPostProcessor.register(diContext);

        // custom beans replacing spring default implementations
        diContext.register(ObjectMapper.class);
        diContext.register(StandaloneStringValueResolver.class);

        standaloneConfigurations.forEach(diContext::register);
        diContext.refresh();

        // overwrite scanning package
        diContext.getBean(SpringwolfConfigProperties.class).getDocket().setBasePackage(applicationBasePackage);
    }

    @Override
    public AsyncApiService getAsyncApiService() {
        return diContext.getBean(AsyncApiService.class);
    }
}
