// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SpringAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationClassLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.SpringAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationClassLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings.KafkaBindingFactory;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.common.header.AsyncHeadersForKafkaBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

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
    public KafkaBindingFactory kafkaBindingFactory() {
        return new KafkaBindingFactory();
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder() {
        return new AsyncHeadersForKafkaBuilder();
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationChannelsScanner simpleKafkaClassLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            KafkaBindingFactory kafkaBindingFactory,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationClassLevelChannelsScanner<KafkaListener, KafkaHandler> strategy =
                new SpringAnnotationClassLevelChannelsScanner<>(
                        KafkaListener.class,
                        KafkaHandler.class,
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationChannelsScanner(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleKafkaClassLevelListenerAnnotationOperationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            KafkaBindingFactory kafkaBindingFactory,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        SpringAnnotationClassLevelOperationsScanner<KafkaListener, KafkaHandler> strategy =
                new SpringAnnotationClassLevelOperationsScanner<>(
                        KafkaListener.class,
                        KafkaHandler.class,
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService,
                        operationCustomizers);

        return new SpringAnnotationOperationsScanner(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationChannelsScanner simpleKafkaMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            KafkaBindingFactory kafkaBindingFactory,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<KafkaListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        KafkaListener.class,
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationChannelsScanner(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleKafkaMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            KafkaBindingFactory kafkaBindingFactory,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        SpringAnnotationMethodLevelOperationsScanner<KafkaListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        KafkaListener.class,
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        operationCustomizers,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationOperationsScanner(springwolfClassScanner, strategy);
    }
}
