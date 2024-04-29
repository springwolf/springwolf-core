// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.kotlinx_serialization_model_converter.configuration;

import io.github.springwolf.addons.kotlinx_serialization_model_converter.converter.KotlinxSerializationModelConverter;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_USE_FQN;

/**
 * Spring AutoConfiguration adding an {@link KotlinxSerializationModelConverter} Bean to the spring context.
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class KotlinxSerializationModelConverterAutoConfiguration {

    @Bean
    public KotlinxSerializationModelConverter kotlinxSerializationTypeConverter(
            @Value("${" + SPRINGWOLF_USE_FQN + ":true}") boolean useFqn) {
        return new KotlinxSerializationModelConverter(useFqn);
    }
}
