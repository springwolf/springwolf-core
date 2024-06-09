// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.TypeToClassConverter;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.channels.CloudStreamFunctionChannelsScanner;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanBuilder;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.operations.CloudStreamFunctionOperationsScanner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Autoconfiguration for the springwolf cloudstream plugin.
 */
@AutoConfiguration
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfCloudStreamAutoConfiguration {

    @Bean
    public CloudStreamFunctionChannelsScanner cloudStreamFunctionChannelsScanner(
            AsyncApiDocketService asyncApiDocketService,
            BeanMethodsScanner beanMethodsScanner,
            ComponentClassScanner componentClassScanner,
            ComponentsService componentsService,
            PayloadService payloadService,
            BindingServiceProperties cloudstreamBindingServiceProperties,
            FunctionalChannelBeanBuilder functionalChannelBeanBuilder,
            List<ChannelBindingProcessor> channelBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        return new CloudStreamFunctionChannelsScanner(
                asyncApiDocketService,
                beanMethodsScanner,
                componentClassScanner,
                componentsService,
                payloadService,
                cloudstreamBindingServiceProperties,
                functionalChannelBeanBuilder,
                channelBindingProcessors,
                messageBindingProcessors);
    }

    @Bean
    public CloudStreamFunctionOperationsScanner cloudStreamFunctionOperationsScanner(
            AsyncApiDocketService asyncApiDocketService,
            BeanMethodsScanner beanMethodsScanner,
            ComponentClassScanner componentClassScanner,
            ComponentsService componentsService,
            PayloadService payloadService,
            BindingServiceProperties cloudstreamBindingServiceProperties,
            FunctionalChannelBeanBuilder functionalChannelBeanBuilder) {
        return new CloudStreamFunctionOperationsScanner(
                asyncApiDocketService,
                beanMethodsScanner,
                componentClassScanner,
                componentsService,
                payloadService,
                cloudstreamBindingServiceProperties,
                functionalChannelBeanBuilder);
    }

    @Bean
    public FunctionalChannelBeanBuilder functionalChannelBeanBuilder(TypeToClassConverter typeToClassConverter) {
        return new FunctionalChannelBeanBuilder(typeToClassConverter);
    }
}
