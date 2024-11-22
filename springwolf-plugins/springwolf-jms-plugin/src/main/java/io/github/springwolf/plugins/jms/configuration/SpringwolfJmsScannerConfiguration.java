// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.jms.asyncapi.scanners.bindings.JmsBindingFactory;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.util.StringValueResolver;

import java.util.List;

import static io.github.springwolf.plugins.jms.configuration.properties.SpringwolfJmsConfigConstants.SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED;

/**
 * spring configuration defining the scanner beans for the jms plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfJmsScannerConfiguration {

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    public JmsBindingFactory jmsBindingFactory(StringValueResolver stringValueResolver) {
        return new JmsBindingFactory(stringValueResolver);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ChannelsScanner simpleJmsMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            JmsBindingFactory jmsBindingFactory,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        val springAnnotationChannelService = new SpringAnnotationChannelService<>(jmsBindingFactory);
        val springAnnotationMessageService = new SpringAnnotationMessageService<>(
                jmsBindingFactory, new AsyncHeadersNotDocumented(), componentsService);
        SpringAnnotationMethodLevelChannelsScanner<JmsListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        JmsListener.class,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        springAnnotationChannelService,
                        springAnnotationMessageService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_JMS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public OperationsScanner simpleJmsMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            JmsBindingFactory jmsBindingFactory,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        val springAnnotationOperationService = new SpringAnnotationOperationService<>(
                jmsBindingFactory,
                new SpringAnnotationMessageService<>(
                        jmsBindingFactory, new AsyncHeadersNotDocumented(), componentsService));
        SpringAnnotationMethodLevelOperationsScanner<JmsListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        JmsListener.class,
                        headerClassExtractor,
                        payloadMethodParameterService,
                        springAnnotationOperationService,
                        operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }
}
