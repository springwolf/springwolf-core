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
import io.github.springwolf.core.asyncapi.messages.DefaultMessagesService;
import io.github.springwolf.core.asyncapi.messages.MessagesService;
import io.github.springwolf.core.asyncapi.operations.DefaultOperationsService;
import io.github.springwolf.core.asyncapi.operations.OperationsService;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodReturnService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.TypeExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import io.github.springwolf.core.asyncapi.schemas.DefaultSchemasService;
import io.github.springwolf.core.asyncapi.schemas.SchemasService;
import io.github.springwolf.core.asyncapi.schemas.converters.ModelConvertersRegisterer;
import io.github.springwolf.core.asyncapi.schemas.converters.SchemaTitleModelConverter;
import io.github.springwolf.core.asyncapi.schemas.schema.SwaggerSchemaMapper;
import io.github.springwolf.core.asyncapi.schemas.schema.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers.DefaultSchemaWalker;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers.SchemaWalker;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers.xml.DefaultExampleXmlValueSerializer;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers.xml.ExampleXmlValueGenerator;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers.xml.ExampleXmlValueSerializer;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers.yaml.DefaultExampleYamlValueSerializer;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers.yaml.ExampleYamlValueGenerator;
import io.github.springwolf.core.asyncapi.schemas.schema.examples.walkers.yaml.ExampleYamlValueSerializer;
import io.github.springwolf.core.asyncapi.schemas.schema.postprocessors.AvroSchemaPostProcessor;
import io.github.springwolf.core.asyncapi.schemas.schema.postprocessors.ExampleGeneratorPostProcessor;
import io.github.springwolf.core.asyncapi.schemas.schema.postprocessors.SchemasPostProcessor;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.configuration.docket.DefaultAsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.grouping.AsyncApiGroupService;
import io.github.springwolf.core.grouping.GroupingService;
import io.github.springwolf.core.standalone.StandaloneConfiguration;
import io.swagger.v3.core.converter.ModelConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import tools.jackson.databind.JacksonModule;

import java.util.List;

