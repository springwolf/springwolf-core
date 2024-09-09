// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationClassLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodReturnService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationClassLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings.StompBindingMessageMappingFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings.StompBindingSendToFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings.StompBindingSendToUserFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.header.AsyncHeadersForStompBuilder;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.operation.annotations.SendToCustomizer;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.operation.annotations.SendToUserCustomizer;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;

import java.util.List;

import static io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigConstants.SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED;
import static io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigConstants.SPRINGWOLF_SCANNER_STOMP_SEND_TO_ENABLED;
import static io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigConstants.SPRINGWOLF_SCANNER_STOMP_SEND_TO_USER_ENABLED;

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
    public StompBindingMessageMappingFactory stompBindingMessageMappingFactory(
            SpringwolfStompConfigProperties properties) {
        return new StompBindingMessageMappingFactory(properties);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_STOMP_SEND_TO_ENABLED, havingValue = "true", matchIfMissing = true)
    public StompBindingSendToFactory stompBindingSendToFactory(SpringwolfStompConfigProperties properties) {
        return new StompBindingSendToFactory(properties);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_SEND_TO_USER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public StompBindingSendToUserFactory stompBindingSendToUserFactory(SpringwolfStompConfigProperties properties) {
        return new StompBindingSendToUserFactory(properties);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public AsyncHeadersForStompBuilder asyncHeadersForStompBuilder() {
        return new AsyncHeadersForStompBuilder();
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_STOMP_SEND_TO_ENABLED, havingValue = "true", matchIfMissing = true)
    public SendToCustomizer sendToCustomizer(
            StompBindingSendToFactory stompBindingSendToFactory,
            PayloadMethodReturnService payloadMethodReturnService) {
        return new SendToCustomizer(stompBindingSendToFactory, payloadMethodReturnService);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_SEND_TO_USER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public SendToUserCustomizer sendToUserCustomizer(
            StompBindingSendToUserFactory stompBindingSendToUserFactory,
            PayloadMethodReturnService payloadMethodReturnService) {
        return new SendToUserCustomizer(stompBindingSendToUserFactory, payloadMethodReturnService);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ChannelsScanner simpleStompClassLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            StompBindingMessageMappingFactory stompBindingMessageMappingFactory,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationClassLevelChannelsScanner<MessageMapping, AnnotationScannerUtil.AllMethods> strategy =
                new SpringAnnotationClassLevelChannelsScanner<>(
                        MessageMapping.class,
                        AnnotationScannerUtil.AllMethods.class,
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public OperationsScanner simpleStompClassLevelListenerAnnotationOperationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            StompBindingMessageMappingFactory stompBindingMessageMappingFactory,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        SpringAnnotationClassLevelOperationsScanner<MessageMapping, AnnotationScannerUtil.AllMethods> strategy =
                new SpringAnnotationClassLevelOperationsScanner<>(
                        MessageMapping.class,
                        AnnotationScannerUtil.AllMethods.class,
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService,
                        operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ChannelsScanner simpleStompMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            StompBindingMessageMappingFactory stompBindingMessageMappingFactory,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<MessageMapping> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        MessageMapping.class,
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_MESSAGE_MAPPING_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public OperationsScanner simpleStompMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            StompBindingMessageMappingFactory stompBindingMessageMappingFactory,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        SpringAnnotationMethodLevelOperationsScanner<MessageMapping> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        MessageMapping.class,
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        operationCustomizers,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_STOMP_SEND_TO_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ChannelsScanner simpleStompMethodLevelListenerSendToAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            StompBindingSendToFactory stompBindingMessageMappingFactory,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodReturnService payloadMethodReturnService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<SendTo> strategy = new SpringAnnotationMethodLevelChannelsScanner<>(
                SendTo.class,
                stompBindingMessageMappingFactory,
                asyncHeadersForStompBuilder,
                payloadMethodReturnService,
                headerClassExtractor,
                componentsService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_SEND_TO_USER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ChannelsScanner simpleStompMethodLevelListenerSendToUserAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            StompBindingSendToUserFactory stompBindingMessageMappingFactory,
            AsyncHeadersForStompBuilder asyncHeadersForStompBuilder,
            PayloadMethodReturnService payloadMethodReturnService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<SendToUser> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        SendToUser.class,
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        payloadMethodReturnService,
                        headerClassExtractor,
                        componentsService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }
}
