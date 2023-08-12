// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED;

@Configuration
@ConditionalOnProperty(
        prefix = SPRINGWOLF_KAFKA_CONFIG_PREFIX,
        name = SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
public class SpringwolfKafkaProducerConfiguration {

    @Bean
    public SpringwolfKafkaProducer springwolfKafkaProducer(
            @Autowired SpringwolfKafkaTemplateFactory producerTemplateFactory) {
        return new SpringwolfKafkaProducer(producerTemplateFactory.buildKafkaTemplate());
    }
}
