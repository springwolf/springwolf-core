// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SpringAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationClassLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.SpringAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationClassLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings.AmqpBindingFactory;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.common.headers.AsyncHeadersForAmqpBuilder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

import static io.github.springwolf.plugins.amqp.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED;

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
    public SpringAnnotationChannelsScanner simpleRabbitClassLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            AmqpBindingFactory amqpBindingBuilder,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationClassLevelChannelsScanner<RabbitListener, RabbitHandler> strategy =
                new SpringAnnotationClassLevelChannelsScanner<>(
                        RabbitListener.class,
                        RabbitHandler.class,
                        amqpBindingBuilder,
                        asyncHeadersForAmqpBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleRabbitClassLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            AmqpBindingFactory amqpBindingBuilder,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationClassLevelOperationsScanner<RabbitListener, RabbitHandler> strategy =
                new SpringAnnotationClassLevelOperationsScanner<>(
                        RabbitListener.class,
                        RabbitHandler.class,
                        amqpBindingBuilder,
                        asyncHeadersForAmqpBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationOperationsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationChannelsScanner simpleRabbitMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            AmqpBindingFactory amqpBindingBuilder,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<RabbitListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        RabbitListener.class,
                        amqpBindingBuilder,
                        asyncHeadersForAmqpBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleRabbitMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            AmqpBindingFactory amqpBindingBuilder,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelOperationsScanner<RabbitListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        RabbitListener.class,
                        amqpBindingBuilder,
                        asyncHeadersForAmqpBuilder,
                        List.of(),
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationOperationsScanner(classScanner, strategy);
    }
}
