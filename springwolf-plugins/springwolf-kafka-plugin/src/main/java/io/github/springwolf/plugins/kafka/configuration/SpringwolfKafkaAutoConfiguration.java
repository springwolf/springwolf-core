// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.configuration;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Autoconfiguration for the springwolf kafka plugin.
 */
@AutoConfiguration
@Import({SpringwolfKafkaScannerConfiguration.class, SpringwolfKafkaProducerConfiguration.class})
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfKafkaAutoConfiguration {

    @Bean
    public SpringwolfKafkaConfigProperties springwolfKafkaConfigProperties() {
        return new SpringwolfKafkaConfigProperties();
    }
}
