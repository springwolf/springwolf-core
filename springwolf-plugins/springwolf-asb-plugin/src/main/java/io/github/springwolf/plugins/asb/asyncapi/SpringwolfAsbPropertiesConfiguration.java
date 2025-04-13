package io.github.springwolf.plugins.asb.asyncapi;

import io.github.springwolf.core.standalone.StandaloneConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringwolfAsbPropertiesConfiguration.
 */

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfAsbPropertiesConfiguration {

    @Bean
    public SpringwolfAsbConfigurationProperties springwolfAsbConfigurationProperties(){
        return new SpringwolfAsbConfigurationProperties();
    }
}
