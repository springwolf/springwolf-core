// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.kafka;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.KafkaMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.KafkaOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.ClassLevelKafkaListenerScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelKafkaListenerScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SpringPayloadAnnotationTypeExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigConstants.SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED;

/**
 * spring configuration defining the scanner beans for the kafka plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfKafkaScannerConfiguration {

    @Bean
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public ClassLevelKafkaListenerScanner classLevelKafkaListenerScanner(
            ComponentClassScanner componentClassScanner,
            SchemasService schemasService,
            SpringPayloadAnnotationTypeExtractor springPayloadAnnotationTypeExtractor) {
        return new ClassLevelKafkaListenerScanner(
                componentClassScanner, schemasService, springPayloadAnnotationTypeExtractor);
    }

    @Bean
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_KAFKA_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public MethodLevelKafkaListenerScanner methodLevelKafkaListenerScanner(
            ComponentClassScanner componentClassScanner,
            SchemasService schemasService,
            SpringPayloadAnnotationTypeExtractor springPayloadAnnotationTypeExtractor) {
        return new MethodLevelKafkaListenerScanner(
                componentClassScanner, schemasService, springPayloadAnnotationTypeExtractor);
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
