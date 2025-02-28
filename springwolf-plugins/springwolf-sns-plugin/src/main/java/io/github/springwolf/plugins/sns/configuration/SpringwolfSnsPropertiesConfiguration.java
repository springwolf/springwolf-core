// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.configuration;

import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.plugins.sns.configuration.properties.SpringwolfSnsConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfSnsPropertiesConfiguration {

    @Bean
    public SpringwolfSnsConfigProperties springwolfSnsConfigProperties() {
        return new SpringwolfSnsConfigProperties();
    }
}
