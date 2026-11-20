// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.configuration;

import io.github.springwolf.core.controller.publish.PublishingPayloadCreator;
import io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigProperties;
import io.github.springwolf.plugins.kafka.controller.SpringwolfKafkaController;
import io.github.springwolf.plugins.kafka.producer.SpringwolfKafkaTemplateFromProperties;
import io.github.springwolf.plugins.kafka.producer.SpringwolfKafkaTemplateProvider;
import io.github.springwolf.plugins.kafka.publish.SpringwolfKafkaPublisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;
import static io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        prefix = SPRINGWOLF_KAFKA_CONFIG_PREFIX,
        name = SPRINGWOLF_KAFKA_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
public class SpringwolfKafkaPublisherConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfKafkaController springwolfKafkaController(
            PublishingPayloadCreator publishingPayloadCreator, SpringwolfKafkaPublisher springwolfKafkaPublisher) {
        return new SpringwolfKafkaController(publishingPayloadCreator, springwolfKafkaPublisher);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfKafkaPublisher springwolfKafkaProducer(
            SpringwolfKafkaTemplateProvider springwolfKafkaTemplateProvider) {
        return new SpringwolfKafkaPublisher(springwolfKafkaTemplateProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfKafkaTemplateFromProperties springwolfKafkaTemplateFromProperties(
            SpringwolfKafkaConfigProperties springwolfKafkaConfigProperties) {
        return new SpringwolfKafkaTemplateFromProperties(springwolfKafkaConfigProperties);
    }
}
