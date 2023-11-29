// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.SqsBindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.SqsMessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.SqsOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.MethodLevelAnnotationChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED;

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
            SchemasService schemasService) {
        MethodLevelAnnotationChannelsScanner<SqsListener> strategy = new MethodLevelAnnotationChannelsScanner<>(
                SqsListener.class, sqsBindingBuilder, payloadClassExtractor, schemasService);

        return new SimpleChannelsScanner(classScanner, strategy);
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
