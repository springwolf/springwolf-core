// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.generic_binding.annotation.configuration;

import io.github.springwolf.addons.generic_binding.annotation.processor.AsyncGenericOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class SpringwolfGenericBindingAutoConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.GENERIC_BINDING)
    public AsyncGenericOperationBindingProcessor asyncGenericOperationBindingProcessor() {
        return new AsyncGenericOperationBindingProcessor();
    }
}
