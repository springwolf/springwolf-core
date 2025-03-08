// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfPropertiesConfiguration {

    @Bean
    public SpringwolfConfigProperties springwolfConfigProperties() {
        return new SpringwolfConfigProperties();
    }
}
