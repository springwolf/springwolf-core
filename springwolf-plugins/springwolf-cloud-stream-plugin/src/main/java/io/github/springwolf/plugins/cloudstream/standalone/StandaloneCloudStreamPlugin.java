// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.standalone;

import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.standalone.plugin.StandalonePlugin;
import io.github.springwolf.core.standalone.plugin.StandalonePluginContext;
import io.github.springwolf.core.standalone.plugin.StandalonePluginResult;
import io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanBuilder;
import io.github.springwolf.plugins.cloudstream.configuration.SpringwolfCloudStreamConfiguration;
import org.springframework.cloud.stream.config.BindingServiceProperties;

import java.util.Collections;

public class StandaloneCloudStreamPlugin implements StandalonePlugin {

    @Override
    public StandalonePluginResult load(StandalonePluginContext context) {
        SpringwolfCloudStreamConfiguration cloudStreamAutoConfiguration = new SpringwolfCloudStreamConfiguration();

        FunctionalChannelBeanBuilder functionalChannelBeanBuilder =
                cloudStreamAutoConfiguration.functionalChannelBeanBuilder(context.getTypeExtractor());

        ChannelsScanner cloudStreamFunctionChannelScanner =
                cloudStreamAutoConfiguration.cloudStreamFunctionChannelsScanner(
                        context.getAsyncApiDocketService(),
                        context.getBeanMethodsScanner(),
                        context.getComponentClassScanner(),
                        context.getComponentsService(),
                        context.getPayloadService(),
                        new BindingServiceProperties(), // TODO where to get
                        // org.springframework.cloud.stream.config.BindingServiceProperties from?
                        functionalChannelBeanBuilder,
                        Collections.emptyList(),
                        Collections.emptyList());
        OperationsScanner cloudStreamFunctionOperationScanner =
                cloudStreamAutoConfiguration.cloudStreamFunctionOperationsScanner(
                        context.getAsyncApiDocketService(),
                        context.getBeanMethodsScanner(),
                        context.getComponentClassScanner(),
                        context.getComponentsService(),
                        context.getPayloadService(),
                        new BindingServiceProperties(), // TODO where to get
                        // org.springframework.cloud.stream.config.BindingServiceProperties from?
                        functionalChannelBeanBuilder);

        return StandalonePluginResult.builder()
                .channelsScanner(cloudStreamFunctionChannelScanner)
                .operationsScanner(cloudStreamFunctionOperationScanner)
                .build();
    }
}
