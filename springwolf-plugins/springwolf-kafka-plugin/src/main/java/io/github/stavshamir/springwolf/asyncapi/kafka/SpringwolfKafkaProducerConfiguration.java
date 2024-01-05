// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.kafka;

import io.github.stavshamir.springwolf.asyncapi.controller.PublishingPayloadCreator;
import io.github.stavshamir.springwolf.asyncapi.controller.SpringwolfKafkaController;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigProperties;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaTemplateFromProperties;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaTemplateProvider;
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
            PublishingPayloadCreator publishingPayloadCreator, SpringwolfKafkaProducer springwolfKafkaProducer) {
        return new SpringwolfKafkaController(publishingPayloadCreator, springwolfKafkaProducer);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfKafkaProducer springwolfKafkaProducer(
            SpringwolfKafkaTemplateProvider springwolfKafkaTemplateProvider) {
        return new SpringwolfKafkaProducer(springwolfKafkaTemplateProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfKafkaTemplateFromProperties springwolfKafkaTemplateFromProperties(
            SpringwolfKafkaConfigProperties springwolfKafkaConfigProperties) {
        return new SpringwolfKafkaTemplateFromProperties(springwolfKafkaConfigProperties);
    }
}
