// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.AsyncAnnotationChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.AsyncAnnotationOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationScanner;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ConfigurationClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
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
    public AsyncAnnotationChannelsScanner<AsyncListener> asyncListenerAnnotationChannelScanner(
            SpringwolfClassScanner springwolfClassScanner,
            ComponentsService componentsService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadClassExtractor payloadClassExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        return new AsyncAnnotationChannelsScanner<>(
                buildAsyncListenerAnnotationProvider(),
                springwolfClassScanner,
                componentsService,
                asyncApiDocketService,
                payloadClassExtractor,
                operationBindingProcessors,
                messageBindingProcessors);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public AsyncAnnotationOperationsScanner<AsyncListener> asyncListenerAnnotationOperationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            ComponentsService componentsService,
            PayloadClassExtractor payloadClassExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        return new AsyncAnnotationOperationsScanner<>(
                buildAsyncListenerAnnotationProvider(),
                springwolfClassScanner,
                componentsService,
                payloadClassExtractor,
                operationBindingProcessors,
                messageBindingProcessors);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public AsyncAnnotationChannelsScanner<AsyncPublisher> asyncPublisherChannelAnnotationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            ComponentsService componentsService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadClassExtractor payloadClassExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        return new AsyncAnnotationChannelsScanner<>(
                buildAsyncPublisherAnnotationProvider(),
                springwolfClassScanner,
                componentsService,
                asyncApiDocketService,
                payloadClassExtractor,
                operationBindingProcessors,
                messageBindingProcessors);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public AsyncAnnotationOperationsScanner<AsyncPublisher> asyncPublisherOperationAnnotationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            ComponentsService componentsService,
            PayloadClassExtractor payloadClassExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        return new AsyncAnnotationOperationsScanner<>(
                buildAsyncPublisherAnnotationProvider(),
                springwolfClassScanner,
                componentsService,
                payloadClassExtractor,
                operationBindingProcessors,
                messageBindingProcessors);
    }

    private static AsyncAnnotationScanner.AsyncAnnotationProvider<AsyncListener>
            buildAsyncListenerAnnotationProvider() {
        return new AsyncAnnotationScanner.AsyncAnnotationProvider<>() {
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

    private static AsyncAnnotationScanner.AsyncAnnotationProvider<AsyncPublisher>
            buildAsyncPublisherAnnotationProvider() {
        return new AsyncAnnotationScanner.AsyncAnnotationProvider<>() {
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
