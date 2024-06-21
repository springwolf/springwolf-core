// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.configuration;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.SpringAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.SpringAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.sqs.asyncapi.scanners.bindings.SqsBindingFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import static io.github.springwolf.plugins.sqs.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED;

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
    public SpringAnnotationChannelsScanner simpleSqsMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner classScanner,
            SqsBindingFactory sqsBindingBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<SqsListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        SqsListener.class,
                        sqsBindingBuilder,
                        new AsyncHeadersNotDocumented(),
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationChannelsScanner(classScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public SpringAnnotationOperationsScanner simpleSqsMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner classScanner,
            SqsBindingFactory sqsBindingBuilder,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelOperationsScanner<SqsListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        SqsListener.class,
                        sqsBindingBuilder,
                        new AsyncHeadersNotDocumented(),
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new SpringAnnotationOperationsScanner(classScanner, strategy);
    }
}
