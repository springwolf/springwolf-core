// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.configuration;

import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfKafkaPropertiesConfiguration {

    @Bean
    public SpringwolfKafkaConfigProperties springwolfKafkaConfigProperties() {
        return new SpringwolfKafkaConfigProperties();
    }
}
