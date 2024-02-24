// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.configuration;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.SimpleOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.MethodLevelAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.schemas.ComponentsService;
import io.github.springwolf.plugins.sqs.scanners.bindings.SqsBindingFactory;
import io.github.springwolf.plugins.sqs.scanners.bindings.processor.SqsMessageBindingProcessor;
import io.github.springwolf.plugins.sqs.scanners.bindings.processor.SqsOperationBindingProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import static io.github.springwolf.plugins.sqs.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED;

/**
 * spring configuration defining the scanner beans for the kafka plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfSqsScannerConfiguration {

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    public SqsBindingFactory sqsBindingBuilder() {
        return new SqsBindingFactory();
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleChannelsScanner simpleSqsMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            SqsBindingFactory sqsBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        MethodLevelAnnotationChannelsScanner<SqsListener> strategy = new MethodLevelAnnotationChannelsScanner<>(
                SqsListener.class, sqsBindingBuilder, payloadClassExtractor, componentsService);

        return new SimpleChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SimpleOperationsScanner simpleSqsMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            SqsBindingFactory sqsBindingBuilder,
            PayloadClassExtractor payloadClassExtractor,
            ComponentsService componentsService) {
        MethodLevelAnnotationOperationsScanner<SqsListener> strategy = new MethodLevelAnnotationOperationsScanner<>(
                SqsListener.class, sqsBindingBuilder, payloadClassExtractor, componentsService);

        return new SimpleOperationsScanner(classScanner, strategy);
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public SqsMessageBindingProcessor sqsMessageBindingProcessor() {
        return new SqsMessageBindingProcessor();
    }

    @Bean
    @Order(value = BindingProcessorPriority.PROTOCOL_BINDING)
    @ConditionalOnMissingBean
    public SqsOperationBindingProcessor sqsOperationBindingProcessor() {
        return new SqsOperationBindingProcessor();
    }
}
