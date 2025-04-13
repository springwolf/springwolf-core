// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.asb.asyncapi;

import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import java.util.List;

import static io.github.springwolf.plugins.asb.asyncapi.SpringwolfAsbConfigConstants.*;


@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        prefix = SPRINGWOLF_AMQP_CONFIG_PREFIX,
        name = SPRINGWOLF_ASB_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
public class SpringwolfAsbProducerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfAsbProducer springwolfAmqpProducer(
            AsyncApiService asyncApiService,
            @NonNull List<ServiceBusSenderAsyncClient> senderAsyncClients
    ) {
        return new SpringwolfAsbProducer(asyncApiService, senderAsyncClients);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfAsbController springwolfAmqpController(
            PublishingPayloadCreator publishingPayloadCreator,
            SpringwolfAsbProducer springwolfAsbProducer
    ) {
        return new SpringwolfAsbController(publishingPayloadCreator, springwolfAsbProducer);
    }
}
