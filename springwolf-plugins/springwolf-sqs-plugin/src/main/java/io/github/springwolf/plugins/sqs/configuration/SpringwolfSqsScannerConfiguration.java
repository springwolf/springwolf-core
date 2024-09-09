// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.configuration;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.SpringAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.sqs.asyncapi.scanners.bindings.SqsBindingFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

import static io.github.springwolf.plugins.sqs.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED;

/**
 * spring configuration defining the scanner beans for the kafka plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfSqsScannerConfiguration {

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    public SqsBindingFactory sqsBindingFactory() {
        return new SqsBindingFactory();
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public ChannelsScanner simpleSqsMethodLevelListenerAnnotationChannelsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            SqsBindingFactory sqsBindingFactory,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        SpringAnnotationMethodLevelChannelsScanner<SqsListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        SqsListener.class,
                        sqsBindingFactory,
                        new AsyncHeadersNotDocumented(),
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.AUTO_DISCOVERED)
    public OperationsScanner simpleSqsMethodLevelListenerAnnotationOperationsScanner(
            SpringwolfClassScanner springwolfClassScanner,
            SqsBindingFactory sqsBindingFactory,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> operationCustomizers) {
        SpringAnnotationMethodLevelOperationsScanner<SqsListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        SqsListener.class,
                        sqsBindingFactory,
                        new AsyncHeadersNotDocumented(),
                        operationCustomizers,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        componentsService);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }
}
