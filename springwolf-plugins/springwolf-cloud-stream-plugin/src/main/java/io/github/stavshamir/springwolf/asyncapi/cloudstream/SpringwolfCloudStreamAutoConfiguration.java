// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.cloudstream;

import io.github.stavshamir.springwolf.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream.CloudStreamFunctionChannelsScanner;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Bean;

/**
 * Autoconfiguration for the springwolf cloudstream plugin.
 */
@AutoConfiguration
public class SpringwolfCloudStreamAutoConfiguration {

    @Bean
    public CloudStreamFunctionChannelsScanner cloudStreamFunctionChannelsScanner(
            AsyncApiDocketService asyncApiDocketService,
            BeanMethodsScanner beanMethodsScanner,
            SchemasService schemasService,
            BindingServiceProperties cloudstreamBindingServiceProperties) {
        return new CloudStreamFunctionChannelsScanner(
                asyncApiDocketService, beanMethodsScanner, schemasService, cloudstreamBindingServiceProperties);
    }
}
