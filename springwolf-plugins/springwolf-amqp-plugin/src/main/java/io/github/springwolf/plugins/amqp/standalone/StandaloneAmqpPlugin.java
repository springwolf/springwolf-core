// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.standalone;

import io.github.springwolf.bindings.amqp.configuration.SpringwolfAmqpBindingAutoConfiguration;
import io.github.springwolf.bindings.amqp.scanners.messages.AmqpMessageBindingProcessor;
import io.github.springwolf.bindings.amqp.scanners.operations.AmqpOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.standalone.plugin.StandalonePlugin;
import io.github.springwolf.core.standalone.plugin.StandalonePluginContext;
import io.github.springwolf.core.standalone.plugin.StandalonePluginResult;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings.AmqpBindingFactory;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.common.headers.AsyncHeadersForAmqpBuilder;
import io.github.springwolf.plugins.amqp.configuration.SpringwolfAmqpScannerConfiguration;

import java.util.Collections;

public class StandaloneAmqpPlugin implements StandalonePlugin {

    @Override
    public StandalonePluginResult load(StandalonePluginContext context) {
        SpringwolfAmqpScannerConfiguration amqpScannerAutoConfiguration = new SpringwolfAmqpScannerConfiguration();

        // TODO fix amqp beans
        AmqpBindingFactory kafkaBindingFactory = amqpScannerAutoConfiguration.amqpBindingFactory(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                context.getStringValueResolver());
        AsyncHeadersForAmqpBuilder asyncHeadersForKafkaBuilder =
                amqpScannerAutoConfiguration.asyncHeadersForAmqpBuilder();
        ChannelsScanner simpleKafkaClassLevelListenerAnnotationChannelsScanner =
                amqpScannerAutoConfiguration.simpleRabbitClassLevelListenerAnnotationChannelsScanner(
                        context.getClassScanner(),
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService());
        OperationsScanner simpleKafkaClassLevelListenerAnnotationOperationScanner =
                amqpScannerAutoConfiguration.simpleRabbitClassLevelListenerAnnotationOperationsScanner(
                        context.getClassScanner(),
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService(),
                        context.getOperationCustomizers());
        ChannelsScanner simpleKafkaMethodLevelListenerAnnotationChannelsScanner =
                amqpScannerAutoConfiguration.simpleRabbitMethodLevelListenerAnnotationChannelsScanner(
                        context.getClassScanner(),
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService());
        OperationsScanner simpleKafkaMethodLevelListenerAnnotationOperationsScanner =
                amqpScannerAutoConfiguration.simpleRabbitMethodLevelListenerAnnotationOperationsScanner(
                        context.getClassScanner(),
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService(),
                        context.getOperationCustomizers());

        SpringwolfAmqpBindingAutoConfiguration springwolfKafkaBindingAutoConfiguration =
                new SpringwolfAmqpBindingAutoConfiguration();
        AmqpOperationBindingProcessor kafkaOperationBindingProcessor =
                springwolfKafkaBindingAutoConfiguration.amqpOperationBindingProcessor(context.getStringValueResolver());
        // TODO Why dont we pass context.getStringValueResolver() here?
        AmqpMessageBindingProcessor kafkaMessageBindingProcessor =
                springwolfKafkaBindingAutoConfiguration.amqpMessageBindingProcessor();

        return StandalonePluginResult.builder()
                .channelsScanner(simpleKafkaClassLevelListenerAnnotationChannelsScanner)
                .channelsScanner(simpleKafkaMethodLevelListenerAnnotationChannelsScanner)
                .operationsScanner(simpleKafkaClassLevelListenerAnnotationOperationScanner)
                .operationsScanner(simpleKafkaMethodLevelListenerAnnotationOperationsScanner)
                .operationBindingProcessor(kafkaOperationBindingProcessor)
                .messageBindingProcessor(kafkaMessageBindingProcessor)
                .build();
    }
}
