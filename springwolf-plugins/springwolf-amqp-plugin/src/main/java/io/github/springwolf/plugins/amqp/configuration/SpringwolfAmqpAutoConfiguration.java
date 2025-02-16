// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.configuration;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.plugins.amqp.configuration.properties.SpringwolfAmqpConfigProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Autoconfiguration for the springwolf amqp plugin.
 */
@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
@Import({SpringwolfAmqpScannerConfiguration.class, SpringwolfAmqpProducerConfiguration.class})
@StandaloneConfiguration
public class SpringwolfAmqpAutoConfiguration {

    @Bean
    public SpringwolfAmqpConfigProperties springwolfAmqpConfigProperties() {
        return new SpringwolfAmqpConfigProperties();
    }
}
