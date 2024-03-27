// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.kotlinx_serialization_model_converter.configuration;

import io.github.springwolf.addons.kotlinx_serialization_model_converter.converter.KotlinxSerializationModelConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AutoConfiguration adding an {@link KotlinxSerializationModelConverter} Bean to the spring context.
 */
@Configuration(proxyBeanMethods = false)
public class KotlinxSerializationModelConverterAutoConfiguration {

    @Bean
    public KotlinxSerializationModelConverter kotlinxSerializationTypeConverter(
            @Value("${springwolf.use-fqn}") boolean useFqn) {
        return new KotlinxSerializationModelConverter(useFqn);
    }
}
