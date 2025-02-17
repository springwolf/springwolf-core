// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.configuration;

import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.plugins.amqp.configuration.properties.SpringwolfAmqpConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfAmqpPropertiesConfiguration {

    @Bean
    public SpringwolfAmqpConfigProperties springwolfAmqpConfigProperties() {
        return new SpringwolfAmqpConfigProperties();
    }
}
