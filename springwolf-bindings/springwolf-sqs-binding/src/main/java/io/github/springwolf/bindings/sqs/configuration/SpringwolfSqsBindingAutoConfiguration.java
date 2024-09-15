// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sqs.configuration;

import io.github.springwolf.bindings.sqs.scanners.messages.SqsMessageBindingProcessor;
import io.github.springwolf.bindings.sqs.scanners.operations.SqsOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringValueResolver;

/**
 * Autoconfiguration for the springwolf SQS Binding.
 */
@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfSqsBindingAutoConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public SqsMessageBindingProcessor sqsMessageBindingProcessor() {
        return new SqsMessageBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public SqsOperationBindingProcessor sqsOperationBindingProcessor(StringValueResolver stringValueResolver) {
        return new SqsOperationBindingProcessor(stringValueResolver);
    }
}
