// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.amqp;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.AmqpMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.AmqpOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.ClassLevelRabbitListenerScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelRabbitListenerScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
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
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED, matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ClassLevelRabbitListenerScanner classLevelRabbitListenerScanner(
            ComponentClassScanner componentClassScanner,
            SchemasService schemasService,
            List<Queue> queues,
            List<Exchange> exchanges,
            List<Binding> bindings) {
        return new ClassLevelRabbitListenerScanner(componentClassScanner, schemasService, queues, exchanges, bindings);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED, matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public MethodLevelRabbitListenerScanner methodLevelRabbitListenerScanner(
            ComponentClassScanner componentClassScanner,
            SchemasService schemasService,
            List<Queue> queues,
            List<Exchange> exchanges,
            List<Binding> bindings) {
        return new MethodLevelRabbitListenerScanner(componentClassScanner, schemasService, queues, exchanges, bindings);
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    public AmqpMessageBindingProcessor amqpMessageBindingProcessor() {
        return new AmqpMessageBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    public AmqpOperationBindingProcessor amqpOperationBindingProcessor() {
        return new AmqpOperationBindingProcessor();
    }
}
