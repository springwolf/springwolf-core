// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf;

import io.github.stavshamir.springwolf.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.AsyncAnnotationChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ConfigurationClassScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.util.List;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED;

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
    public AsyncAnnotationChannelsScanner<AsyncListener> asyncListenerAnnotationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            SchemasService schemasService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadClassExtractor payloadClassExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        return new AsyncAnnotationChannelsScanner<>(
                buildAsyncListenerAnnotationProvider(),
                springwolfClassScanner,
                schemasService,
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
    public AsyncAnnotationChannelsScanner<AsyncPublisher> asyncPublisherAnnotationScanner(
            SpringwolfClassScanner springwolfClassScanner,
            SchemasService schemasService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadClassExtractor payloadClassExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        return new AsyncAnnotationChannelsScanner<>(
                buildAsyncPublisherAnnotationProvider(),
                springwolfClassScanner,
                schemasService,
                asyncApiDocketService,
                payloadClassExtractor,
                operationBindingProcessors,
                messageBindingProcessors);
    }

    private static AsyncAnnotationChannelsScanner.AsyncAnnotationProvider<AsyncListener>
            buildAsyncListenerAnnotationProvider() {
        return new AsyncAnnotationChannelsScanner.AsyncAnnotationProvider<>() {
            @Override
            public Class<AsyncListener> getAnnotation() {
                return AsyncListener.class;
            }

            @Override
            public AsyncOperation getAsyncOperation(AsyncListener annotation) {
                return annotation.operation();
            }

            @Override
            public OperationData.OperationType getOperationType() {
                return OperationData.OperationType.PUBLISH;
            }
        };
    }

    private static AsyncAnnotationChannelsScanner.AsyncAnnotationProvider<AsyncPublisher>
            buildAsyncPublisherAnnotationProvider() {
        return new AsyncAnnotationChannelsScanner.AsyncAnnotationProvider<>() {
            @Override
            public Class<AsyncPublisher> getAnnotation() {
                return AsyncPublisher.class;
            }

            @Override
            public AsyncOperation getAsyncOperation(AsyncPublisher annotation) {
                return annotation.operation();
            }

            @Override
            public OperationData.OperationType getOperationType() {
                return OperationData.OperationType.SUBSCRIBE;
            }
        };
    }
}
