// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiSerializerService;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiSerializerService;
import io.github.stavshamir.springwolf.asyncapi.controller.ActuatorAsyncApiController;
import io.github.stavshamir.springwolf.asyncapi.controller.AsyncApiController;
import io.github.stavshamir.springwolf.asyncapi.controller.PublishingPayloadCreator;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_ENDPOINT_ACTUATOR_ENABLED;

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
    @ConditionalOnProperty(name = SPRINGWOLF_ENDPOINT_ACTUATOR_ENABLED, havingValue = "false", matchIfMissing = true)
    @ConditionalOnMissingBean
    public PublishingPayloadCreator publishingPayloadCreator(SchemasService schemasService, ObjectMapper objectMapper) {
        return new PublishingPayloadCreator(schemasService, objectMapper);
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
        return new DefaultAsyncApiSerializerService();
    }
}
