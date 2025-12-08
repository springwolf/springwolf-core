// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.grouping.AsyncApiGroupService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.controller.ActuatorAsyncApiController;
import io.github.springwolf.core.controller.AsyncApiController;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.core.controller.UiConfigController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tools.jackson.databind.json.JsonMapper;

import static io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_ENDPOINT_ACTUATOR_ENABLED;

/**
 * Spring-Configuration defining the web controller beans.
 */
@Configuration(proxyBeanMethods = false)
@Import(SpringwolfUiResourceConfiguration.class)
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
            ComponentsService componentsService, JsonMapper jsonMapper) {
        return new PublishingPayloadCreator(componentsService, jsonMapper);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_ENDPOINT_ACTUATOR_ENABLED, havingValue = "true")
    @ConditionalOnMissingBean
    public ActuatorAsyncApiController actuatorAsyncApiController(
            AsyncApiService asyncApiService, AsyncApiSerializerService asyncApiSerializerService) {
        return new ActuatorAsyncApiController(asyncApiService, asyncApiSerializerService);
    }

    @Bean
    @ConditionalOnProperty(name = SPRINGWOLF_ENDPOINT_ACTUATOR_ENABLED, havingValue = "false", matchIfMissing = true)
    @ConditionalOnMissingBean
    public UiConfigController uiConfigController(
            AsyncApiGroupService asyncApiGroupService, SpringwolfConfigProperties configProperties) {
        return new UiConfigController(asyncApiGroupService, configProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncApiSerializerService asyncApiSerializerService() {
        return new DefaultAsyncApiSerializerService();
    }
}
