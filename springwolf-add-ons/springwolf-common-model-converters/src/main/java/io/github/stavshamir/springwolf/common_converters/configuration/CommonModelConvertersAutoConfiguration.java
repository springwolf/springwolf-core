// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.common_converters.configuration;

import io.github.stavshamir.springwolf.common_converters.converters.monetaryamount.MonetaryAmountConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AutoConfiguration adding an {@link MonetaryAmountConverter} Bean to the spring context.
 */
@Configuration(proxyBeanMethods = false)
public class CommonModelConvertersAutoConfiguration {

    @Bean
    public MonetaryAmountConverter monetaryAmountConverter() {
        return new MonetaryAmountConverter();
    }
}
