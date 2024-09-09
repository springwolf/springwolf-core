// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.AsyncAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ConfigurationClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationMethodLevelScanner;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.AsyncAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.util.List;

import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED;
import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED;

/**
 * Spring configuration defining the core scanner beans.
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfScannerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ComponentClassScanner componentClassScanner(
            AsyncApiDocketService asyncApiDocketService, Environment environment) {
        return new ComponentClassScanner(asyncApiDocketService, environment);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConfigurationClassScanner configurationClassScanner(ComponentClassScanner componentClassScanner) {
        return new ConfigurationClassScanner(componentClassScanner);
    }

    @Bean
    @ConditionalOnMissingBean
    public BeanMethodsScanner beanMethodsScanner(ConfigurationClassScanner configurationClassScanner) {
        return new DefaultBeanMethodsScanner(configurationClassScanner);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfClassScanner springwolfClassScanner(
            ComponentClassScanner componentClassScanner, BeanMethodsScanner beanMethodsScanner) {
        return new SpringwolfClassScanner(componentClassScanner, beanMethodsScanner);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public ChannelsScanner asyncListenerAnnotationChannelScanner(
            SpringwolfClassScanner springwolfClassScanner,
            ComponentsService componentsService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadAsyncOperationService payloadService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors,
            StringValueResolverProxy resolver) {
        val strategy = new AsyncAnnotationMethodLevelChannelsScanner<>(
                buildAsyncListenerAnnotationProvider(),
                componentsService,
                asyncApiDocketService,
                payloadService,
                operationBindingProcessors,
                messageBindingProcessors,
                resolver);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public OperationsScanner asyncListenerAnnotationOperationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            ComponentsService componentsService,
            PayloadAsyncOperationService payloadService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors,
            List<OperationCustomizer> operationCustomizers,
            StringValueResolverProxy resolver) {
        val strategy = new AsyncAnnotationMethodLevelOperationsScanner<>(
                buildAsyncListenerAnnotationProvider(),
                componentsService,
                payloadService,
                operationBindingProcessors,
                messageBindingProcessors,
                operationCustomizers,
                resolver);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public ChannelsScanner asyncPublisherChannelAnnotationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            ComponentsService componentsService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadAsyncOperationService payloadService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors,
            StringValueResolverProxy resolver) {
        val strategy = new AsyncAnnotationMethodLevelChannelsScanner<>(
                buildAsyncPublisherAnnotationProvider(),
                componentsService,
                asyncApiDocketService,
                payloadService,
                operationBindingProcessors,
                messageBindingProcessors,
                resolver);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public OperationsScanner asyncPublisherOperationAnnotationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            ComponentsService componentsService,
            PayloadAsyncOperationService payloadService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors,
            List<OperationCustomizer> customizers,
            StringValueResolverProxy resolver) {
        val strategy = new AsyncAnnotationMethodLevelOperationsScanner<>(
                buildAsyncPublisherAnnotationProvider(),
                componentsService,
                payloadService,
                operationBindingProcessors,
                messageBindingProcessors,
                customizers,
                resolver);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnMissingBean
    public StringValueResolverProxy stringValueResolverProxy() {
        return new StringValueResolverProxy();
    }

    private static AsyncAnnotationMethodLevelScanner.AsyncAnnotationProvider<AsyncListener>
            buildAsyncListenerAnnotationProvider() {
        return new AsyncAnnotationMethodLevelScanner.AsyncAnnotationProvider<>() {
            @Override
            public Class<AsyncListener> getAnnotation() {
                return AsyncListener.class;
            }

            @Override
            public AsyncOperation getAsyncOperation(AsyncListener annotation) {
                return annotation.operation();
            }

            @Override
            public OperationAction getOperationType() {
                return OperationAction.RECEIVE;
            }
        };
    }

    private static AsyncAnnotationMethodLevelScanner.AsyncAnnotationProvider<AsyncPublisher>
            buildAsyncPublisherAnnotationProvider() {
        return new AsyncAnnotationMethodLevelScanner.AsyncAnnotationProvider<>() {
            @Override
            public Class<AsyncPublisher> getAnnotation() {
                return AsyncPublisher.class;
            }

            @Override
            public AsyncOperation getAsyncOperation(AsyncPublisher annotation) {
                return annotation.operation();
            }

            @Override
            public OperationAction getOperationType() {
                return OperationAction.SEND;
            }
        };
    }
}
