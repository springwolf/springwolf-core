// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.configuration;

import io.awspring.cloud.sns.core.SnsTemplate;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.plugins.sns.controller.SpringwolfSnsController;
import io.github.springwolf.plugins.sns.producer.SpringwolfSnsProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static io.github.springwolf.plugins.sns.configuration.properties.SpringwolfSnsConfigConstants.SPRINGWOLF_SNS_CONFIG_PREFIX;
import static io.github.springwolf.plugins.sns.configuration.properties.SpringwolfSnsConfigConstants.SPRINGWOLF_SNS_PLUGIN_PUBLISHING_ENABLED;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        prefix = SPRINGWOLF_SNS_CONFIG_PREFIX,
        name = SPRINGWOLF_SNS_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
public class SpringwolfSnsProducerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfSnsController springwolfSnsController(
            PublishingPayloadCreator publishingPayloadCreator, SpringwolfSnsProducer springwolfSnsProducer) {
        return new SpringwolfSnsController(publishingPayloadCreator, springwolfSnsProducer);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfSnsProducer springwolfSnsProducer(List<SnsTemplate> snsTemplate) {
        return new SpringwolfSnsProducer(snsTemplate);
    }
}
