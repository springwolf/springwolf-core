// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

import io.github.springwolf.core.SpringwolfInitApplicationListener;
import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.asyncapi.DefaultAsyncApiService;
import io.github.springwolf.core.asyncapi.channels.ChannelsService;
import io.github.springwolf.core.asyncapi.channels.DefaultChannelsService;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.DefaultComponentsService;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.DefaultSchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.SchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.asyncapi.components.examples.walkers.xml.DefaultExampleXmlValueSerializer;
import io.github.springwolf.core.asyncapi.components.examples.walkers.xml.ExampleXmlValueGenerator;
import io.github.springwolf.core.asyncapi.components.examples.walkers.xml.ExampleXmlValueSerializer;
import io.github.springwolf.core.asyncapi.components.examples.walkers.yaml.DefaultExampleYamlValueSerializer;
import io.github.springwolf.core.asyncapi.components.examples.walkers.yaml.ExampleYamlValueGenerator;
import io.github.springwolf.core.asyncapi.components.examples.walkers.yaml.ExampleYamlValueSerializer;
import io.github.springwolf.core.asyncapi.components.postprocessors.AvroSchemaPostProcessor;
import io.github.springwolf.core.asyncapi.components.postprocessors.ExampleGeneratorPostProcessor;
import io.github.springwolf.core.asyncapi.components.postprocessors.SchemasPostProcessor;
import io.github.springwolf.core.asyncapi.operations.DefaultOperationsService;
import io.github.springwolf.core.asyncapi.operations.OperationsService;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodReturnService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.TypeToClassConverter;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.asyncapi.schemas.converters.SchemaTitleModelConverter;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.configuration.docket.DefaultAsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
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
            AsyncApiService asyncApiService, SpringwolfConfigProperties springwolfConfigProperties) {
        return new SpringwolfInitApplicationListener(asyncApiService, springwolfConfigProperties);
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
    public ComponentsService componentsService(SwaggerSchemaService schemaService) {
        return new DefaultComponentsService(schemaService);
    }

    @Bean
    @ConditionalOnMissingBean
    public SwaggerSchemaService schemasService(
            List<ModelConverter> modelConverters,
            List<SchemasPostProcessor> schemaPostProcessors,
            SwaggerSchemaUtil swaggerSchemaUtil,
            SpringwolfConfigProperties springwolfConfigProperties) {
        return new SwaggerSchemaService(
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
    public SchemaTitleModelConverter schemaTitleModelConverter() {
        return new SchemaTitleModelConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public SchemaWalkerProvider schemaWalkerProvider(List<SchemaWalker> schemaWalkers) {
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
    public SchemaWalker yamlSchemaWalker(
            ExampleYamlValueSerializer exampleYamlValueSerializer,
            SpringwolfConfigProperties springwolfConfigProperties) {
        return new DefaultSchemaWalker<>(new ExampleYamlValueGenerator(
                new ExampleJsonValueGenerator(), exampleYamlValueSerializer, springwolfConfigProperties));
    }

    @Bean
    @ConditionalOnMissingBean
    public TypeToClassConverter typeToClassConverter(SpringwolfConfigProperties springwolfConfigProperties) {
        return new TypeToClassConverter(springwolfConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadClassExtractor payloadClassExtractor() {
        return new PayloadClassExtractor();
    }

    @Bean
    @ConditionalOnMissingBean
    public HeaderClassExtractor headerClassExtractor(SwaggerSchemaService schemaService) {
        return new HeaderClassExtractor(schemaService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadService payloadService(
            ComponentsService componentsService, SpringwolfConfigProperties springwolfConfigProperties) {
        return new PayloadService(componentsService, springwolfConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadAsyncOperationService payloadAsyncOperationService(
            PayloadClassExtractor payloadClassExtractor, PayloadService payloadService) {
        return new PayloadAsyncOperationService(payloadClassExtractor, payloadService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadMethodParameterService payloadMethodParameterService(
            PayloadClassExtractor payloadClassExtractor, PayloadService payloadService) {
        return new PayloadMethodParameterService(payloadClassExtractor, payloadService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadMethodReturnService payloadMethodReturnService(PayloadService payloadService) {
        return new PayloadMethodReturnService(payloadService);
    }
}
