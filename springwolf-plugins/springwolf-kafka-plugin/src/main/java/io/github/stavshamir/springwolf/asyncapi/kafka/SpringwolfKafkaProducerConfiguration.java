// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.controller.SpringwolfKafkaController;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigProperties;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaTemplateFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        prefix = SPRINGWOLF_KAFKA_CONFIG_PREFIX,
        name = SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
public class SpringwolfKafkaProducerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfKafkaController springwolfKafkaController(
            AsyncApiDocketService asyncApiDocketService,
            SpringwolfKafkaProducer springwolfKafkaProducer,
            ObjectMapper objectMapper) {
        return new SpringwolfKafkaController(asyncApiDocketService, springwolfKafkaProducer, objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfKafkaProducer springwolfKafkaProducer(SpringwolfKafkaTemplateFactory producerTemplateFactory) {
        return new SpringwolfKafkaProducer(producerTemplateFactory.buildKafkaTemplate());
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfKafkaTemplateFactory springwolfKafkaTemplateFactory(
            SpringwolfKafkaConfigProperties springwolfKafkaConfigProperties) {
        return new SpringwolfKafkaTemplateFactory(springwolfKafkaConfigProperties);
    }
}
