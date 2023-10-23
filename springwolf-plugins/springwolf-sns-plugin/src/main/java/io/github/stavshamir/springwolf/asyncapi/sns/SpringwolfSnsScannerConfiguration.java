// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.sns;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.SnsMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.SnsOperationBindingProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * spring configuration defining the scanner beans for the sns plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfSnsScannerConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public SnsMessageBindingProcessor snsMessageBindingProcessor() {
        return new SnsMessageBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public SnsOperationBindingProcessor snsOperationBindingProcessor() {
        return new SnsOperationBindingProcessor();
    }
}