@Configuration(proxyBeanMethods = false)
@StandaloneConfiguration
public class SpringwolfCoreConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SpringwolfInitApplicationListener springwolfInitApplicationListener(
            AsyncApiService asyncApiService, SpringwolfConfigProperties springwolfConfigProperties) {
        return new SpringwolfInitApplicationListener(asyncApiService, springwolfConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public GroupingService groupingService() {
        return new GroupingService();
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncApiGroupService asyncApiGroupService(
            SpringwolfConfigProperties springwolfConfigProperties, GroupingService groupingService) {
        return new AsyncApiGroupService(springwolfConfigProperties, groupingService);
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncApiService asyncApiService(
            AsyncApiDocketService asyncApiDocketService,
            ChannelsService channelsService,
            OperationsService operationsService,
            ComponentsService componentsService,
            List<AsyncApiCustomizer> customizers,
            AsyncApiGroupService groupService) {
        return new DefaultAsyncApiService(
                asyncApiDocketService,
                channelsService,
                operationsService,
                componentsService,
                customizers,
                groupService);
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
    public SwaggerSchemaMapper swaggerSchemaUtil(SpringwolfConfigProperties springwolfConfigProperties) {
        return new SwaggerSchemaMapper(springwolfConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public ComponentsService componentsService(SchemasService schemasService, MessagesService messagesService) {
        return new DefaultComponentsService(schemasService, messagesService);
    }

    @Bean
    @ConditionalOnMissingBean
    public MessagesService messageService() {
        return new DefaultMessagesService();
    }

    @Bean
    @ConditionalOnMissingBean
    public SchemasService defaultSchemasService(SwaggerSchemaService schemaService) {
        return new DefaultSchemasService(schemaService);
    }

    @Bean
    @ConditionalOnMissingBean
    public SwaggerSchemaService schemasService(
            SpringwolfConfigProperties springwolfConfigProperties,
            List<SchemasPostProcessor> schemaPostProcessors,
            SwaggerSchemaMapper swaggerSchemaMapper,
            ModelConvertersRegisterer modelConvertersRegisterer) {
        return new SwaggerSchemaService(
                springwolfConfigProperties, schemaPostProcessors, swaggerSchemaMapper, modelConvertersRegisterer);
    }

    @Bean
    @ConditionalOnMissingBean
    public ModelConvertersRegisterer modelConvertersProvider(
            SpringwolfConfigProperties springwolfConfigProperties,
            List<ModelConverter> modelConverters,
            List<JacksonModule> jacksonModules) {
        return new ModelConvertersRegisterer(springwolfConfigProperties, modelConverters, jacksonModules);
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncApiDocketService asyncApiDocketService(
            SpringwolfConfigProperties springwolfConfigProperties, ApplicationContext applicationContext) {
        return new DefaultAsyncApiDocketService(springwolfConfigProperties, applicationContext);
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
    public SchemaWalkerProvider schemaWalkerProvider(List<SchemaWalker<?>> schemaWalkers) {
        return new SchemaWalkerProvider(schemaWalkers);
    }

    @Bean
    @ConditionalOnMissingBean(name = "jsonSchemaWalker")
    public SchemaWalker<?> jsonSchemaWalker() {
        return new DefaultSchemaWalker<>(new ExampleJsonValueGenerator());
    }

    @Bean
    @ConditionalOnMissingBean
    public ExampleXmlValueSerializer exampleXmlValueSerializer() {
        return new DefaultExampleXmlValueSerializer();
    }

    @Bean
    @ConditionalOnMissingBean(name = "xmlSchemaWalker")
    public SchemaWalker<?> xmlSchemaWalker(ExampleXmlValueSerializer exampleXmlValueSerializer) {
        return new DefaultSchemaWalker<>(new ExampleXmlValueGenerator(exampleXmlValueSerializer));
    }

    @Bean
    @ConditionalOnMissingBean
    public ExampleYamlValueSerializer exampleYamlValueSerializer() {
        return new DefaultExampleYamlValueSerializer();
    }

    @Bean
    @ConditionalOnMissingBean(name = "yamlSchemaWalker")
    public SchemaWalker<?> yamlSchemaWalker(
            ExampleYamlValueSerializer exampleYamlValueSerializer,
            SpringwolfConfigProperties springwolfConfigProperties) {
        return new DefaultSchemaWalker<>(new ExampleYamlValueGenerator(
                new ExampleJsonValueGenerator(), exampleYamlValueSerializer, springwolfConfigProperties));
    }

    @Bean
    @ConditionalOnMissingBean
    public TypeExtractor typeExtractor(SpringwolfConfigProperties springwolfConfigProperties) {
        return new TypeExtractor(springwolfConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadExtractor payloadExtractor(TypeExtractor typeExtractor) {
        return new PayloadExtractor(typeExtractor);
    }

    @Bean
    @ConditionalOnMissingBean
    public HeaderClassExtractor headerClassExtractor(SwaggerSchemaService schemaService) {
        return new HeaderClassExtractor(schemaService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadService payloadService(
            SchemasService schemasService, SpringwolfConfigProperties springwolfConfigProperties) {
        return new PayloadService(schemasService, springwolfConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadAsyncOperationService payloadAsyncOperationService(
            PayloadExtractor payloadExtractor, PayloadService payloadService) {
        return new PayloadAsyncOperationService(payloadExtractor, payloadService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadMethodParameterService payloadMethodParameterService(
            PayloadExtractor payloadExtractor, PayloadService payloadService) {
        return new PayloadMethodParameterService(payloadExtractor, payloadService);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadMethodReturnService payloadMethodReturnService(PayloadService payloadService) {
        return new PayloadMethodReturnService(payloadService);
    }

    @Bean
    @ConditionalOnMissingBean
    public StringValueResolverProxy stringValueResolverProxy() {
        return new StringValueResolverProxy();
    }

    @Bean
    @ConditionalOnMissingBean
    public AsyncAnnotationMessageService asyncAnnotationMessageService(
            MessagesService messagesService,
            SchemasService schemasService,
            PayloadAsyncOperationService payloadAsyncOperationService,
            List<MessageBindingProcessor> messageBindingProcessors,
            StringValueResolverProxy stringValueResolver) {
        return new AsyncAnnotationMessageService(
                payloadAsyncOperationService,
                messagesService,
                schemasService,
                messageBindingProcessors,
                stringValueResolver);
    }
}
