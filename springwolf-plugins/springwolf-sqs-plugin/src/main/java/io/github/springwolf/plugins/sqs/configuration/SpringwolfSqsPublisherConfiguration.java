// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.configuration;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.github.springwolf.core.controller.publish.PublishingPayloadCreator;
import io.github.springwolf.plugins.sqs.controller.SpringwolfSqsController;
import io.github.springwolf.plugins.sqs.publish.SpringwolfSqsPublisher;
import io.github.springwolf.web.controller.publish.PublishingBaseController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static io.github.springwolf.plugins.sqs.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SQS_CONFIG_PREFIX;
import static io.github.springwolf.plugins.sqs.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SQS_PLUGIN_PUBLISHING_ENABLED;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        prefix = SPRINGWOLF_SQS_CONFIG_PREFIX,
        name = SPRINGWOLF_SQS_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
public class SpringwolfSqsPublisherConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfSqsPublisher springwolfSqsPublisher(List<SqsTemplate> sqsTemplates) {
        return new SpringwolfSqsPublisher(sqsTemplates);
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(PublishingBaseController.class)
    public static class WebConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public SpringwolfSqsController springwolfSqsController(
                PublishingPayloadCreator publishingPayloadCreator, SpringwolfSqsPublisher springwolfSqsPublisher) {
            return new SpringwolfSqsController(publishingPayloadCreator, springwolfSqsPublisher);
        }
    }
}
