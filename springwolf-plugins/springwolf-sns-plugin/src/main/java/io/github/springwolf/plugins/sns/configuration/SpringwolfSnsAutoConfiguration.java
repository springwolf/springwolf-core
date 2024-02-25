// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.configuration;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.plugins.sns.properties.SpringwolfSnsConfigProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Autoconfiguration for the springwolf sns plugin.
 */
@AutoConfiguration
@Import({SpringwolfSnsScannerConfiguration.class, SpringwolfSnsProducerConfiguration.class})
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfSnsAutoConfiguration {

    @Bean
    public SpringwolfSnsConfigProperties springwolfSnsConfigProperties() {
        return new SpringwolfSnsConfigProperties();
    }
}
