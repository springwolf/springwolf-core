// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.controller.ActuatorAsyncApiController;
import io.github.springwolf.core.controller.AsyncApiController;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_ENDPOINT_ACTUATOR_ENABLED;

/**
 * Spring-Configuration defining the web controller beans.
 */
@Configuration(proxyBeanMethods = false)
public class SpringwolfWebConfiguration {

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_ENDPOINT_ACTUATOR_ENABLED, havingValue = "false", matchIfMissing = true)
    @ConditionalOnMissingBean
    public AsyncApiController asyncApiController(
            AsyncApiService asyncApiService, AsyncApiSerializerService asyncApiSerializerService) {
        return new AsyncApiController(asyncApiService, asyncApiSerializerService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PublishingPayloadCreator publishingPayloadCreator(
            ComponentsService componentsService, ObjectMapper objectMapper) {
        return new PublishingPayloadCreator(componentsService, objectMapper);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_ENDPOINT_ACTUATOR_ENABLED, havingValue = "true")
    @ConditionalOnMissingBean
    public ActuatorAsyncApiController actuatorAsyncApiController(
            AsyncApiService asyncApiService, AsyncApiSerializerService asyncApiSerializerService) {
        return new ActuatorAsyncApiController(asyncApiService, asyncApiSerializerService);
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncApiSerializerService asyncApiSerializerService() {
        return new DefaultAsyncApiSerializer();
    }
}
