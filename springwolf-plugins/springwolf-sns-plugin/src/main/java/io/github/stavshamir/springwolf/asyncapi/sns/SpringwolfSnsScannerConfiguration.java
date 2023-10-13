// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.sns;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.SnsMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.SnsOperationBindingProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring configuration defining the scanner beans for the sns plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfSnsScannerConfiguration {

    @Bean
    public SnsMessageBindingProcessor snsMessageBindingProcessor() {
        return new SnsMessageBindingProcessor();
    }

    @Bean
    public SnsOperationBindingProcessor snsOperationBindingProcessor() {
        return new SnsOperationBindingProcessor();
    }
}
