// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.jms;

import io.github.stavshamir.springwolf.asyncapi.controller.PublishingPayloadCreator;
import io.github.stavshamir.springwolf.asyncapi.controller.SpringwolfJmsController;
import io.github.stavshamir.springwolf.producer.SpringwolfJmsProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfJmsConfigConstants.SPRINGWOLF_JMS_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfJmsConfigConstants.SPRINGWOLF_JMS_PLUGIN_PUBLISHING_ENABLED;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        prefix = SPRINGWOLF_JMS_CONFIG_PREFIX,
        name = SPRINGWOLF_JMS_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
public class SpringwolfJmsProducerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfJmsProducer springwolfJmsProducer(List<JmsTemplate> jmsTemplates) {
        return new SpringwolfJmsProducer(jmsTemplates);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfJmsController springwolfJmsController(
            PublishingPayloadCreator publishingPayloadCreator, SpringwolfJmsProducer springwolfJmsProducer) {
        return new SpringwolfJmsController(publishingPayloadCreator, springwolfJmsProducer);
    }
}
