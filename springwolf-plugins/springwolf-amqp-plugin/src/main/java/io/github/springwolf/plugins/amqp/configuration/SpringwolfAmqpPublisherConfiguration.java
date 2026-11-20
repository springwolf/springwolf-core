// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.configuration;

import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.controller.publish.PublishingPayloadCreator;
import io.github.springwolf.plugins.amqp.controller.SpringwolfAmqpController;
import io.github.springwolf.plugins.amqp.publish.SpringwolfAmqpPublisher;
import io.github.springwolf.web.controller.publish.PublishingBaseController;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

import static io.github.springwolf.plugins.amqp.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_AMQP_CONFIG_PREFIX;
import static io.github.springwolf.plugins.amqp.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        prefix = SPRINGWOLF_AMQP_CONFIG_PREFIX,
        name = SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
@Import({SpringwolfAmqpScannerConfiguration.class})
public class SpringwolfAmqpPublisherConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfAmqpPublisher springwolfAmqpProducer(
            AsyncApiService asyncApiService, List<RabbitTemplate> rabbitTemplates) {
        return new SpringwolfAmqpPublisher(asyncApiService, rabbitTemplates);
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(PublishingBaseController.class)
    public static class WebConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public SpringwolfAmqpController springwolfAmqpController(
                PublishingPayloadCreator publishingPayloadCreator, SpringwolfAmqpPublisher springwolfAmqpPublisher) {
            return new SpringwolfAmqpController(publishingPayloadCreator, springwolfAmqpPublisher);
        }
    }
}
