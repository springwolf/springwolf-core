// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.addon.genericbinding.configuration;

import io.github.stavshamir.springwolf.asyncapi.addon.genericbinding.annotation.processor.AsyncGenericOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class SpringwolfGenericBindingConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.GENERIC_BINDING)
    public AsyncGenericOperationBindingProcessor asyncGenericOperationBindingProcessor() {
        return new AsyncGenericOperationBindingProcessor();
    }
}
