// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.jms;

import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.SimpleOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.MethodLevelAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.schemas.ComponentsService;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.JmsBindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.JmsMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.JmsOperationBindingProcessor;
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
    public JmsBindingFactory jmsBindingBuilder() {
        return new JmsBindingFactory();
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleChannelsScanner simpleJmsMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            JmsBindingFactory jmsBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        MethodLevelAnnotationChannelsScanner<JmsListener> strategy = new MethodLevelAnnotationChannelsScanner<>(
                JmsListener.class, jmsBindingBuilder, payloadClassExtractor, componentsService);

        return new SimpleChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleOperationsScanner simpleJmsMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            JmsBindingFactory jmsBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        MethodLevelAnnotationOperationsScanner<JmsListener> strategy = new MethodLevelAnnotationOperationsScanner<>(
                JmsListener.class, jmsBindingBuilder, payloadClassExtractor, componentsService);

        return new SimpleOperationsScanner(classScanner, strategy);
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
