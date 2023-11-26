// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.jms;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.JmsBindingBuilder;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.JmsMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.JmsOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfJmsConfigConstants.SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED;

/**
 * spring configuration defining the scanner beans for the jms plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfJmsScannerConfiguration {

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    public JmsBindingBuilder jmsBindingBuilder() {
        return new JmsBindingBuilder();
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleChannelsScanner simpleJmsMethodLevelListenerAnnotationChannelsScanner(
            ComponentClassScanner classScanner,
            JmsBindingBuilder jmsBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            SchemasService schemasService) {
        MethodLevelAnnotationChannelsScanner<JmsListener> strategy = new MethodLevelAnnotationChannelsScanner<>(
                JmsListener.class, jmsBindingBuilder, payloadClassExtractor, schemasService);

        return new SimpleChannelsScanner(classScanner, strategy);
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public JmsMessageBindingProcessor jmsMessageBindingProcessor() {
        return new JmsMessageBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public JmsOperationBindingProcessor jmsOperationBindingProcessor() {
        return new JmsOperationBindingProcessor();
    }
}
