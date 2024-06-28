// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SpringAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.SpringAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.jms.asyncapi.scanners.bindings.JmsBindingFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;

import java.util.List;

import static io.github.springwolf.plugins.jms.configuration.properties.SpringwolfJmsConfigConstants.SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED;

/**
 * spring configuration defining the scanner beans for the jms plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfJmsScannerConfiguration {

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    public JmsBindingFactory jmsBindingFactory() {
        return new JmsBindingFactory();
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationChannelsScanner simpleJmsMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            JmsBindingFactory jmsBindingFactory,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<JmsListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        JmsListener.class,
                        jmsBindingFactory,
                        new AsyncHeadersNotDocumented(),
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationChannelsScanner(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleJmsMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            JmsBindingFactory jmsBindingFactory,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelOperationsScanner<JmsListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        JmsListener.class,
                        jmsBindingFactory,
                        new AsyncHeadersNotDocumented(),
                        List.of(),
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationOperationsScanner(springwolfClassScanner, strategy);
    }
}
