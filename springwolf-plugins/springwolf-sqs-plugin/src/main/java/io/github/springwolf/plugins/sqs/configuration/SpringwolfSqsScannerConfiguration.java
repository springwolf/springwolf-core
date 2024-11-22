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
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersNotDocumented;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.SpringAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.plugins.sqs.asyncapi.scanners.bindings.SqsBindingFactory;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringValueResolver;

import java.util.List;

import static io.github.springwolf.plugins.sqs.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED;

/**
 * spring configuration defining the scanner beans for the kafka plugin
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfSqsScannerConfiguration {

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED, havingValue = "true", matchIfMissing = true)
    public SqsBindingFactory sqsBindingFactory(StringValueResolver stringValueResolver) {
        return new SqsBindingFactory(stringValueResolver);
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
        val springAnnotationChannelService = new SpringAnnotationChannelService<>(sqsBindingFactory);
        val springAnnotationMessageService = new SpringAnnotationMessageService<>(
                sqsBindingFactory, new AsyncHeadersNotDocumented(), componentsService);
        SpringAnnotationMethodLevelChannelsScanner<SqsListener> strategy =
                new SpringAnnotationMethodLevelChannelsScanner<>(
                        SqsListener.class,
                        payloadMethodParameterService,
                        headerClassExtractor,
                        springAnnotationChannelService,
                        springAnnotationMessageService);
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
        val springAnnotationOperationService = new SpringAnnotationOperationService<>(
                sqsBindingFactory,
                new SpringAnnotationMessageService<>(
                        sqsBindingFactory, new AsyncHeadersNotDocumented(), componentsService));
        SpringAnnotationMethodLevelOperationsScanner<SqsListener> strategy =
                new SpringAnnotationMethodLevelOperationsScanner<>(
                        SqsListener.class,
                        headerClassExtractor,
                        payloadMethodParameterService,
                        springAnnotationOperationService,
                        operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }
}
