// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.configuration;

import io.github.springwolf.core.asyncapi.controller.PublishingPayloadCreator;
import io.github.springwolf.plugins.kafka.controller.SpringwolfKafkaController;
import io.github.springwolf.plugins.kafka.producer.SpringwolfKafkaProducer;
import io.github.springwolf.plugins.kafka.producer.SpringwolfKafkaTemplateFromProperties;
import io.github.springwolf.plugins.kafka.producer.SpringwolfKafkaTemplateProvider;
import io.github.springwolf.plugins.kafka.properties.SpringwolfKafkaConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.github.springwolf.plugins.kafka.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;
import static io.github.springwolf.plugins.kafka.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED;

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
