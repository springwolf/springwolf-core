// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.configuration;

import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SpringAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.SpringAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationClassLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationClassLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.plugins.kafka.scanners.bindings.KafkaBindingFactory;
import io.github.springwolf.plugins.kafka.scanners.bindings.processor.KafkaMessageBindingProcessor;
import io.github.springwolf.plugins.kafka.scanners.bindings.processor.KafkaOperationBindingProcessor;
import io.github.springwolf.plugins.kafka.types.channel.operation.message.header.AsyncHeadersForKafkaBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

import static io.github.springwolf.plugins.kafka.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED;

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
    public KafkaBindingFactory kafkaBindingBuilder() {
        return new KafkaBindingFactory();
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public AsyncHeadersForKafkaBuilder kafkaAsyncHeadersBuilder() {
        return new AsyncHeadersForKafkaBuilder();
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationChannelsScanner simpleKafkaClassLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            KafkaBindingFactory kafkaBindingBuilder,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationClassLevelChannelsScanner<KafkaListener, KafkaHandler> strategy =
                new SpringAnnotationClassLevelChannelsScanner<>(
                        KafkaListener.class,
                        KafkaHandler.class,
                        kafkaBindingBuilder,
                        asyncHeadersForKafkaBuilder,
                        payloadClassExtractor,
                        componentsService);

        return new SpringAnnotationChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleKafkaClassLevelListenerAnnotationOperationScanner(
            SpringwolfClassScanner classScanner,
            KafkaBindingFactory kafkaBindingBuilder,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationClassLevelOperationsScanner<KafkaListener, KafkaHandler> strategy =
                new SpringAnnotationClassLevelOperationsScanner<>(
                        KafkaListener.class,
                        KafkaHandler.class,
                        kafkaBindingBuilder,
                        asyncHeadersForKafkaBuilder,
                        payloadClassExtractor,
                        componentsService);

        return new SpringAnnotationOperationsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationChannelsScanner simpleKafkaMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            KafkaBindingFactory kafkaBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<KafkaListener> strategy = new SpringAnnotationMethodLevelChannelsScanner<>(
                KafkaListener.class, kafkaBindingBuilder, payloadClassExtractor, componentsService);

        return new SpringAnnotationChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleKafkaMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            KafkaBindingFactory kafkaBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelOperationsScanner<KafkaListener> strategy = new SpringAnnotationMethodLevelOperationsScanner<>(
                KafkaListener.class, kafkaBindingBuilder, payloadClassExtractor, componentsService);

        return new SpringAnnotationOperationsScanner(classScanner, strategy);
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public KafkaMessageBindingProcessor kafkaMessageBindingProcessor() {
        return new KafkaMessageBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public KafkaOperationBindingProcessor kafkaOperationBindingProcessor() {
        return new KafkaOperationBindingProcessor();
    }
}
