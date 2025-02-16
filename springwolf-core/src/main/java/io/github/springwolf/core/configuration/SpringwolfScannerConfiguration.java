// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelPriority;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.AsyncAnnotationClassLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.annotations.AsyncAnnotationMethodLevelChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.channel.AsyncAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.AsyncAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScannerAdapter;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.AsyncAnnotationClassLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.AsyncAnnotationMethodLevelOperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
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
@StandaloneConfiguration
public class SpringwolfScannerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ComponentClassScanner componentClassScanner(
            AsyncApiDocketService asyncApiDocketService, Environment environment) {
        return new ComponentClassScanner(asyncApiDocketService, environment);
    }

    @Bean
    @ConditionalOnMissingBean
    public BeanMethodsScanner beanMethodsScanner(ComponentClassScanner componentClassScanner) {
        return new DefaultBeanMethodsScanner(componentClassScanner);
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
    @ConditionalOnMissingBean(name = "asyncListenerMethodLevelAnnotationChannelScanner")
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public ChannelsScanner asyncListenerMethodLevelAnnotationChannelScanner(
            AsyncAnnotationProvider<AsyncListener> asyncAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            AsyncApiDocketService asyncApiDocketService,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationBindingProcessor> operationBindingProcessors,
            StringValueResolverProxy stringValueResolver) {
        AsyncAnnotationOperationService<AsyncListener> asyncAnnotationOperationService =
                new AsyncAnnotationOperationService<>(
                        asyncAnnotationProvider,
                        operationBindingProcessors,
                        asyncAnnotationMessageService,
                        stringValueResolver);
        val asyncAnnotationChannelService = new AsyncAnnotationChannelService<>(
                asyncAnnotationProvider,
                asyncAnnotationOperationService,
                asyncAnnotationMessageService,
                stringValueResolver,
                asyncApiDocketService);
        val strategy =
                new AsyncAnnotationMethodLevelChannelsScanner<>(asyncAnnotationProvider, asyncAnnotationChannelService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "asyncListenerClassLevelAnnotationChannelScanner")
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public ChannelsScanner asyncListenerClassLevelAnnotationChannelScanner(
            AsyncAnnotationProvider<AsyncListener> asyncAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            AsyncApiDocketService asyncApiDocketService,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationBindingProcessor> operationBindingProcessors,
            StringValueResolverProxy stringValueResolver) {
        AsyncAnnotationOperationService<AsyncListener> asyncAnnotationOperationService =
                new AsyncAnnotationOperationService<>(
                        asyncAnnotationProvider,
                        operationBindingProcessors,
                        asyncAnnotationMessageService,
                        stringValueResolver);
        val asyncAnnotationChannelService = new AsyncAnnotationChannelService<>(
                asyncAnnotationProvider,
                asyncAnnotationOperationService,
                asyncAnnotationMessageService,
                stringValueResolver,
                asyncApiDocketService);
        val strategy =
                new AsyncAnnotationClassLevelChannelsScanner<>(asyncAnnotationProvider, asyncAnnotationChannelService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "asyncListenerMethodLevelAnnotationOperationScanner")
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public OperationsScanner asyncListenerMethodLevelAnnotationOperationScanner(
            AsyncAnnotationProvider<AsyncListener> asyncAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<OperationCustomizer> operationCustomizers,
            StringValueResolverProxy stringValueResolver) {
        val asyncAnnotationOperationService = new AsyncAnnotationOperationService<>(
                asyncAnnotationProvider,
                operationBindingProcessors,
                asyncAnnotationMessageService,
                stringValueResolver);
        val strategy = new AsyncAnnotationMethodLevelOperationsScanner<>(
                asyncAnnotationProvider, asyncAnnotationOperationService, operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "asyncListenerClassLevelListenerAnnotationOperationsScanner")
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public OperationsScanner asyncListenerClassLevelListenerAnnotationOperationsScanner(
            AsyncAnnotationProvider<AsyncListener> asyncAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<OperationCustomizer> operationCustomizers,
            StringValueResolverProxy stringValueResolver) {
        val asyncAnnotationOperationService = new AsyncAnnotationOperationService<>(
                asyncAnnotationProvider,
                operationBindingProcessors,
                asyncAnnotationMessageService,
                stringValueResolver);
        AsyncAnnotationClassLevelOperationsScanner<AsyncListener> strategy =
                new AsyncAnnotationClassLevelOperationsScanner<>(
                        AsyncListener.class,
                        asyncAnnotationProvider,
                        asyncAnnotationOperationService,
                        operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "asyncPublisherClassLevelChannelAnnotationScanner")
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public ChannelsScanner asyncPublisherClassLevelChannelAnnotationScanner(
            AsyncAnnotationProvider<AsyncPublisher> asyncAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            AsyncApiDocketService asyncApiDocketService,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationBindingProcessor> operationBindingProcessors,
            StringValueResolverProxy stringValueResolver) {
        AsyncAnnotationOperationService<AsyncPublisher> asyncAnnotationOperationService =
                new AsyncAnnotationOperationService<>(
                        asyncAnnotationProvider,
                        operationBindingProcessors,
                        asyncAnnotationMessageService,
                        stringValueResolver);
        val asyncAnnotationChannelService = new AsyncAnnotationChannelService<>(
                asyncAnnotationProvider,
                asyncAnnotationOperationService,
                asyncAnnotationMessageService,
                stringValueResolver,
                asyncApiDocketService);
        val strategy =
                new AsyncAnnotationMethodLevelChannelsScanner<>(asyncAnnotationProvider, asyncAnnotationChannelService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "asyncPublisherClassLevelAnnotationChannelScanner")
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public ChannelsScanner asyncPublisherClassLevelAnnotationChannelScanner(
            AsyncAnnotationProvider<AsyncPublisher> asyncAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            AsyncApiDocketService asyncApiDocketService,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationBindingProcessor> operationBindingProcessors,
            StringValueResolverProxy stringValueResolver) {
        AsyncAnnotationOperationService<AsyncPublisher> asyncAnnotationOperationService =
                new AsyncAnnotationOperationService<>(
                        asyncAnnotationProvider,
                        operationBindingProcessors,
                        asyncAnnotationMessageService,
                        stringValueResolver);
        val asyncAnnotationChannelService = new AsyncAnnotationChannelService<>(
                asyncAnnotationProvider,
                asyncAnnotationOperationService,
                asyncAnnotationMessageService,
                stringValueResolver,
                asyncApiDocketService);
        val strategy =
                new AsyncAnnotationClassLevelChannelsScanner<>(asyncAnnotationProvider, asyncAnnotationChannelService);

        return new ChannelsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "asyncPublisherClassLevelOperationAnnotationScanner")
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public OperationsScanner asyncPublisherClassLevelOperationAnnotationScanner(
            AsyncAnnotationProvider<AsyncPublisher> asyncAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<OperationCustomizer> customizers,
            StringValueResolverProxy stringValueResolver) {

        val asyncAnnotationOperationService = new AsyncAnnotationOperationService<>(
                asyncAnnotationProvider,
                operationBindingProcessors,
                asyncAnnotationMessageService,
                stringValueResolver);
        val strategy = new AsyncAnnotationMethodLevelOperationsScanner<>(
                asyncAnnotationProvider, asyncAnnotationOperationService, customizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @ConditionalOnMissingBean(name = "asyncPublisherClassLevelListenerAnnotationOperationsScanner")
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public OperationsScanner asyncPublisherClassLevelListenerAnnotationOperationsScanner(
            AsyncAnnotationProvider<AsyncPublisher> asyncAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            List<OperationBindingProcessor> operationBindingProcessors,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationCustomizer> operationCustomizers,
            StringValueResolverProxy stringValueResolver) {
        val asyncAnnotationOperationService = new AsyncAnnotationOperationService<>(
                asyncAnnotationProvider,
                operationBindingProcessors,
                asyncAnnotationMessageService,
                stringValueResolver);
        AsyncAnnotationClassLevelOperationsScanner<AsyncPublisher> strategy =
                new AsyncAnnotationClassLevelOperationsScanner<>(
                        AsyncPublisher.class,
                        asyncAnnotationProvider,
                        asyncAnnotationOperationService,
                        operationCustomizers);

        return new OperationsInClassScannerAdapter(springwolfClassScanner, strategy);
    }

    @Bean
    @ConditionalOnMissingBean(name = "asyncListenerAnnotationProvider")
    public AsyncAnnotationProvider<AsyncListener> asyncListenerAnnotationProvider() {
        return new AsyncAnnotationProvider<>() {
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

    @Bean
    @ConditionalOnMissingBean(name = "asyncPublisherAnnotationProvider")
    public AsyncAnnotationProvider<AsyncPublisher> asyncPublisherAnnotationProvider() {
        return new AsyncAnnotationProvider<>() {
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
