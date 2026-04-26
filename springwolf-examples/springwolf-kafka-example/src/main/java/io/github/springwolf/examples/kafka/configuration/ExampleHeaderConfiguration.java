// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.configuration;

import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.examples.kafka.modelconverters.ConsumerRecordMetadataModelConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@StandaloneConfiguration
public class ExampleHeaderConfiguration {

    @Bean
    public ConsumerRecordMetadataModelConverter consumerRecordMetadataModelConverter() {
        return new ConsumerRecordMetadataModelConverter();
    }
}
