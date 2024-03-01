// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.configuration;

import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.plugins.amqp.controller.SpringwolfAmqpController;
import io.github.springwolf.plugins.amqp.producer.SpringwolfAmqpProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;

import java.util.List;

import static io.github.springwolf.plugins.amqp.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_AMQP_CONFIG_PREFIX;
import static io.github.springwolf.plugins.amqp.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        prefix = SPRINGWOLF_AMQP_CONFIG_PREFIX,
        name = SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
@Import({SpringwolfAmqpScannerConfiguration.class})
public class SpringwolfAmqpProducerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfAmqpProducer springwolfAmqpProducer(
            AsyncApiService asyncApiService, @NonNull List<RabbitTemplate> rabbitTemplates) {
        return new SpringwolfAmqpProducer(asyncApiService, rabbitTemplates);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfAmqpController springwolfAmqpController(
            PublishingPayloadCreator publishingPayloadCreator, SpringwolfAmqpProducer springwolfAmqpProducer) {
        return new SpringwolfAmqpController(publishingPayloadCreator, springwolfAmqpProducer);
    }
}
