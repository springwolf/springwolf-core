// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests.application.configuration;

import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import org.springframework.context.annotation.Bean;

@StandaloneConfiguration
public class CustomizerMarkerConfiguration {

    public static boolean hasCustomized = false;

    @Bean
    public AsyncApiCustomizer customizer() {
        return asyncApi -> hasCustomized = true;
    }
}
