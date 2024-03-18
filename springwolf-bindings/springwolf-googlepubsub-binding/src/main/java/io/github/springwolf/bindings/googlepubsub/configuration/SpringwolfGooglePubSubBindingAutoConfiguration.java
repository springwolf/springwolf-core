// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.configuration;

import io.github.springwolf.bindings.googlepubsub.scanners.channels.GooglePubSubChannelBindingProcessor;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfGooglePubSubBindingAutoConfiguration {
    @Bean
    public GooglePubSubChannelBindingProcessor googlePubSubChannelBindingProcessor() {
        return new GooglePubSubChannelBindingProcessor();
    }
}
