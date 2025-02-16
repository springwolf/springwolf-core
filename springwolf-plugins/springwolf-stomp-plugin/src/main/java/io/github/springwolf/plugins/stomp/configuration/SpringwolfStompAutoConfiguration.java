// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.configuration;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Autoconfiguration for the springwolf stomp plugin.
 */
@AutoConfiguration
@Import({SpringwolfStompScannerConfiguration.class})
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
@StandaloneConfiguration
public class SpringwolfStompAutoConfiguration {

    @Bean
    public SpringwolfStompConfigProperties springwolfStompConfigProperties() {
        return new SpringwolfStompConfigProperties();
    }
}
