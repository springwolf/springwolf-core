// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.amqp;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.AmqpBindingBuilder;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.AmqpMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.AmqpOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.ClassLevelRabbitListenerScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED;

/**
 * Spring configuration defining the scanner beans for this amqp plugin.
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfAmqpScannerConfiguration {

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ClassLevelRabbitListenerScanner classLevelRabbitListenerScanner(
            ComponentClassScanner componentClassScanner,
            SchemasService schemasService,
            PayloadClassExtractor payloadClassExtractor,
            List<Queue> queues,
            List<Exchange> exchanges,
            List<Binding> bindings) {
        return new ClassLevelRabbitListenerScanner(
                componentClassScanner, schemasService, payloadClassExtractor, queues, exchanges, bindings);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public AmqpBindingBuilder amqpBindingBuilder(List<Queue> queues, List<Exchange> exchanges, List<Binding> bindings) {
        return new AmqpBindingBuilder(queues, exchanges, bindings);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleChannelsScanner simpleRabbitMethodLevelListenerAnnotationChannelsScanner(
            ComponentClassScanner classScanner,
            AmqpBindingBuilder amqpBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            SchemasService schemasService) {
        MethodLevelAnnotationChannelsScanner<RabbitListener> strategy = new MethodLevelAnnotationChannelsScanner<>(
                RabbitListener.class, amqpBindingBuilder, payloadClassExtractor, schemasService);

        return new SimpleChannelsScanner(classScanner, strategy);
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public AmqpMessageBindingProcessor amqpMessageBindingProcessor() {
        return new AmqpMessageBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public AmqpOperationBindingProcessor amqpOperationBindingProcessor() {
        return new AmqpOperationBindingProcessor();
    }
}
