// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiCustomizer;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.ChannelsService;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.DefaultChannelsService;
import io.github.stavshamir.springwolf.asyncapi.SpringwolfInitApplicationListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleGenerator;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import io.github.stavshamir.springwolf.schemas.postprocessor.AvroSchemaPostProcessor;
import io.github.stavshamir.springwolf.schemas.postprocessor.ExampleGeneratorPostProcessor;
import io.github.stavshamir.springwolf.schemas.postprocessor.SchemasPostProcessor;
import io.github.stavshamir.springwolf.schemas.postprocessor.SwaggerSchemaPostProcessor;
import io.swagger.v3.core.converter.ModelConverter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * Spring Boot auto-configuration which loads all spring-beans for springwolf core module.
 * <p>
 * To disable springwolf support, set the environment property {@code springwolf.enabled=false}.
 */
@AutoConfiguration
@Import({SpringwolfWebConfiguration.class, SpringwolfScannerConfiguration.class})
@ConditionalOnProperty(name = SpringwolfConfigConstants.SPRINGWOLF_ENABLED, havingValue = "true", matchIfMissing = true)
public class SpringwolfAutoConfiguration {

    @Bean
    public SpringwolfConfigProperties springwolfConfigProperties() {
        return new SpringwolfConfigProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfInitApplicationListener springwolfInitApplicationListener(
            AsyncApiService asyncApiService, SpringwolfConfigProperties configProperties) {
        return new SpringwolfInitApplicationListener(asyncApiService, configProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncApiService asyncApiService(
            AsyncApiDocketService asyncApiDocketService,
            ChannelsService channelsService,
            SchemasService schemasService,
            List<AsyncApiCustomizer> customizers) {
        return new DefaultAsyncApiService(asyncApiDocketService, channelsService, schemasService, customizers);
    }

    @Bean
    @ConditionalOnMissingBean
    public ChannelsService channelsService(List<? extends ChannelsScanner> channelsScanners) {
        return new DefaultChannelsService(channelsScanners);
    }

    @Bean
    @ConditionalOnMissingBean
    public SchemasService schemasService(
            List<ModelConverter> modelConverters,
            List<SchemasPostProcessor> schemaPostProcessors,
            SpringwolfConfigProperties springwolfConfigProperties) {
        return new DefaultSchemasService(modelConverters, schemaPostProcessors, springwolfConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncApiDocketService asyncApiDocketService(SpringwolfConfigProperties springwolfConfigProperties) {
        return new DefaultAsyncApiDocketService(springwolfConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @Order(0)
    public AvroSchemaPostProcessor avroSchemaPostProcessor() {
        return new AvroSchemaPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    @Order(10)
    public ExampleGeneratorPostProcessor exampleGeneratorPostProcessor(ExampleGenerator exampleGenerator) {
        return new ExampleGeneratorPostProcessor(exampleGenerator);
    }

    @Bean
    @ConditionalOnMissingBean
    @Order(100)
    public SwaggerSchemaPostProcessor swaggerSchemaPostProcessor() {
        return new SwaggerSchemaPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public ExampleGenerator exampleGenerator() {
        return new ExampleJsonGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadClassExtractor payloadClassExtractor(SpringwolfConfigProperties springwolfConfigProperties) {
        return new PayloadClassExtractor(springwolfConfigProperties);
    }
}
