// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf;

import io.github.stavshamir.springwolf.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SpringPayloadAnnotationTypeExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ConsumerOperationDataScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProducerOperationDataScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListenerAnnotationScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisherAnnotationScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ConfigurationClassScanner;
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
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_SCANNER_CONSUMER_DATA_ENABLED;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_SCANNER_PRODUCER_DATA_ENABLED;

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
    public ConfigurationClassScanner configurationClassScanner(
            AsyncApiDocketService asyncApiDocketService, Environment environment) {
        return new ConfigurationClassScanner(asyncApiDocketService, environment);
    }

    @Bean
    @ConditionalOnMissingBean
    public BeanMethodsScanner beanMethodsScanner(ConfigurationClassScanner configurationClassScanner) {
        return new DefaultBeanMethodsScanner(configurationClassScanner);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_CONSUMER_DATA_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.MANUAL_DEFINED)
    public ConsumerOperationDataScanner consumerOperationDataScanner(
            AsyncApiDocketService asyncApiDocketService, SchemasService schemasService) {
        return new ConsumerOperationDataScanner(asyncApiDocketService, schemasService);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_SCANNER_PRODUCER_DATA_ENABLED, havingValue = "true", matchIfMissing = true)
    @Order(value = ChannelPriority.MANUAL_DEFINED)
    public ProducerOperationDataScanner producerOperationDataScanner(
            AsyncApiDocketService asyncApiDocketService, SchemasService schemasService) {
        return new ProducerOperationDataScanner(asyncApiDocketService, schemasService);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_LISTENER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public AsyncListenerAnnotationScanner asyncListenerAnnotationScanner(
            ComponentClassScanner componentClassScanner,
            SchemasService schemasService,
            AsyncApiDocketService asyncApiDocketService,
            SpringPayloadAnnotationTypeExtractor springPayloadAnnotationTypeExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        return new AsyncListenerAnnotationScanner(
                componentClassScanner,
                schemasService,
                asyncApiDocketService,
                springPayloadAnnotationTypeExtractor,
                operationBindingProcessors,
                messageBindingProcessors);
    }

    @Bean
    @ConditionalOnProperty(
            name = SPRINGWOLF_SCANNER_ASYNC_PUBLISHER_ENABLED,
            havingValue = "true",
            matchIfMissing = true)
    @Order(value = ChannelPriority.ASYNC_ANNOTATION)
    public AsyncPublisherAnnotationScanner asyncPublisherAnnotationScanner(
            ComponentClassScanner componentClassScanner,
            SchemasService schemasService,
            AsyncApiDocketService asyncApiDocketService,
            SpringPayloadAnnotationTypeExtractor springPayloadAnnotationTypeExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        return new AsyncPublisherAnnotationScanner(
                componentClassScanner,
                schemasService,
                asyncApiDocketService,
                springPayloadAnnotationTypeExtractor,
                operationBindingProcessors,
                messageBindingProcessors);
    }
}
