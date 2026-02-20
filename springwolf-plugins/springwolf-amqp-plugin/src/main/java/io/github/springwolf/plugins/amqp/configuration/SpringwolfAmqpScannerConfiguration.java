// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationClassLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.channel.inferrer.ChannelNameInferrer;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessagesService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationsService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationCustomizer;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationClassLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings.AmqpBindingFactory;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.channels.RabbitQueueBeanScanner;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.common.headers.AsyncHeadersForAmqpBuilder;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.common.inferrer.RabbitListenerChannelNameInferrer;
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
import org.springframework.util.StringValueResolver;

import java.util.List;

import static io.github.springwolf.plugins.amqp.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED;

/**
 * Spring configuration defining the scanner beans for this amqp plugin.
 */
@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfAmqpScannerConfiguration {

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean
    public AmqpBindingFactory amqpBindingFactory(
            List<Queue> queues,
            List<Exchange> exchanges,
            List<Binding> bindings,
            StringValueResolver stringValueResolver) {
        return new AmqpBindingFactory(queues, exchanges, bindings, stringValueResolver);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean
    public AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder() {
        return new AsyncHeadersForAmqpBuilder();
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    @ConditionalOnMissingBean(name = "simpleRabbitClassLevelListenerAnnotationChannelsScanner")
    public ChannelsScanner simpleRabbitClassLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            AmqpBindingFactory amqpBindingFactory,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        final var springAnnotationMessagesService = new SpringAnnotationMessagesService<>(
                amqpBindingFactory,
                asyncHeadersForAmqpBuilder,
                payloadMethodParameterService,
                headerClassExtractor,
                componentsService);
        final var springAnnotationChannelService = new SpringAnnotationChannelService<>(amqpBindingFactory);
        SpringAnnotationClassLevelChannelsScanner<RabbitListener, RabbitHandler> strategy =
                new SpringAnnotationClassLevelChannelsScanner<>(
                        RabbitListener.class,
                        RabbitHandler.class,
                        springAnnotationMessagesService,
                        springAnnotationChannelService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    @ConditionalOnMissingBean(name = "simpleRabbitClassLevelListenerAnnotationOperationsScanner")
    public OperationsScanner simpleRabbitClassLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            AmqpBindingFactory amqpBindingFactory,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        final var springAnnotationOperationsService = new SpringAnnotationOperationsService<>(
                amqpBindingFactory,
                new SpringAnnotationMessagesService<>(
                        amqpBindingFactory,
                        asyncHeadersForAmqpBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService));
        SpringAnnotationClassLevelOperationsScanner<RabbitListener, RabbitHandler> strategy =
                new SpringAnnotationClassLevelOperationsScanner<>(
                        RabbitListener.class,
                        RabbitHandler.class,
                        springAnnotationOperationsService,
                        operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    @ConditionalOnMissingBean(name = "simpleRabbitMethodLevelListenerAnnotationChannelsScanner")
    public ChannelsScanner simpleRabbitMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            AmqpBindingFactory amqpBindingFactory,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        final var springAnnotationChannelService = new SpringAnnotationChannelService<>(amqpBindingFactory);
        final var springAnnotationMessageService =
                new SpringAnnotationMessageService<>(amqpBindingFactory, asyncHeadersForAmqpBuilder, componentsService);
        SpringAnnotationMethodLevelChannelsScanner<RabbitListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        RabbitListener.class,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        springAnnotationChannelService,
                        springAnnotationMessageService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    @ConditionalOnMissingBean(name = "simpleRabbitMethodLevelListenerAnnotationOperationsScanner")
    public OperationsScanner simpleRabbitMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            AmqpBindingFactory amqpBindingFactory,
            AsyncHeadersForAmqpBuilder asyncHeadersForAmqpBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        final var springAnnotationOperationService = new SpringAnnotationOperationService<>(
                amqpBindingFactory,
                new SpringAnnotationMessageService<>(
                        amqpBindingFactory, asyncHeadersForAmqpBuilder, componentsService));
        SpringAnnotationMethodLevelOperationsScanner<RabbitListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        RabbitListener.class,
                        headerClassExtractor,
                        payloadMethodParameterService,
                        springAnnotationOperationService,
                        operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    @ConditionalOnMissingBean
    public RabbitQueueBeanScanner rabbitQueueBeanScanner(List<Queue> queues, List<Binding> bindings) {
        return new RabbitQueueBeanScanner(queues, bindings);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean
    public ChannelNameInferrer rabbitListenerChannelNameInferrer(StringValueResolver stringValueResolver) {
        return new RabbitListenerChannelNameInferrer(stringValueResolver);
    }
}
