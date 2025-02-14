// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationClassLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessagesService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationsService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationClassLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings.KafkaBindingFactory;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.common.header.AsyncHeadersForKafkaBuilder;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.util.List;

import static io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED;

/**
 * spring configuration defining the scanner beans for the kafka plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfKafkaScannerConfiguration {

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "kafkaBindingFactory")
    public KafkaBindingFactory kafkaBindingFactory(StringValueResolver stringValueResolver) {
        return new KafkaBindingFactory(stringValueResolver);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "asyncHeadersForKafkaBuilder")
    public AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder() {
        return new AsyncHeadersForKafkaBuilder();
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "simpleKafkaClassLevelListenerAnnotationChannelsScanner")
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ChannelsScanner simpleKafkaClassLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            KafkaBindingFactory kafkaBindingFactory,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        val springAnnotationMessagesService = new SpringAnnotationMessagesService<>(
                kafkaBindingFactory,
                asyncHeadersForKafkaBuilder,
                payloadMethodParameterService,
                headerClassExtractor,
                componentsService);
        val springAnnotationChannelService = new SpringAnnotationChannelService<>(kafkaBindingFactory);
        SpringAnnotationClassLevelChannelsScanner<KafkaListener, KafkaHandler> strategy =
                new SpringAnnotationClassLevelChannelsScanner<>(
                        KafkaListener.class,
                        KafkaHandler.class,
                        springAnnotationMessagesService,
                        springAnnotationChannelService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "simpleKafkaClassLevelListenerAnnotationOperationScanner")
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public OperationsScanner simpleKafkaClassLevelListenerAnnotationOperationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            KafkaBindingFactory kafkaBindingFactory,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        val springAnnotationOperationsService = new SpringAnnotationOperationsService<>(
                kafkaBindingFactory,
                new SpringAnnotationMessagesService<>(
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService));
        SpringAnnotationClassLevelOperationsScanner<KafkaListener, KafkaHandler> strategy =
                new SpringAnnotationClassLevelOperationsScanner<>(
                        KafkaListener.class,
                        KafkaHandler.class,
                        springAnnotationOperationsService,
                        operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "simpleKafkaMethodLevelListenerAnnotationChannelsScanner")
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ChannelsScanner simpleKafkaMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            KafkaBindingFactory kafkaBindingFactory,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        val springAnnotationChannelService = new SpringAnnotationChannelService<>(kafkaBindingFactory);
        val springAnnotationMessageService = new SpringAnnotationMessageService<>(
                kafkaBindingFactory, asyncHeadersForKafkaBuilder, componentsService);
        SpringAnnotationMethodLevelChannelsScanner<KafkaListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        KafkaListener.class,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        springAnnotationChannelService,
                        springAnnotationMessageService);
        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "simpleKafkaMethodLevelListenerAnnotationOperationsScanner")
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public OperationsScanner simpleKafkaMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            KafkaBindingFactory kafkaBindingFactory,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        val springAnnotationOperationService = new SpringAnnotationOperationService<>(
                kafkaBindingFactory,
                new SpringAnnotationMessageService<>(
                        kafkaBindingFactory, asyncHeadersForKafkaBuilder, componentsService));
        SpringAnnotationMethodLevelOperationsScanner<KafkaListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        KafkaListener.class,
                        headerClassExtractor,
                        payloadMethodParameterService,
                        springAnnotationOperationService,
                        operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }
}
