// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SpringAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationClassLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.ClassLevelAnnotationScanner;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.SpringAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationClassLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings.StompBindingFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.header.AsyncHeadersForStompBuilder;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;

import static io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigConstants.SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED;

/**
 * spring configuration defining the scanner beans for the stomp plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfStompScannerConfiguration {

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public StompBindingFactory stompBindingBuilder(SpringwolfStompConfigProperties properties) {
        return new StompBindingFactory(properties);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public AsyncHeadersForStompBuilder stompAsyncHeadersBuilder() {
        return new AsyncHeadersForStompBuilder();
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationChannelsScanner simpleStompClassLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            StompBindingFactory stompBindingBuilder,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodParameterService payloadService,
            ComponentsService componentsService) {
        SpringAnnotationClassLevelChannelsScanner<MessageMapping, ClassLevelAnnotationScanner.AllMethods> strategy =
                new SpringAnnotationClassLevelChannelsScanner<>(
                        MessageMapping.class,
                        ClassLevelAnnotationScanner.AllMethods.class,
                        stompBindingBuilder,
                        asyncHeadersForStompBuilder,
                        payloadService,
                        componentsService);

        return new SpringAnnotationChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleStompClassLevelListenerAnnotationOperationScanner(
            SpringwolfClassScanner classScanner,
            StompBindingFactory stompBindingBuilder,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodParameterService payloadService,
            ComponentsService componentsService) {
        SpringAnnotationClassLevelOperationsScanner<MessageMapping, ClassLevelAnnotationScanner.AllMethods> strategy =
                new SpringAnnotationClassLevelOperationsScanner<>(
                        MessageMapping.class,
                        ClassLevelAnnotationScanner.AllMethods.class,
                        stompBindingBuilder,
                        asyncHeadersForStompBuilder,
                        payloadService,
                        componentsService);

        return new SpringAnnotationOperationsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationChannelsScanner simpleStompMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            StompBindingFactory stompBindingBuilder,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodParameterService payloadService,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<MessageMapping> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        MessageMapping.class,
                        stompBindingBuilder,
                        asyncHeadersForStompBuilder,
                        payloadService,
                        componentsService);

        return new SpringAnnotationChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleStompMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            StompBindingFactory stompBindingBuilder,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodParameterService payloadService,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelOperationsScanner<MessageMapping> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        MessageMapping.class,
                        stompBindingBuilder,
                        asyncHeadersForStompBuilder,
                        payloadService,
                        componentsService);

        return new SpringAnnotationOperationsScanner(classScanner, strategy);
    }
}
