// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiCustomizer;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.ChannelsService;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.DefaultChannelsService;
import io.github.stavshamir.springwolf.asyncapi.DefaultOperationsService;
import io.github.stavshamir.springwolf.asyncapi.OperationsService;
import io.github.stavshamir.springwolf.asyncapi.SpringwolfInitApplicationListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.OperationsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.ComponentsService;
import io.github.stavshamir.springwolf.schemas.DefaultComponentsService;
import io.github.stavshamir.springwolf.schemas.SwaggerSchemaUtil;
import io.github.stavshamir.springwolf.schemas.example.DefaultSchemaWalker;
import io.github.stavshamir.springwolf.schemas.example.ExampleGeneratorProvider;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonValueGenerator;
import io.github.stavshamir.springwolf.schemas.example.SchemaWalker;
import io.github.stavshamir.springwolf.schemas.postprocessor.AvroSchemaPostProcessor;
import io.github.stavshamir.springwolf.schemas.postprocessor.ExampleGeneratorPostProcessor;
import io.github.stavshamir.springwolf.schemas.postprocessor.SchemasPostProcessor;
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
            OperationsService operationsService,
            ComponentsService componentsService,
            List<AsyncApiCustomizer> customizers) {
        return new DefaultAsyncApiService(
                asyncApiDocketService, channelsService, operationsService, componentsService, customizers);
    }

    @Bean
    @ConditionalOnMissingBean
    public ChannelsService channelsService(List<? extends ChannelsScanner> channelsScanners) {
        return new DefaultChannelsService(channelsScanners);
    }

    @Bean
    @ConditionalOnMissingBean
    public OperationsService operationsService(List<? extends OperationsScanner> operationsScanners) {
        return new DefaultOperationsService(operationsScanners);
    }

    @Bean
    @ConditionalOnMissingBean
    public SwaggerSchemaUtil swaggerSchemaUtil() {
        return new SwaggerSchemaUtil();
    }

    @Bean
    @ConditionalOnMissingBean
    public ComponentsService schemasService(
            List<ModelConverter> modelConverters,
            List<SchemasPostProcessor> schemaPostProcessors,
            SwaggerSchemaUtil swaggerSchemaUtil,
            SpringwolfConfigProperties springwolfConfigProperties) {
        return new DefaultComponentsService(
                modelConverters, schemaPostProcessors, swaggerSchemaUtil, springwolfConfigProperties);
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
    public ExampleGeneratorPostProcessor exampleGeneratorPostProcessor(SchemaWalker schemaWalker) {
        return new ExampleGeneratorPostProcessor(schemaWalker);
    }

    @Bean
    @ConditionalOnMissingBean
    @Order(100)
    public SwaggerSchemaPostProcessor swaggerSchemaPostProcessor() {
        return new SwaggerSchemaPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public ExampleGeneratorProvider exampleGeneratorProvider(List<SchemaWalker> schemaWalkers) {
        return new ExampleGeneratorProvider(schemaWalkers);
    }

    @Bean
    @ConditionalOnMissingBean
    public SchemaWalker exampleGenerator() {
        return new DefaultSchemaWalker<>(new ExampleJsonValueGenerator());
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadClassExtractor payloadClassExtractor(SpringwolfConfigProperties springwolfConfigProperties) {
        return new PayloadClassExtractor(springwolfConfigProperties);
    }
}
