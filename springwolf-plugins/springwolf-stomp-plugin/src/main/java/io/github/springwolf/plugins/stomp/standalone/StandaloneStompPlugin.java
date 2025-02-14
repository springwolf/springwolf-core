// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.standalone;

import io.github.springwolf.bindings.stomp.configuration.SpringwolfStompBindingAutoConfiguration;
import io.github.springwolf.bindings.stomp.scanners.messages.StompMessageBindingProcessor;
import io.github.springwolf.bindings.stomp.scanners.operations.StompOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.standalone.common.SpringwolfConfigPropertiesLoader;
import io.github.springwolf.core.standalone.plugin.StandalonePlugin;
import io.github.springwolf.core.standalone.plugin.StandalonePluginContext;
import io.github.springwolf.core.standalone.plugin.StandalonePluginResult;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings.StompBindingMessageMappingFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings.StompBindingSendToFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings.StompBindingSendToUserFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.header.AsyncHeadersForStompBuilder;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.operation.annotations.SendToCustomizer;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.operation.annotations.SendToUserCustomizer;
import io.github.springwolf.plugins.stomp.configuration.SpringwolfStompScannerConfiguration;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigConstants;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigProperties;

import java.util.List;

public class StandaloneStompPlugin implements StandalonePlugin {

    @Override
    public StandalonePluginResult load(StandalonePluginContext context) {
        SpringwolfStompScannerConfiguration stompScannerAutoConfiguration = new SpringwolfStompScannerConfiguration();

        SpringwolfStompConfigProperties springwolfKafkaConfigProperties = new SpringwolfConfigPropertiesLoader()
                .loadConfigProperties(
                        context.getEnvironment(),
                        SpringwolfStompConfigConstants.SPRINGWOLF_STOMP_CONFIG_PREFIX,
                        SpringwolfStompConfigProperties.class);

        StompBindingMessageMappingFactory stompBindingMessageMappingFactory =
                stompScannerAutoConfiguration.stompBindingMessageMappingFactory(
                        springwolfKafkaConfigProperties, context.getStringValueResolver());

        StompBindingSendToFactory stompBindingSendToFactory = stompScannerAutoConfiguration.stompBindingSendToFactory(
                springwolfKafkaConfigProperties, context.getStringValueResolver());

        StompBindingSendToUserFactory stompBindingSendToUserFactory =
                stompScannerAutoConfiguration.stompBindingSendToUserFactory(
                        springwolfKafkaConfigProperties, context.getStringValueResolver());

        AsyncHeadersForStompBuilder asyncHeadersForStompBuilder =
                stompScannerAutoConfiguration.asyncHeadersForStompBuilder();

        SendToCustomizer sendToCustomizer = stompScannerAutoConfiguration.sendToCustomizer(
                stompBindingSendToFactory, context.getPayloadMethodReturnService());
        SendToUserCustomizer sendToUserCustomizer = stompScannerAutoConfiguration.sendToUserCustomizer(
                stompBindingSendToUserFactory, context.getPayloadMethodReturnService());
        List<OperationCustomizer> operationCustomizers = List.of(sendToCustomizer, sendToUserCustomizer);

        ChannelsScanner simpleStompClassLevelListenerAnnotationChannelsScanner =
                stompScannerAutoConfiguration.simpleStompClassLevelListenerAnnotationChannelsScanner(
                        context.getClassScanner(),
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService());

        OperationsScanner simpleStompClassLevelListenerAnnotationOperationScanner =
                stompScannerAutoConfiguration.simpleStompClassLevelListenerAnnotationOperationScanner(
                        context.getClassScanner(),
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService(),
                        operationCustomizers);

        ChannelsScanner simpleStompMethodLevelListenerAnnotationChannelsScanner =
                stompScannerAutoConfiguration.simpleStompMethodLevelListenerAnnotationChannelsScanner(
                        context.getClassScanner(),
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService());

        OperationsScanner simpleStompMethodLevelListenerAnnotationOperationsScanner =
                stompScannerAutoConfiguration.simpleStompMethodLevelListenerAnnotationOperationsScanner(
                        context.getClassScanner(),
                        stompBindingMessageMappingFactory,
                        asyncHeadersForStompBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService(),
                        operationCustomizers);

        ChannelsScanner simpleStompMethodLevelListenerSendToAnnotationChannelsScanner =
                stompScannerAutoConfiguration.simpleStompMethodLevelListenerSendToAnnotationChannelsScanner(
                        context.getClassScanner(),
                        stompBindingSendToFactory,
                        asyncHeadersForStompBuilder,
                        context.getPayloadMethodReturnService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService());

        ChannelsScanner simpleStompMethodLevelListenerSendToUserAnnotationChannelsScanner =
                stompScannerAutoConfiguration.simpleStompMethodLevelListenerSendToUserAnnotationChannelsScanner(
                        context.getClassScanner(),
                        stompBindingSendToUserFactory,
                        asyncHeadersForStompBuilder,
                        context.getPayloadMethodReturnService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService());

        SpringwolfStompBindingAutoConfiguration springwolfStompBindingAutoConfiguration =
                new SpringwolfStompBindingAutoConfiguration();
        StompMessageBindingProcessor stompMessageBindingProcessor =
                springwolfStompBindingAutoConfiguration.stompMessageBindingProcessor();
        StompOperationBindingProcessor stompOperationBindingProcessor =
                springwolfStompBindingAutoConfiguration.stompOperationBindingProcessor(
                        context.getStringValueResolver());

        return StandalonePluginResult.builder()
                .channelsScanner(simpleStompClassLevelListenerAnnotationChannelsScanner)
                .channelsScanner(simpleStompMethodLevelListenerAnnotationChannelsScanner)
                .channelsScanner(simpleStompMethodLevelListenerSendToAnnotationChannelsScanner)
                .channelsScanner(simpleStompMethodLevelListenerSendToUserAnnotationChannelsScanner)
                .operationsScanner(simpleStompClassLevelListenerAnnotationOperationScanner)
                .operationsScanner(simpleStompMethodLevelListenerAnnotationOperationsScanner)
                .operationCustomizers(operationCustomizers)
                .messageBindingProcessor(stompMessageBindingProcessor)
                .operationBindingProcessor(stompOperationBindingProcessor)
                .build();
    }
}
