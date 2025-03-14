// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.configuration;

import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.plugins.jms.configuration.properties.SpringwolfJmsConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfJmsPropertiesConfiguration {

    @Bean
    public SpringwolfJmsConfigProperties springwolfJmsConfigProperties() {
        return new SpringwolfJmsConfigProperties();
    }
}
