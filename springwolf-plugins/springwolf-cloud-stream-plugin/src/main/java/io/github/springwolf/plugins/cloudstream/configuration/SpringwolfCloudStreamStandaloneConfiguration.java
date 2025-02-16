// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.configuration;

import io.github.springwolf.core.standalone.StandaloneConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@StandaloneConfiguration
public class SpringwolfCloudStreamStandaloneConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public BindingServiceProperties bindingServiceProperties() {
        return new BindingServiceProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public ConversionService integrationConversionService() {
        return new DefaultConversionService();
    }
}
