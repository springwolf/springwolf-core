// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.configuration;

import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfStompPropertiesConfiguration {

    @Bean
    public SpringwolfStompConfigProperties springwolfStompConfigProperties() {
        return new SpringwolfStompConfigProperties();
    }
}
