// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.sqs;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfSqsConfigProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Autoconfiguration for the springwolf sqs plugin.
 */
@AutoConfiguration
@Import({SpringwolfSqsScannerConfiguration.class, SpringwolfSqsProducerConfiguration.class})
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfSqsAutoConfiguration {

    @Bean
    public SpringwolfSqsConfigProperties springwolfSqsConfigProperties() {
        return new SpringwolfSqsConfigProperties();
    }
}
