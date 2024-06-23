// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.kotlinx_serialization_model_converter.configuration;

import io.github.springwolf.addons.kotlinx_serialization_model_converter.converter.KotlinxSerializationModelConverter;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AutoConfiguration adding an {@link KotlinxSerializationModelConverter} Bean to the spring context.
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class KotlinxSerializationModelConverterAutoConfiguration {

    @Bean
    public KotlinxSerializationModelConverter kotlinxSerializationModelConverter(
            SpringwolfConfigProperties springwolfConfigProperties) {
        return new KotlinxSerializationModelConverter(springwolfConfigProperties.isUseFqn());
    }
}
