// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.kafka;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.KafkaBindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.KafkaMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.KafkaOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.ClassLevelAnnotationChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeadersForKafkaBuilder;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED;

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
    public SimpleChannelsScanner simpleKafkaClassLevelListenerAnnotationChannelsScanner(
            ComponentClassScanner classScanner,
            KafkaBindingFactory kafkaBindingBuilder,
            AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder,
            PayloadClassExtractor payloadClassExtractor,
            SchemasService schemasService) {
        ClassLevelAnnotationChannelsScanner<KafkaListener, KafkaHandler> strategy =
                new ClassLevelAnnotationChannelsScanner<>(
                        KafkaListener.class,
                        KafkaHandler.class,
                        kafkaBindingBuilder,
                        asyncHeadersForKafkaBuilder,
                        payloadClassExtractor,
                        schemasService);

        return new SimpleChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleChannelsScanner simpleKafkaMethodLevelListenerAnnotationChannelsScanner(
            ComponentClassScanner classScanner,
            KafkaBindingFactory kafkaBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            SchemasService schemasService) {
        MethodLevelAnnotationChannelsScanner<KafkaListener> strategy = new MethodLevelAnnotationChannelsScanner<>(
                KafkaListener.class, kafkaBindingBuilder, payloadClassExtractor, schemasService);

        return new SimpleChannelsScanner(classScanner, strategy);
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
