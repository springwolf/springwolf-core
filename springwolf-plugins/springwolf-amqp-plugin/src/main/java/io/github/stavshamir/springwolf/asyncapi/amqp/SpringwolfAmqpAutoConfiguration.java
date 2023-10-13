// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.amqp;

import io.github.stavshamir.springwolf.configuration.properties.SpringwolfAmqpConfigProperties;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants;
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
public class SpringwolfAmqpAutoConfiguration {

    @Bean
    public SpringwolfAmqpConfigProperties springwolfAmqpConfigProperties() {
        return new SpringwolfAmqpConfigProperties();
    }
}
