// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.configuration;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.plugins.jms.properties.SpringwolfJmsConfigProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Autoconfiguration for the springwolf jms plugin.
 */
@AutoConfiguration
@Import({SpringwolfJmsScannerConfiguration.class, SpringwolfJmsProducerConfiguration.class})
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfJmsAutoConfiguration {

    @Bean
    public SpringwolfJmsConfigProperties springwolfJmsConfigProperties() {
        return new SpringwolfJmsConfigProperties();
    }
}
