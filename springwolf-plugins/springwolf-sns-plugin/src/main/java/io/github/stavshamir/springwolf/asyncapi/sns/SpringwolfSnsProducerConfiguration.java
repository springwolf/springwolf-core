// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.sns;

import io.awspring.cloud.sns.core.SnsTemplate;
import io.github.springwolf.core.asyncapi.controller.PublishingPayloadCreator;
import io.github.stavshamir.springwolf.asyncapi.controller.SpringwolfSnsController;
import io.github.stavshamir.springwolf.producer.SpringwolfSnsProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSnsConfigConstants.SPRINGWOLF_SNS_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSnsConfigConstants.SPRINGWOLF_SNS_PLUGIN_PUBLISHING_ENABLED;

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
