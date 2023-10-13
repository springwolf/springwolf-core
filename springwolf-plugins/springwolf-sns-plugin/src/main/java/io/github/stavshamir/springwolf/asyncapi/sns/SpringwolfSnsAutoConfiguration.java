// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.sns;

import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfSnsConfigProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Autoconfiguration for the springwolf sns plugin.
 */
@AutoConfiguration
@Import({SpringwolfSnsScannerConfiguration.class, SpringwolfSnsProducerConfiguration.class})
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, matchIfMissing = true)
public class SpringwolfSnsAutoConfiguration {

    @Bean
    public SpringwolfSnsConfigProperties springwolfSnsConfigProperties() {
        return new SpringwolfSnsConfigProperties();
    }
}
