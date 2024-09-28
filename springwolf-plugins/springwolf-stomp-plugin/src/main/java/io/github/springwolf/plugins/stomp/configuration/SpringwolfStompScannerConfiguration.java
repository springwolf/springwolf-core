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
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AllMethods;
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessagesService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationsService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodReturnService;
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
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.util.StringValueResolver;

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
            SpringwolfStompConfigProperties properties, StringValueResolver stringValueResolver) {
        return new StompBindingMessageMappingFactory(properties, stringValueResolver);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_STOMP_SEND_TO_ENABLED, havingValue = "true", matchIfMissing = true)
    public StompBindingSendToFactory stompBindingSendToFactory(
            SpringwolfStompConfigProperties properties, StringValueResolver stringValueResolver) {
        return new StompBindingSendToFactory(properties, stringValueResolver);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_STOMP_SEND_TO_USER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    public StompBindingSendToUserFactory stompBindingSendToUserFactory(
            SpringwolfStompConfigProperties properties, StringValueResolver stringValueResolver) {
        return new StompBindingSendToUserFactory(properties, stringValueResolver);
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
        val springAnnotationMessagesService = new SpringAnnotationMessagesService<>(
                stompBindingMessageMappingFactory,
                asyncHeadersForStompBuilder,
                payloadMethodParameterService,
                headerClassExtractor,
                componentsService);
        val springAnnotationChannelService = new SpringAnnotationChannelService<>(stompBindingMessageMappingFactory);
        SpringAnnotationClassLevelChannelsScanner<MessageMapping, AllMethods> strategy =
                new SpringAnnotationClassLevelChannelsScanner<>(
                        MessageMapping.class,
                        AllMethods.class,
                        springAnnotationMessagesService,
                        springAnnotationChannelService);

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
        val springAnnotationOperationsService = new SpringAnnotationOperationsService<>(
                stompBindingMessageMappingFactory,
                new SpringAnnotationMessagesService<>(
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService));
        SpringAnnotationClassLevelOperationsScanner<MessageMapping, AllMethods> strategy =
                new SpringAnnotationClassLevelOperationsScanner<>(
                        MessageMapping.class,
                        AllMethods.class,
                        stompBindingMessageMappingFactory,
                        springAnnotationOperationsService,
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
        val springAnnotationChannelService = new SpringAnnotationChannelService<>(stompBindingMessageMappingFactory);
        val springAnnotationMessageService = new SpringAnnotationMessageService<>(
                stompBindingMessageMappingFactory, asyncHeadersForStompBuilder, componentsService);
        SpringAnnotationMethodLevelChannelsScanner<MessageMapping> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        MessageMapping.class,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        springAnnotationChannelService,
                        springAnnotationMessageService);
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
        val springAnnotationOperationService = new SpringAnnotationOperationService<>(
                stompBindingMessageMappingFactory,
                new SpringAnnotationMessageService<>(
                        stompBindingMessageMappingFactory, asyncHeadersForStompBuilder, componentsService));
        SpringAnnotationMethodLevelOperationsScanner<MessageMapping> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        MessageMapping.class,
                        stompBindingMessageMappingFactory,
                        headerClassExtractor,
                        payloadMethodParameterService,
                        springAnnotationOperationService,
                        operationCustomizers);

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
        val springAnnotationChannelService = new SpringAnnotationChannelService<>(stompBindingMessageMappingFactory);
        val springAnnotationMessageService = new SpringAnnotationMessageService<>(
                stompBindingMessageMappingFactory, asyncHeadersForStompBuilder, componentsService);
        SpringAnnotationMethodLevelChannelsScanner<SendTo> strategy = new SpringAnnotationMethodLevelChannelsScanner<>(
                SendTo.class,
                payloadMethodReturnService,
                headerClassExtractor,
                springAnnotationChannelService,
                springAnnotationMessageService);
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
        val springAnnotationChannelService = new SpringAnnotationChannelService<>(stompBindingMessageMappingFactory);
        val springAnnotationMessageService = new SpringAnnotationMessageService<>(
                stompBindingMessageMappingFactory, asyncHeadersForStompBuilder, componentsService);
        SpringAnnotationMethodLevelChannelsScanner<SendToUser> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        SendToUser.class,
                        payloadMethodReturnService,
                        headerClassExtractor,
                        springAnnotationChannelService,
                        springAnnotationMessageService);
        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }
}
