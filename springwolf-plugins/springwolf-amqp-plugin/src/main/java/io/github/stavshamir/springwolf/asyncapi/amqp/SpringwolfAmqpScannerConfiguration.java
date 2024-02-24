// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.amqp;

import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.SimpleOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.ClassLevelAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.ClassLevelAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.MethodLevelAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.schemas.ComponentsService;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.AmqpBindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.AmqpMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.AmqpOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeadersForAmqpBuilder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
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
    public AmqpBindingFactory amqpBindingBuilder(List<Queue> queues, List<Exchange> exchanges, List<Binding> bindings) {
        return new AmqpBindingFactory(queues, exchanges, bindings);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder() {
        return new AsyncHeadersForAmqpBuilder();
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleChannelsScanner simpleRabbitClassLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            AmqpBindingFactory amqpBindingBuilder,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        ClassLevelAnnotationChannelsScanner<RabbitListener, RabbitHandler> strategy =
                new ClassLevelAnnotationChannelsScanner<>(
                        RabbitListener.class,
                        RabbitHandler.class,
                        amqpBindingBuilder,
                        asyncHeadersForAmqpBuilder,
                        payloadClassExtractor,
                        componentsService);

        return new SimpleChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleOperationsScanner simpleRabbitClassLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            AmqpBindingFactory amqpBindingBuilder,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        ClassLevelAnnotationOperationsScanner<RabbitListener, RabbitHandler> strategy =
                new ClassLevelAnnotationOperationsScanner<>(
                        RabbitListener.class,
                        RabbitHandler.class,
                        amqpBindingBuilder,
                        asyncHeadersForAmqpBuilder,
                        payloadClassExtractor,
                        componentsService);

        return new SimpleOperationsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleChannelsScanner simpleRabbitMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            AmqpBindingFactory amqpBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        MethodLevelAnnotationChannelsScanner<RabbitListener> strategy = new MethodLevelAnnotationChannelsScanner<>(
                RabbitListener.class, amqpBindingBuilder, payloadClassExtractor, componentsService);

        return new SimpleChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleOperationsScanner simpleRabbitMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            AmqpBindingFactory amqpBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        MethodLevelAnnotationOperationsScanner<RabbitListener> strategy = new MethodLevelAnnotationOperationsScanner<>(
                RabbitListener.class, amqpBindingBuilder, payloadClassExtractor, componentsService);

        return new SimpleOperationsScanner(classScanner, strategy);
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
