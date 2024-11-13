// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.generic_binding.annotation.configuration;

import io.github.springwolf.addons.generic_binding.annotation.processor.AsyncGenericOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringValueResolver;

@Configuration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfGenericBindingAutoConfiguration {

    @Bean
    @Order(value = BindingProcessorPriority.GENERIC_BINDING)
    public AsyncGenericOperationBindingProcessor asyncGenericOperationBindingProcessor(
            StringValueResolver stringValueResolver) {
        return new AsyncGenericOperationBindingProcessor(stringValueResolver);
    }
}
