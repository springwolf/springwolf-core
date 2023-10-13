// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.controller.SpringwolfAmqpController;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.producer.SpringwolfAmqpProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;

import java.util.List;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_AMQP_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(
        prefix = SPRINGWOLF_AMQP_CONFIG_PREFIX,
        name = SPRINGWOLF_AMQP_PLUGIN_PUBLISHING_ENABLED,
        havingValue = "true")
@Import({SpringwolfAmqpScannerConfiguration.class})
public class SpringwolfAmqpProducerConfiguration {

    @Bean
    public SpringwolfAmqpProducer springwolfAmqpProducer(
            AsyncApiService asyncApiService, @NonNull List<RabbitTemplate> rabbitTemplates) {
        return new SpringwolfAmqpProducer(asyncApiService, rabbitTemplates);
    }

    @Bean
    public SpringwolfAmqpController springwolfAmqpController(
            AsyncApiDocketService asyncApiDocketService,
            SpringwolfAmqpProducer springwolfAmqpProducer,
            ObjectMapper objectMapper) {
        return new SpringwolfAmqpController(asyncApiDocketService, springwolfAmqpProducer, objectMapper);
    }
}
