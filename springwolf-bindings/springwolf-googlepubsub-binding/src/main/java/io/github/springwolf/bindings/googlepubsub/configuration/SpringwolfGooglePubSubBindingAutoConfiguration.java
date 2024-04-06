// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.configuration;

import io.github.springwolf.bindings.googlepubsub.scanners.channels.GooglePubSubChannelBindingProcessor;
import io.github.springwolf.bindings.googlepubsub.scanners.messages.GooglePubSubMessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * Autoconfiguration for the springwolf Google PubSub Binding.
 */
@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfGooglePubSubBindingAutoConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public GooglePubSubChannelBindingProcessor googlePubSubChannelBindingProcessor() {
        return new GooglePubSubChannelBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public GooglePubSubMessageBindingProcessor googlePubSubMessageBindingProcessor() {
        return new GooglePubSubMessageBindingProcessor();
    }
}
