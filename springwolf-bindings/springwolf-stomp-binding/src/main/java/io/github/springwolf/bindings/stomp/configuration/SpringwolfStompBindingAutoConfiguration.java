// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.stomp.configuration;

import io.github.springwolf.bindings.stomp.scanners.messages.StompMessageBindingProcessor;
import io.github.springwolf.bindings.stomp.scanners.operations.StompOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringValueResolver;

/**
 * Autoconfiguration for the springwolf STOMP Binding.
 */
@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfStompBindingAutoConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public StompMessageBindingProcessor stompMessageBindingProcessor() {
        return new StompMessageBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public StompOperationBindingProcessor stompOperationBindingProcessor(StringValueResolver stringValueResolver) {
        return new StompOperationBindingProcessor(stringValueResolver);
    }
}
