// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings.KafkaBindingFactory;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.common.header.AsyncHeadersForKafkaBuilder;
import io.github.springwolf.plugins.kafka.configuration.SpringwolfKafkaScannerConfiguration;

import java.io.IOException;

public class KafkaPluginContext implements StandalonePluginContext {

    @Override
    public StandalonePluginResult load(StandaloneContext context) throws IOException {
        SpringwolfKafkaScannerConfiguration kafkaScannerAutoConfiguration = new SpringwolfKafkaScannerConfiguration();

        //        Environment environment = new SpringwolfConfigPropertiesLoader().loadEnvironment();
        //        SpringwolfKafkaConfigProperties springwolfKafkaConfigProperties = new
        // SpringwolfConfigPropertiesLoader()
        //                .loadConfigProperties(
        //                        environment,
        //                        SpringwolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX,
        //                        SpringwolfKafkaConfigProperties.class);
        // TODO:

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

        return StandalonePluginResult.builder()
                .channelsScanner(simpleKafkaClassLevelListenerAnnotationChannelsScanner)
                .channelsScanner(simpleKafkaMethodLevelListenerAnnotationChannelsScanner)
                .operationsScanner(simpleKafkaClassLevelListenerAnnotationOperationScanner)
                .operationsScanner(simpleKafkaMethodLevelListenerAnnotationOperationsScanner)
                .build();
    }
}
