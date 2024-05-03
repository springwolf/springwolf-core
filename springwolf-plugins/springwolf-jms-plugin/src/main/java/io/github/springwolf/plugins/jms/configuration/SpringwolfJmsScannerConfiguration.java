// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SpringAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.operations.SpringAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.jms.asyncapi.scanners.bindings.JmsBindingFactory;
import io.github.springwolf.plugins.jms.asyncapi.scanners.bindings.messages.JmsMessageBindingProcessor;
import io.github.springwolf.plugins.jms.asyncapi.scanners.bindings.operations.JmsOperationBindingProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;

import static io.github.springwolf.plugins.jms.configuration.properties.SpringwolfJmsConfigConstants.SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED;

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
    public SpringAnnotationChannelsScanner simpleJmsMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            JmsBindingFactory jmsBindingBuilder,
            PayloadService payloadService,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<JmsListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        JmsListener.class,
                        jmsBindingBuilder,
                        new AsyncHeadersNotDocumented(),
                        payloadService,
                        componentsService);

        return new SpringAnnotationChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleJmsMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            JmsBindingFactory jmsBindingBuilder,
            PayloadService payloadService,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelOperationsScanner<JmsListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        JmsListener.class,
                        jmsBindingBuilder,
                        new AsyncHeadersNotDocumented(),
                        payloadService,
                        componentsService);

        return new SpringAnnotationOperationsScanner(classScanner, strategy);
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
