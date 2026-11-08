// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.configuration;

import io.awspring.cloud.sns.core.SnsTemplate;
import io.github.springwolf.core.controller.publish.PublishingPayloadCreator;
import io.github.springwolf.plugins.sns.controller.SpringwolfSnsController;
import io.github.springwolf.plugins.sns.publish.SpringwolfSnsPublisher;
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
public class SpringwolfSnsPublisherConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfSnsController springwolfSnsController(
            PublishingPayloadCreator publishingPayloadCreator, SpringwolfSnsPublisher springwolfSnsPublisher) {
        return new SpringwolfSnsController(publishingPayloadCreator, springwolfSnsPublisher);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfSnsPublisher springwolfSnsProducer(List<SnsTemplate> snsTemplate) {
        return new SpringwolfSnsPublisher(snsTemplate);
    }
}
