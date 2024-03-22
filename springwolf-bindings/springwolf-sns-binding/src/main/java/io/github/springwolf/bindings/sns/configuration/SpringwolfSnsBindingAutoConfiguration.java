// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sns.configuration;

import io.github.springwolf.bindings.sns.scanners.messages.SnsMessageBindingProcessor;
import io.github.springwolf.bindings.sns.scanners.operations.SnsOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * Autoconfiguration for the springwolf SQS Binding.
 */
@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfSnsBindingAutoConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public SnsMessageBindingProcessor sqsMessageBindingProcessor() {
        return new SnsMessageBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public SnsOperationBindingProcessor sqsOperationBindingProcessor() {
        return new SnsOperationBindingProcessor();
    }
}
