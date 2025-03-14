// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.amqp.configuration;

import io.github.springwolf.bindings.amqp.scanners.messages.AmqpMessageBindingProcessor;
import io.github.springwolf.bindings.amqp.scanners.operations.AmqpOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringValueResolver;

/**
 * Autoconfiguration for the springwolf Amqp Binding.
 */
@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
@StandaloneConfiguration
public class SpringwolfAmqpBindingAutoConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public AmqpOperationBindingProcessor amqpOperationBindingProcessor(StringValueResolver stringValueResolver) {
        return new AmqpOperationBindingProcessor(stringValueResolver);
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public AmqpMessageBindingProcessor amqpMessageBindingProcessor() {
        return new AmqpMessageBindingProcessor();
    }
}
