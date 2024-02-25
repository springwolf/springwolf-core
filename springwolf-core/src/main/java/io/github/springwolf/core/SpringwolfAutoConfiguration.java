// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core;

import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.asyncapi.ChannelsService;
import io.github.springwolf.core.asyncapi.DefaultAsyncApiService;
import io.github.springwolf.core.asyncapi.DefaultChannelsService;
import io.github.springwolf.core.asyncapi.DefaultOperationsService;
import io.github.springwolf.core.asyncapi.OperationsService;
import io.github.springwolf.core.asyncapi.SpringwolfInitApplicationListener;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.springwolf.core.configuration.AsyncApiDocketService;
import io.github.springwolf.core.configuration.DefaultAsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.schemas.ComponentsService;
import io.github.springwolf.core.schemas.DefaultComponentsService;
import io.github.springwolf.core.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.schemas.example.DefaultExampleXmlValueSerializer;
import io.github.springwolf.core.schemas.example.DefaultExampleYamlValueSerializer;
import io.github.springwolf.core.schemas.example.DefaultSchemaWalker;
import io.github.springwolf.core.schemas.example.ExampleJsonValueGenerator;
import io.github.springwolf.core.schemas.example.ExampleXmlValueGenerator;
import io.github.springwolf.core.schemas.example.ExampleXmlValueSerializer;
import io.github.springwolf.core.schemas.example.ExampleYamlValueGenerator;
import io.github.springwolf.core.schemas.example.ExampleYamlValueSerializer;
import io.github.springwolf.core.schemas.example.SchemaWalker;
import io.github.springwolf.core.schemas.example.SchemaWalkerProvider;
import io.github.springwolf.core.schemas.postprocessor.AvroSchemaPostProcessor;
import io.github.springwolf.core.schemas.postprocessor.ExampleGeneratorPostProcessor;
import io.github.springwolf.core.schemas.postprocessor.SchemasPostProcessor;
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
    public ExampleGeneratorPostProcessor exampleGeneratorPostProcessor(SchemaWalkerProvider schemaWalkerProvider) {
        return new ExampleGeneratorPostProcessor(schemaWalkerProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public SchemaWalkerProvider exampleGeneratorProvider(List<SchemaWalker> schemaWalkers) {
        return new SchemaWalkerProvider(schemaWalkers);
    }

    @Bean
    public SchemaWalker jsonSchemaWalker() {
        return new DefaultSchemaWalker<>(new ExampleJsonValueGenerator());
    }

    @Bean
    @ConditionalOnMissingBean
    public ExampleXmlValueSerializer defaultExampleXmlValueSerializer() {
        return new DefaultExampleXmlValueSerializer();
    }

    @Bean
    public SchemaWalker xmlSchemaWalker(ExampleXmlValueSerializer exampleXmlValueSerializer) {
        return new DefaultSchemaWalker<>(new ExampleXmlValueGenerator(exampleXmlValueSerializer));
    }

    @Bean
    @ConditionalOnMissingBean
    public ExampleYamlValueSerializer defaultExampleYamlValueSerializer() {
        return new DefaultExampleYamlValueSerializer();
    }

    @Bean
    public SchemaWalker yamlSchemaWalker(ExampleYamlValueSerializer exampleYamlValueSerializer) {
        return new DefaultSchemaWalker<>(
                new ExampleYamlValueGenerator(new ExampleJsonValueGenerator(), exampleYamlValueSerializer));
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadClassExtractor payloadClassExtractor(SpringwolfConfigProperties springwolfConfigProperties) {
        return new PayloadClassExtractor(springwolfConfigProperties);
    }
}
