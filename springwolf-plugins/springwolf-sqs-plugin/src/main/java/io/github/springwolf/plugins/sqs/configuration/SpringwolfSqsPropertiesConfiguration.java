// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.configuration;

import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.plugins.sqs.configuration.properties.SpringwolfSqsConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfSqsPropertiesConfiguration {

    @Bean
    public SpringwolfSqsConfigProperties springwolfSqsConfigProperties() {
        return new SpringwolfSqsConfigProperties();
    }
}
