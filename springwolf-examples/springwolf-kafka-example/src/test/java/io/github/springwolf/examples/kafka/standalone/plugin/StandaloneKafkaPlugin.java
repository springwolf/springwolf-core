// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone.plugin;

import io.github.springwolf.bindings.kafka.configuration.SpringwolfKafkaBindingAutoConfiguration;
import io.github.springwolf.bindings.kafka.scanners.messages.KafkaMessageBindingProcessor;
import io.github.springwolf.bindings.kafka.scanners.operations.KafkaOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.examples.kafka.standalone.SpringwolfConfigPropertiesLoader;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings.KafkaBindingFactory;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.common.header.AsyncHeadersForKafkaBuilder;
import io.github.springwolf.plugins.kafka.configuration.SpringwolfKafkaScannerConfiguration;
import io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigConstants;
import io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigProperties;

import java.io.IOException;

public class StandaloneKafkaPlugin implements StandalonePlugin {

    @Override
    public StandalonePluginResult load(StandalonePluginContext context) throws IOException {
        SpringwolfKafkaScannerConfiguration kafkaScannerAutoConfiguration = new SpringwolfKafkaScannerConfiguration();

        SpringwolfKafkaConfigProperties springwolfKafkaConfigProperties = new SpringwolfConfigPropertiesLoader()
                .loadConfigProperties(
                        context.getEnvironment(),
                        SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX,
                        SpringwolfKafkaConfigProperties.class);

        KafkaBindingFactory kafkaBindingFactory =
                kafkaScannerAutoConfiguration.kafkaBindingFactory(context.getStringValueResolver());
        AsyncHeadersForKafkaBuilder asyncHeadersForKafkaBuilder =
                kafkaScannerAutoConfiguration.asyncHeadersForKafkaBuilder();
        ChannelsScanner simpleKafkaClassLevelListenerAnnotationChannelsScanner =
                kafkaScannerAutoConfiguration.simpleKafkaClassLevelListenerAnnotationChannelsScanner(
                        context.getClassScanner(),
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService());
        OperationsScanner simpleKafkaClassLevelListenerAnnotationOperationScanner =
                kafkaScannerAutoConfiguration.simpleKafkaClassLevelListenerAnnotationOperationScanner(
                        context.getClassScanner(),
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService(),
                        context.getOperationCustomizers());
        ChannelsScanner simpleKafkaMethodLevelListenerAnnotationChannelsScanner =
                kafkaScannerAutoConfiguration.simpleKafkaMethodLevelListenerAnnotationChannelsScanner(
                        context.getClassScanner(),
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService());
        OperationsScanner simpleKafkaMethodLevelListenerAnnotationOperationsScanner =
                kafkaScannerAutoConfiguration.simpleKafkaMethodLevelListenerAnnotationOperationsScanner(
                        context.getClassScanner(),
                        kafkaBindingFactory,
                        asyncHeadersForKafkaBuilder,
                        context.getPayloadMethodParameterService(),
                        context.getHeaderClassExtractor(),
                        context.getComponentsService(),
                        context.getOperationCustomizers());

        SpringwolfKafkaBindingAutoConfiguration springwolfKafkaBindingAutoConfiguration =
                new SpringwolfKafkaBindingAutoConfiguration();
        KafkaOperationBindingProcessor kafkaOperationBindingProcessor =
                springwolfKafkaBindingAutoConfiguration.kafkaOperationBindingProcessor(
                        context.getStringValueResolver());
        KafkaMessageBindingProcessor kafkaMessageBindingProcessor =
                springwolfKafkaBindingAutoConfiguration.kafkaMessageBindingProcessor(context.getStringValueResolver());

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
