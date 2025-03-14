// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.configuration;

import io.github.springwolf.addons.common_model_converters.converters.enums.NameInReffedSchemaModelConverter;
import io.github.springwolf.addons.common_model_converters.converters.monetaryamount.MonetaryAmountConverter;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AutoConfiguration adding an {@link MonetaryAmountConverter} Bean to the spring context.
 */
@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class CommonModelConvertersAutoConfiguration {

    @Bean
    public MonetaryAmountConverter monetaryAmountConverter() {
        return new MonetaryAmountConverter();
    }

    @Bean
    public NameInReffedSchemaModelConverter nameInReffedSchemaModelConverter() {
        return new NameInReffedSchemaModelConverter();
    }
}
